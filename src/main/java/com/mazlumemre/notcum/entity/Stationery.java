package com.mazlumemre.notcum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stationery")
public class Stationery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stationery_name", nullable = false)
    private String name;

    @Column(name = "stationery_mail", nullable = false)
    private String mail;

    @Column(name = "stationery_password", nullable = false)
    private String password;

    @Column(name = "stationery_address", nullable = false)
    private String address;

}
