package com.example.thecoffeehouse.service;

import com.example.thecoffeehouse.model.LoaiHang;
import com.example.thecoffeehouse.repository.LoaiHangRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoaiHangService {
    private final LoaiHangRepository loaiHangRepository;

    public LoaiHangService(LoaiHangRepository loaiHangRepository) {
        this.loaiHangRepository = loaiHangRepository;
    }

    public List<LoaiHang> getParentCategories() {
        return loaiHangRepository.findByParentIsNull();
    }
}
