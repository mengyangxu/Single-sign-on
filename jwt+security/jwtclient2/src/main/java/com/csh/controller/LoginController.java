package com.csh.controller;

import com.csh.util.CookieUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";


    @RequestMapping("/log")
    public String login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            System.out.println("当前用户:" + auth.getPrincipal());
        }
            return "first";
    }


    @RequestMapping("/exit")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            System.out.println("当前用户:"+auth.getPrincipal());
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        CookieUtil.clear(response,jwtTokenCookieName);
        return "exit";
    }

}
