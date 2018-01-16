package com.csh.controller;

import com.csh.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * loginController
 * 登录退出controller
 * @author xumengyang on 2017/12.
 */

/**
 *
 */
@Controller
public class LoginController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";


    @RequestMapping("/first")
    public String log(){
        return "first";
    }

    @RequestMapping("/tologin")
    public ModelAndView login(HttpServletRequest req){
        ModelAndView mv = new ModelAndView();
        String redirect = req.getParameter("redirect");
       // System.out.println("url:"+redirect);

        if(null!=req.getParameter("code")&&""!=req.getParameter("code")){       //这里code只设置一种情况所以不作分析
            mv.addObject("error","登录名或密码错误");
        }

        mv.addObject("redirect",redirect);
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/exit")
    public String logout(HttpServletResponse response){
        CookieUtil.clear(response,jwtTokenCookieName);
        return "exit";
       // return "redirect:http://localhost:8080/exit";
    }


}
