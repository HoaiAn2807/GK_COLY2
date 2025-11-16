package com.example.thecoffeehouse.repository;

import com.example.thecoffeehouse.model.LoaiHang;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoaiHangRepository extends JpaRepository<LoaiHang, Long> {
    List<LoaiHang> findByParentIsNull();
}
