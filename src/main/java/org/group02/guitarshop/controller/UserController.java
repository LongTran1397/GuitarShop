package org.group02.guitarshop.controller;

import org.group02.guitarshop.entity.User;
import org.group02.guitarshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

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
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
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
            modelAndView.addObject("successMessage", "Đăng ký thành công!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/main/login");
        }
        return modelAndView;
    }
}
