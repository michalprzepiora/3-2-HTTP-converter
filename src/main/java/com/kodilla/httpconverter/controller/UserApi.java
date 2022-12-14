package com.kodilla.httpconverter.controller;

import com.kodilla.httpconverter.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {

    @PostMapping("/add")
    public void acceptCustomTextType(@RequestBody User user){
        System.out.println(user);
    }
}
