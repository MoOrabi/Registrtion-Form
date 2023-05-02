package com.registrationform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotNull.user.firstName}")
    private String firstName;

    @NotBlank(message = "{NotBlank.user.lastName}")
    private String lastName;

    @NotNull(message = "{Range.user.age}")
    @Min(value = 5, message = "{Range.user.age}")
    @Max(value = 20, message = "{Range.user.age}")
    private Integer age;

    @NotNull(message = "{Range.user.level}")
    @Min(value = 1, message = "{Range.user.level}")
    @Max(value = 8, message = "{Range.user.level}")
    private Integer level;

    @NotBlank(message = "{Size.user.nationalId}")
    @Size(min = 14, max = 14, message = "Size.user.nationalId}")
    private String nationalId;

    @Lob
    private byte[] image;

    @NotBlank(message = "{NotBlank.user.darName}")
    private String darName;

    @NotBlank(message = "{Size.user.phone}")
    @Size(min = 11, max = 11, message = "{Size.user.phone}")
    private String phone;

    @NotNull(message = "Registration date is mandatory")
    private LocalDate registrationDate;



}
