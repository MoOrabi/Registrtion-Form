package com.registrationform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Person {

    @Id
    @Size(min = 14, max = 14)
    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    
    @NotNull
    private String image;
    @NotNull
    private int level;

    @NotNull
    private String theNameOfDar;



}
