package com.example.thecoffeehouse.repository;

import com.example.thecoffeehouse.model.BaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BaiVietRepository extends JpaRepository<BaiViet, Long> {
    // Dùng cho chế độ bình thường (cũ)
    List<BaiViet> findByChude(String chude);

    // Dùng cho phân trang (mới)
    Page<BaiViet> findByChude(String chude, Pageable pageable);
}
