package com.rehome.chat.filter;

import com.rehome.chat.entity.UserDetailsImpl;
import com.rehome.chat.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = jwtUtils.getJwtFromCookies(request);
            if (StringUtils.isBlank(jwt) || !jwtUtils.validateJwtToken(jwt)) {
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwtUtils.getAllClaims(jwt);
            UserDetailsImpl userDetails = createUserDetailsFromClaims(claims);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.error("JwtAuthFilter error: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private UserDetailsImpl createUserDetailsFromClaims(Claims claims) {
        boolean isHandyman = (boolean) claims.get("is_handyman");
        String role = isHandyman ? "ROLE_HANDYMAN" : "ROLE_USER";

        return UserDetailsImpl.builder()
            .id(((Integer) claims.get("user_id")).longValue())
            .username((String) claims.get("username"))
            .authorities(List.of(new SimpleGrantedAuthority(role)))
            .build();
    }
}

