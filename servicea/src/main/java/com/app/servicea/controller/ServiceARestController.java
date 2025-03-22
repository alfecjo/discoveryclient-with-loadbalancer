package com.app.servicea.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceARestController {

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello world from Service A!";
	}

	@GetMapping("/api/health")
    public String healthCheck() {
        return "OK";
    }

}
