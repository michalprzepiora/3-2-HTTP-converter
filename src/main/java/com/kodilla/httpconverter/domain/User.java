package com.kodilla.httpconverter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private String name;
    private String surname;
    private int phone;
    private String email;
}
