package com.mazlumemre.notcum.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mazlumemre.notcum.entity.Note;
import com.mazlumemre.notcum.service.NoteService;


@Controller
public class PageController {


    @Autowired
    private NoteService noteService;


    @GetMapping(path = "/")
    public String showcasePage() {
        return "showcase";
    }

    @GetMapping(path = "/start")
    public String startPage() {
        return "start";
    }

    @GetMapping(path = "/start-stationery")
    public String startStationeryPage() {
        return "start-stationery";
    }

    @GetMapping("/start-user")
    public String startUserPage() {
        return "start-user";
    }


    @GetMapping(path = "/signup")
    public String signUpPage() {
        return "signup";
    }

    @GetMapping(path = "/signup-user")
    public String signUpUserPage() {
        return "signup-user";
    }

    @GetMapping(path = "/signup-stationery")
    public String signUpStationeryPage() {
        return "signup-stationery";
    }


    @GetMapping(path = "/notes")
    public String notesPage() {
        return "notes";
    }

    @GetMapping("/note-detail")
    public String noteDetailPage(@RequestParam Long id, Model model) {
        // id'ye göre notu veritabanından al
        Note note = noteService.getNoteById(id);

        // Not bilgilerini model'e ekle
        model.addAttribute("note", note);
        return "note-detail";
    }


    @GetMapping(path = "/note-upload")
    public String noteUploadPage() {
        return "note-upload";
    }


    @GetMapping(path = "/home")
    public String homePage() {
        return "home"; // home.html dosyasını döndürür
    }

    @GetMapping(path = "/home-stationery")
    public String homeStationeryPage() {
        return "home-stationery";
    }

    @GetMapping(path = "/contactus")
    public String contactUsPage() {
        return "contact-us";
    }

    @GetMapping(path = "/aboutus")
    public String aboutUsPage() {
        return "about-us";
    }

    @GetMapping(path = "/profile")
    public String profilePage() {
        return "profile";
    }
}
