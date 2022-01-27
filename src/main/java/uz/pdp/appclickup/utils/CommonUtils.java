package uz.pdp.appclickup.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.entity.enums.SystemRoleName;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CommonUtils {

    public static Map<String, Object> getPrincipalAndRoleFromSecurityContextHolder() {
        Map<String, Object> map = new HashMap<String, Object>();

        User principalUser = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        principalUser = (User) authentication.getPrincipal();

        SystemRoleName principalUserRoles = principalUser.getSystemRoleName();
        map.put("principalUser", principalUser);
        map.put("principalUserRole", principalUserRoles.name());
        return map;
    }

    public static boolean isExistsAuthority(String workspaceRoleName, String roleName) {
        return workspaceRoleName.equals(roleName);
    }

    public static Integer generateCode() {
        return new Random().nextInt((999999 - 100000) + 1) + 100000;
    }
}
