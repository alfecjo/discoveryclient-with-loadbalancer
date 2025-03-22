package com.app.serviceb.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ServiceBRestController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    // Injeção do RestTemplate com o @LoadBalanced
    @Autowired
    public ServiceBRestController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/helloEureka")
    public String helloWorld() {
        // Usando o nome do serviço registrado no Eureka diretamente com o RestTemplate
        String url = "http://servicea/helloWorld";
        return restTemplate.getForObject(url, String.class);
    }
}
