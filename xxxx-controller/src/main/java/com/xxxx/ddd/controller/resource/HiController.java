package com.xxxx.ddd.controller.resource;

import com.xxxx.ddd.application.service.event.EventAppService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HiController {

    @Autowired
    private EventAppService eventAppService;

    @GetMapping("/hi")
    @RateLimiter(name = "backendA", fallbackMethod = "fallbackHello")
    public String Hello() {
        return eventAppService.sayHi("thieu bao tram");
    }

    @GetMapping("/hi/v1")
    @RateLimiter(name = "backendB", fallbackMethod = "fallbackHello")
    public String sayHi() {
        // return "Hello Controller!";
        return eventAppService.sayHi("Ho");
    }

    @GetMapping("/circuit/breaker")
    @CircuitBreaker(name = "checkRandom", fallbackMethod = "fallbackCircuitBreaker")
    public String circuitBreaker() {


        return null;
    }

    public String fallbackCircuitBreaker(Throwable throwable) {
        return throwable.getMessage();
    }

    public String fallbackHello(Throwable throwable) {
        return "Too many requests";
    }



}


