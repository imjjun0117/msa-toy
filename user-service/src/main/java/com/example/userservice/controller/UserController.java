package com.example.userservice.controller;

import com.example.userservice.UserService;
import com.example.userservice.vo.Greeting;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private Environment env;

    private Greeting greeting;

    private UserService userService;
    @Autowired
    public UserController(Environment env, Greeting greeting, UserService userService) {
        this.env = env;
        this.greeting = greeting;
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        log.info("welcome");

//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @GetMapping("/health-check")
    public String status() { //http:localhost:60000/health-check
        return "CHECK OK";
    }

}
