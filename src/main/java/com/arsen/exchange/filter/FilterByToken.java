package com.arsen.exchange.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterByToken extends OncePerRequestFilter {

    private String securityTokenHeader = "token";
    private String securityToken = "token";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenFromRequest = request.getHeader(securityTokenHeader);
        if (checkToken(tokenFromRequest)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            logger.error("Not authenticated activity");
        }
    }

    private boolean checkToken(String tokenFromRequest) {
        return securityToken.equals(tokenFromRequest);
    }
}