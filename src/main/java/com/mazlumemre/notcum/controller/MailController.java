package com.mazlumemre.notcum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mazlumemre.notcum.service.MailService;

@RestController
@RequestMapping(path = "/contactus")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping(path = "/send")
    public String sendMail(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String message) {
        String subject = "Yeni bir bize ulaşın maili";
        String body = "İsim: " + name + "\nE-Posta: " + email + "\nMesaj: " + message;

        mailService.sendMail("azradalhasanoglu@gmail.com", subject, body);

        return "Mail basaşarıyla gönderildi";
    }
}
