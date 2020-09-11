package com.example.miniorange_demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.HashMap;
import java.util.Map;


@Controller
public class MailController {

    private static final Logger LOG =  LoggerFactory.getLogger(MailController.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    UserRepository repository;

    @GetMapping("/addEmail")
    public String sendForm(Model model) {
        //model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping("/addEmail")
    public String processEmail(@RequestParam("email") String email, @RequestParam String description,@RequestParam String email_input, ModelMap modelMap){

            email_input = email_input.replaceAll("/s"," ");
            modelMap.put("email",email);
            User user2 = new User();
            user2.setDescription(description);
            user2.setMailTo(email);
            user2.setSubject("Fraud Report");
            user2.setMailFrom("chaudharyprince79782@gmail.com");
            repository.save(user2);

            emailService.sendSimpleEmail(user2,description,email_input);
            return "showStatus";
    }

    /*
    public String processForm(@ModelAttribute User user,Model model) {
        model.addAttribute("user",user);
        return "showStatus";
    }*/
}
