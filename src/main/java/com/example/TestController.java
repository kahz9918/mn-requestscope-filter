package com.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import javax.inject.Inject;

@Controller("/test")
public class TestController {
    @Inject
    private TestService testService;

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello " + testService.testBean();
    }
}
