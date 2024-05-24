package com.thelab.spring.keycloak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/admin")
    public String sayHelloAdmin(){
        return "Hello Admin";
    }

    @GetMapping("/user")
    public String sayHelloUser(){
        return "Hello user";
    }
}
