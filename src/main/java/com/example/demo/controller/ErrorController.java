package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public String error() {
		return "Oops,something went wrong. <br/>Try checking the connection and make sure URL spelling is correct";
	}

	public String getErrorPath() {
		return PATH;
	}
}


