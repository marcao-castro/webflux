package com.apirest.webflux.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("test")
@Slf4j
public class Test {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void test (@Valid @RequestBody String token){
        System.out.println(token);
    }

}
