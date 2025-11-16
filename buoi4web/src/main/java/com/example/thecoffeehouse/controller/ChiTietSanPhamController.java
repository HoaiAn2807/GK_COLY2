package com.example.thecoffeehouse.controller;

import com.example.thecoffeehouse.model.HangHoa;
import com.example.thecoffeehouse.service.HangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChiTietSanPhamController {

    @Autowired
    private HangHoaService hangHoaService;

    @GetMapping("/chitiet")
    public String chiTiet(@RequestParam Long id, Model model) {
        HangHoa hangHoa = hangHoaService.getById(id);
        if (hangHoa == null) return "redirect:/menu";

        List<HangHoa> spLienQuan = hangHoaService.getByLoai(hangHoa.getLoaiHang().getMaloai());
        spLienQuan.removeIf(h -> h.getMahang().equals(hangHoa.getMahang()));

        model.addAttribute("hangHoa", hangHoa);
        model.addAttribute("spLienQuan", spLienQuan);
        return "chitiet";
    }
}
