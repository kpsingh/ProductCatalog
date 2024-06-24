package com.lld4.productcatalog.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControler {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
