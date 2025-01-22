package com.mazlumemre.notcum.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazlumemre.notcum.entity.Stationery;
import com.mazlumemre.notcum.service.StationeryService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/stationery")
public class StationeryController {

    @Autowired
    private StationeryService stationeryService;


    @PostMapping(path = "/save")
    public Stationery saveStationery(@RequestBody Stationery stationery) {
        return stationeryService.saveStationery(stationery);
    }

    @GetMapping(path = "/list")
    public List<Stationery> getStationerList() {
        return stationeryService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stationery> getStationeryById(@PathVariable Long id) {
        // Veritabanından notu bulup döndürme
        Stationery stationery = stationeryService.getStationeryById(id);

        if (stationery != null) {
            return ResponseEntity.ok(stationery);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginStationery(@RequestBody Map<String, String> loginRequest, HttpSession session) {
        String mail = loginRequest.get("mail");
        String password = loginRequest.get("password");

        Optional<Stationery> stationery = stationeryService.authenticateAndGetStationery(mail, password);
        if (stationery.isPresent()) {
            session.setAttribute("loggedUser", stationery.get());
            return ResponseEntity.ok("Giriş başarılı!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hatalı mail veya şifre!");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutStationery(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Çıkış başarılı!");
    }


    @GetMapping("/current-stationery")
    public ResponseEntity<Stationery> getCurrentUser(HttpSession session) {
        Stationery loggedStationery = (Stationery) session.getAttribute("loggedUser");
        if (loggedStationery != null) {
            return ResponseEntity.ok(loggedStationery);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
