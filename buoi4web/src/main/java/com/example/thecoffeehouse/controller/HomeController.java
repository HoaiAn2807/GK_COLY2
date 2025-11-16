package com.example.thecoffeehouse.Controller;

import com.example.thecoffeehouse.repository.CategoryRepository;
import com.example.thecoffeehouse.repository.ProductRepository;
import com.example.thecoffeehouse.service.HangHoaService;
import com.example.thecoffeehouse.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/")
    public String home(Model model) {
        // Lấy 10 sản phẩm mới
        model.addAttribute("latestProducts", productServiceImpl.getLatest(1));

        // Lấy 8 sản phẩm khuyến mãi
        model.addAttribute("saleProducts", productServiceImpl.getSale(1));

        return "home";
    }
    @GetMapping("/chuyennha")
    public String chuyenNha() {
        return "HomeCN"; // đúng
    }

    @GetMapping("/tuyendung")
    public String tuyenDung() {
        return "HomeTD"; // đúng
    }

}


