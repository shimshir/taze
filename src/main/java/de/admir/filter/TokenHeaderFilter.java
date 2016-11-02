package de.admir.filter;

import de.admir.util.Helper;

import org.apache.commons.lang.StringUtils;
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
public class TokenHeaderFilter extends OncePerRequestFilter {
    private static final Logger LOG = Logger.getLogger(TokenHeaderFilter.class);
    private static final Pattern REGEX_PATTERN = Pattern.compile(API_REST_BASE_PATH + "/orders/?.*");
    private String token;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        if (REGEX_PATTERN.matcher(req.getRequestURI()).matches() && !Helper.isSafeRequest(req.getMethod())) {
            String tokenHeader = req.getHeader("X-Confirmation-Token");
            token = tokenHeader == null ? StringUtils.EMPTY : tokenHeader;
        } else {
            token = null;
        }
        chain.doFilter(req, res);
    }

    public Optional<String> getToken() {
        return Optional.ofNullable(token);
    }
}
