package com.example.thecoffeehouse.service;

import com.example.thecoffeehouse.model.HangHoa;
import com.example.thecoffeehouse.repository.HangHoaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HangHoaService {
    private final HangHoaRepository hangHoaRepository;

    public HangHoaService(HangHoaRepository hangHoaRepository) {
        this.hangHoaRepository = hangHoaRepository;
    }

    public List<HangHoa> getAll() {
        return hangHoaRepository.findAll();
    }

    public HangHoa getById(Long id) {
        return hangHoaRepository.findById(id).orElse(null);
    }

    public List<HangHoa> getByLoai(Long maloai) {
        return hangHoaRepository.findByLoaiHang_Maloai(maloai);
    }
}
