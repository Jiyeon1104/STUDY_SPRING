package com.example.board.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomError implements ErrorController {
    @GetMapping("/error")
    public String hanleError(){
        return "error/error";
    }
}
