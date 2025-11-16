package com.example.thecoffeehouse.controller;

import com.example.thecoffeehouse.model.KhachHang;
import com.example.thecoffeehouse.service.KhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
@Controller
public class LoginController {
    @Autowired
    private KhachHangService khService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
      /*
    @RequestParam String username
    Lấy giá trị từ input name="username" trong form.
    Ví dụ người dùng nhập “phuong123” thì biến username sẽ nhận "phuong123".
    @RequestParam String password
    Lấy giá trị từ input name="password" trong form (mật khẩu người dùng gõ).
    Model model
    Dùng để truyền dữ liệu sang view (chẳng hạn như lỗi "Tên đăng nhập hoặc mật khẩu không đúng" để hiển thị lại trên trang login.html).
    HttpSession session
    Dùng để lưu thông tin người dùng đăng nhập trong bộ nhớ tạm của session.
     Khi user đăng nhập thành công, ta lưu username và userid vào session để các trang khác có thể nhận biết người dùng hiện tại là ai.
    * */
    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          Model model,
                          HttpSession session) {
        Optional<KhachHang> opt = khService.authenticate(username, password);
        if (opt.isPresent()) {
            KhachHang kh = opt.get();
            session.setAttribute("username", kh.getUsername());
            session.setAttribute("userid", kh.getMakh());
            System.out.println(username);
            return "redirect:/"; // thành công về trang chủ

        }
        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
        return "login";
    }

    // Lấy GET logout (link)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // Nhấn vào logout thì dùng invalidate session để đăng xuất
    // invalidate(): Hủy toàn bộ session (đăng xuất, hết hạn)
    @PostMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
