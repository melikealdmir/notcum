package com.mazlumemre.notcum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mazlumemre.notcum.entity.Stationery;


@Repository
public interface StationeryRepository extends JpaRepository<Stationery, Long> {

    Optional<Stationery> findByMailAndPassword(String mail, String password);

}
