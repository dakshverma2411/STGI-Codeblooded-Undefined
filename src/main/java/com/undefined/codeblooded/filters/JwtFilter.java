package com.undefined.codeblooded.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.undefined.codeblooded.models.ApplicationUser;
import com.undefined.codeblooded.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Order(1)
public class JwtFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        if(Objects.equals(req.getRequestURI(), "/login")){
            chain.doFilter(request, response);
            return;
        }
        String token = "";
        try {
            token = req.getHeader("Authorization").substring(7);
        }
        catch (NullPointerException e){

            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(400);
            res.resetBuffer();
            Map<String, Object> map = new HashMap<>();
            map.put("msg", "JWT Token not found");
            res.getOutputStream().print(new ObjectMapper().writeValueAsString(map));
            res.flushBuffer();
            return;
        }
        try {
            if (jwtUtil.validateToken(token)) {
                chain.doFilter(request, response);
            }
        }
        catch (ExpiredJwtException e){

            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(401);
            res.resetBuffer();
            Map<String, Object> map = new HashMap<>();
            map.put("msg", "JWT Token Expired");
            res.getOutputStream().print(new ObjectMapper().writeValueAsString(map));
            res.flushBuffer();
            return;
        }
        catch (SignatureException e){
            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(401);
            res.resetBuffer();
            Map<String, Object> map = new HashMap<>();
            map.put("msg", "Invalid JWT Token");
            res.getOutputStream().print(new ObjectMapper().writeValueAsString(map));
            res.flushBuffer();
            return;
        }
        return;

    }

}