package com.example.thecoffeehouse.controller;

import com.example.thecoffeehouse.service.HangHoaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaPheController {

    private final HangHoaService hangHoaService;

    public CaPheController(HangHoaService hangHoaService) {
        this.hangHoaService = hangHoaService;
    }

    @GetMapping("/coffee")
    public String coffee(Model model) {
        Long maLoaiCaPhe = 26L; // ☕ mã “Cà Phê Tại Nhà”
        model.addAttribute("dsCaPhe", hangHoaService.getByLoai(maLoaiCaPhe));
        return "coffee";
    }
}
