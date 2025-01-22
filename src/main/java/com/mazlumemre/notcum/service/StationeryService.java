package com.mazlumemre.notcum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazlumemre.notcum.entity.Stationery;
import com.mazlumemre.notcum.repository.StationeryRepository;

@Service
public class StationeryService {

    @Autowired
    private StationeryRepository stationeryRepository;

    public Stationery saveStationery(Stationery stationery) {
        return stationeryRepository.save(stationery);
    }

    ;

    public List<Stationery> getAllUsers() {
        return stationeryRepository.findAll();
    }

    public Optional<Stationery> authenticateAndGetStationery(String mail, String password) {
        return stationeryRepository.findByMailAndPassword(mail, password);
    }

    public Stationery getStationeryById(Long id) {
        return stationeryRepository.findById(id).orElseThrow(() -> new RuntimeException("Not bulunamadı"));
    }
}
