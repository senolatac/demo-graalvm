package com.sha.demograalvm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @GetMapping("demo")
    public List<Demo> getBooks(){
        return List.of(
                new Demo("x","desc-x"),
                new Demo("y","desc-y"),
                new Demo("z","desc-z")
        );
    }

    public record Demo (String name, String description){}
}
