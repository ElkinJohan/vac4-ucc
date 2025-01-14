package com.ucc.vacCauca.config.security.filter;

import com.ucc.vacCauca.config.security.AuthenticatedUser;
import com.ucc.vacCauca.enums.TokenTypeEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Collection;
import java.util.LinkedList;


public class AuthorizationFilter extends BasicAuthenticationFilter{

    private KeyPair keyPair;

    public AuthorizationFilter(AuthenticationManager authenticationManager, KeyPair keyPair) {
        super(authenticationManager);
        this.keyPair = keyPair;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(TokenTypeEnum.BEARER.getValue())) {
            chain.doFilter(req, res);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } catch (JwtException e) {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws JwtException {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION)
                .replace(TokenTypeEnum.BEARER.getValue() + " ", " ");
        Jws<Claims> decodedJWT = Jwts.parserBuilder().setSigningKey(this.keyPair.getPublic()).build()
                .parseClaimsJws(token);
        Collection<GrantedAuthority> authorities = new LinkedList<>();
        Long userId = decodedJWT.getBody().get("uid", Long.class);
        String username = decodedJWT.getBody().get("username", String.class);

        if (userId != null) {
            return new UsernamePasswordAuthenticationToken(
                    new AuthenticatedUser(userId, username),
                    null,
                    authorities);
        } else {
            throw new SessionAuthenticationException("session not found");
        }
    }

}
