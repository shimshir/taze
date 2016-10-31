package de.admir.filter;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static de.admir.Constants.API_REST_BASE_PATH;

@Component
public class ClientIpFilter extends OncePerRequestFilter {
    private static final Logger LOG = Logger.getLogger(ClientIpFilter.class);
    private static final Pattern REGEX_PATTERN = Pattern.compile(API_REST_BASE_PATH + "/sessions/?.*");
    private String clientIpAddress;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        if (REGEX_PATTERN.matcher(req.getRequestURI()).matches()) {
            clientIpAddress = req.getHeader("X-FORWARDED-FOR") == null ? req.getRemoteAddr() : req.getRemoteAddr() + ";" + req.getHeader("X-FORWARDED-FOR");
        } else {
            clientIpAddress = null;
        }
        chain.doFilter(req, res);
    }

    public Optional<String> getClientIpAddress() {
        return Optional.ofNullable(clientIpAddress);
    }
}
