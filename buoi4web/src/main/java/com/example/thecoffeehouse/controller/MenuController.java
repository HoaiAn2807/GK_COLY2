package com.example.thecoffeehouse.controller;

import com.example.thecoffeehouse.service.HangHoaService;
import com.example.thecoffeehouse.service.LoaiHangService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {

    private final HangHoaService hangHoaService;
    private final LoaiHangService loaiHangService;

    public MenuController(HangHoaService hangHoaService, LoaiHangService loaiHangService) {
        this.hangHoaService = hangHoaService;
        this.loaiHangService = loaiHangService;
    }

    @GetMapping("/menu")
    public String menu(@RequestParam(required = false) Long maloai, Model model) {
        model.addAttribute("dsLoaiHang", loaiHangService.getParentCategories());
        if (maloai != null) {
            model.addAttribute("dsHangHoa", hangHoaService.getByLoai(maloai));
        } else {
            model.addAttribute("dsHangHoa", hangHoaService.getAll());
        }
        return "menu";
    }
}
