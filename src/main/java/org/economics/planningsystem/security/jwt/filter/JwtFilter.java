package org.economics.planningsystem.security.jwt.filter;

import org.economics.planningsystem.security.jwt.provider.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class JwtFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;
    private final String headerName;

    public JwtFilter(String headerName, JwtProvider jwtProvider, UserDetailsService userDetailsService) {
        this.headerName = headerName;
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = parseTokenFromRequest(request);
        try {
            if (StringUtils.hasText(token)) {
                String login = jwtProvider.getLoginFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(login);
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception exception) {
            logger.error("Unable to authorize user: {}", exception.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String parseTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(headerName);
    }
}
