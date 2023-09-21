package com.rehome.chat.util;

import com.rehome.chat.service.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

	@Autowired
    private UserInfoServiceImpl userProfileService;

    public static boolean doesCurrentUserHaveAuthority(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() == null) {
            return false;
        }

        return securityContext.getAuthentication().getAuthorities().stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase(authority));
    }
}
