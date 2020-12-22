package org.group02.guitarshop.controller;

import org.group02.guitarshop.entity.Manufacturer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class LoginController {

    @RequestMapping(value={"/dang-nhap"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/login");
        if (error != null) {
            modelAndView.addObject("error", "Email và mật mã không hợp lệ!");
        }
        return modelAndView;
    }

    @RequestMapping("/success")
    public String loginPageRedirect(){
        return "redirect:/";
    }
}
