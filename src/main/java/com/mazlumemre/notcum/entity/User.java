package com.mazlumemre.notcum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Column(name = "user_name", nullable = false)
    @Size(max = 40, message = "İsim en fazla 40 karakter olmalıdır.")
    @NotBlank(message = "İsim boş olamaz.")
    private String name;

    @NotNull(message = "Surname cannot be null")
    @Column(name = "user_surname", nullable = false)
    @Size(max = 20, message = "Soyisim en fazla 20 karakter olmalıdır.")
    @NotBlank(message = "Soyisim boş olamaz.")
    private String surName;

    @NotNull(message = "mail cannot be null")
    @Column(name = "user_mail", nullable = false, unique = true)
    @Email(message = "Geçerli bir e-posta adresi giriniz.")
    @Pattern(regexp = ".*@posta\\.mu\\.edu\\.tr$", message = "E-posta adresi posta.mu.edu.tr uzantılı olmalıdır.")
    private String mail;

    @NotNull(message = "password cannot be null")
    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Şifre en az bir büyük harf, bir rakam içermelidir ve en az 8 karakter olmalıdır.")
    private String password;

    // Getters and Setters
}
	
	
	
	
	
	
	
	
	
	
	


