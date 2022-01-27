package uz.pdp.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.entity.enums.SystemRoleName;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.LoginDto;
import uz.pdp.appclickup.payload.RegisterDto;
import uz.pdp.appclickup.repository.UserRepository;
import uz.pdp.appclickup.security.JwtProvider;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager; // username va password bersa boldi ozi bazadan malumotolib tekshiradi loginni
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;


    //TODO KOD YOZAMIZ
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ApiResponse("Bunday user mavjud", false);
        }
        User user = new User(
                registerDto.getFullName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                SystemRoleName.SYSTEM_ROLE_USER
        );
        user.setEnabled(true);
        int code = new Random().nextInt(999999);
        user.setEmailCode(String.valueOf(code).substring(0, 4));
        userRepository.save(user);
        sendEmail(registerDto.getEmail(), user.getEmailCode());
        return new ApiResponse("User saqlandi", true);
    }

    public Boolean sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("uz@pdp.uz");
            message.setTo(sendingEmail);
            message.setSubject("Akkountni tasdiqlash");
            message.setText(emailCode);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (emailCode.equals(user.getEmailCode())) {
                user.setEnabled(true);
                user.setEmailCode(null);
                userRepository.save(user);
                return new ApiResponse("Akkount aktivlashtirildi", true);
            }
            return new ApiResponse("Kod xato", false);
        }
        return new ApiResponse("Bunday user mavjud emas", false);
    }

    public ApiResponse loginUser(LoginDto loginDto) {
        try {
            UserDetails userDetails = authService.loadUserByUsername(loginDto.getEmail());
            boolean matches = passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword());
//            Authentication authenticate = authenticationManager.
//                    authenticate(new UsernamePasswordAuthenticationToken(
//                            loginDto.getEmail(),
//                            loginDto.getPassword()
//                    ));
//            User user = (User) authenticate.getPrincipal();
            if (matches) {
                String token = jwtProvider.generateToken(loginDto.getEmail());
                return new ApiResponse("Token", true, token);
            }
            return new ApiResponse("ERROR", false);
        } catch (Exception e) {
            return new ApiResponse("Parol yoki login xato", false);
        }
    }
}
