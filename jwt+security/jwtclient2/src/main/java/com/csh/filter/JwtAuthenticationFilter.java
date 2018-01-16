package com.csh.filter;

import com.csh.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @author zhaoxinguo on 2017/9/13.
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
       super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = JwtUtil.getSubject(request, jwtTokenCookieName, signingKey);

        if(username == null){
            String authService = "http://localhost:8080/login";
            response.sendRedirect(authService + "?redirect=" + request.getRequestURL());



        } else{
            request.setAttribute("username", username);


            

            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }





     /*

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }*/



    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String username = JwtUtil.getSubject(request, jwtTokenCookieName, signingKey);
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null);
        }
        return null;


    }

}
