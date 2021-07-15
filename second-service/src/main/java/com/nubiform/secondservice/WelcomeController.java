package com.nubiform.secondservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/second-service")
public class WelcomeController {

    private final Environment environment;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Second Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info("message: {}", header);
        return "Hello World in Second Service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("server port: {}", request.getServerPort());
        return String.format("Hi, there. This is a message from Second Service on PORT %s", environment.getProperty("local.server.port"));
    }
}
