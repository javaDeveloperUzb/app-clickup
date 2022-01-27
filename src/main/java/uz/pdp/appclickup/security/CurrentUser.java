package uz.pdp.appclickup.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;

import java.lang.annotation.*;

///Bu anotatsiya userni olish uchun ishlatiladi xuddiku
///User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); shu b.n bir xil
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal
public @interface CurrentUser {

}
