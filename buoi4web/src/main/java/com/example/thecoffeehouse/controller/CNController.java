package com.example.thecoffeehouse.controller;

import com.example.thecoffeehouse.service.BaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CNController {

    @Autowired
    private BaiVietService baiVietService;

    @GetMapping("/BaiViet")
    public String chuyenNha(
            @RequestParam(value = "chude", required = false) String chude,
            @RequestParam(defaultValue = "1") int page,
            Model model) {

        int pageSize = 5; // Hiển thị 5 bài viết mỗi trang
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<?> baivietPage;

        // Nếu có chủ đề, lọc theo chủ đề
        if (chude != null && !chude.isEmpty()) {
            baivietPage = baiVietService.getBaiVietByChude(chude, pageable);
        } else {
            baivietPage = baiVietService.getAllBaiViet(pageable);
        }

        model.addAttribute("baivietPage", baivietPage);
        model.addAttribute("selectedChude", chude);
        return "HomeBV1";
    }
}
