package com.ggkps.superflix.filters;

import com.ggkps.superflix.models.AuthUser;
import com.ggkps.superflix.services.AuthUserDetailService;
import com.ggkps.superflix.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * The {@link JwtControlFilter} class is a jwt-specific
 * implementation of the {@link OncePerRequestFilter} abstract
 * class.
 * @author geozi
 * version 1
 */
@Component
public class JwtControlFilter extends OncePerRequestFilter {

    @Autowired
    private AuthUserDetailService authUserDetailService;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull
    FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = jwtService.extractToken(request);
            if(!Objects.equals(jwt, null) && jwtService.validateToken(jwt)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                String username = jwtService.extractUsername(jwt);
                String role = jwtService.extractRole(jwt);
                AuthUser authUser = (AuthUser) authUserDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                if(!checkAuthorizationRoute(role, request.getRequestURI())) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
                    return;
                }
            }
        } catch(UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }

    protected boolean checkAuthorizationRoute(String role, String URI) {
        String adminPath = "/api/v1/admin";
        String userPath = "/api/v1/user";
        String professionalPath = "/api/v1/professional";

        if (URI.startsWith(adminPath)) {
            return role.equals("ADMIN");
        }
        if (URI.startsWith(userPath)) {
            return role.equals("USER");
        }
        if (URI.startsWith(professionalPath)) {
            return role.equals("PRO");
        }
        return false;
    }
}