package com.ccr.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorld
 * @author ccr
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello SpringBoot";
    }
}
