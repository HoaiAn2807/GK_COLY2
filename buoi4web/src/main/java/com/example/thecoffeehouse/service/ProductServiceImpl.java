package com.example.thecoffeehouse.service;

import com.example.thecoffeehouse.model.HangHoa;
import com.example.thecoffeehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Đánh dấu đây là một Spring Service Bean
public class ProductServiceImpl {

    @Autowired
    private ProductRepository repo;

    public List<HangHoa> getLatest(int limit) {
        return repo.findLatest(Pageable.ofSize(limit));
    }

    public List<HangHoa> getSale(int limit) {
        return repo.findSale(Pageable.ofSize(limit));
    }
}
