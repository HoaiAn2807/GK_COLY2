package com.example.thecoffeehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StorePageController {

    @GetMapping("/stores-page")
    public String showStoreLocatorPage() {
        return "cuahang"; // Tên của file HTML (không có .html)
    }
}
