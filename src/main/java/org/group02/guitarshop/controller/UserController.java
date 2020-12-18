package org.group02.guitarshop.controller;

import net.bytebuddy.utility.RandomString;
import org.group02.guitarshop.entity.Product;
import org.group02.guitarshop.entity.User;
import org.group02.guitarshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value="/dang-ky", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/main/register");
        return modelAndView;
    }

    @RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Địa chỉ email đã có người đăng ký! Vui lòng thử lại.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/main/register");
        } else {
            userService.saveUser(user);
            String siteURL = Utility.getSiteURL(request);
            userService.sendVerificationEmail(user, siteURL);
            modelAndView.addObject("successMessage", "Đăng ký thành công!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("main/login");
        }
        return modelAndView;
    }

    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code){
        boolean verified = userService.verify(code);
        return "main/" + (verified ? "verify_success" : "verify_fail");
    }
}
