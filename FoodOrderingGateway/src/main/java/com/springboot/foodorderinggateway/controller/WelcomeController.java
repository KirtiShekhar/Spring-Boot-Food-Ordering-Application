package com.springboot.foodorderinggateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController 
{
	@GetMapping("/")
	public @ResponseBody String hello(){
		return "Welcome To Food Ordering Application";
	}

}
