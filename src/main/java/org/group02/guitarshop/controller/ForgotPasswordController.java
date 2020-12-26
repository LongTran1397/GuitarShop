package org.group02.guitarshop.controller;

import net.bytebuddy.utility.RandomString;
import org.group02.guitarshop.entity.User;
import org.group02.guitarshop.others.Utility;
import org.group02.guitarshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "main/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "GuitarShop đã gửi đường dẫn Reset Mật khẩu tới Email của bạn.");

        } catch (IOException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (MessagingException e) {
            model.addAttribute("error", "Không gửi đc Email.");
        }

        return "main/message";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("guitarshop@gmail.com", "GuitarShop");
        helper.setTo(recipientEmail);

        String subject = "Guitar Shop - Reset Mật khẩu";

        String content = "<p>Chào bạn,</p>"
                + "<p>Chúng tôi đã nhận được yêu cầu Reset Mật khẩu của bạn.</p>"
                + "<p>Click vào link bên dưới để đổi mật khẩu:</p>"
                + "<p><a href=\"" + link + "\">Reset Mật khẩu</a></p>"
                + "<br>"
                + "<p>Bỏ qua Mail này nếu bạn đã đăng nhập được hoặc bạn không gửi yêu cầu Reset mật khảu.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (user == null) {
            model.addAttribute("message", "Token không tồn tại!!!");
            return "main/message";
        }
        return "main/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset mật khẩu");

        if (user == null) {
            model.addAttribute("message", "Token không tồn tại!!!");
            return "main/message";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "Bạn đã đổi mật khẩu thành công!!!");
        }

        return "main/message";
    }
}