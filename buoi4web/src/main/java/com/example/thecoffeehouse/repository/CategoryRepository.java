package com.example.thecoffeehouse.repository;

import com.example.thecoffeehouse.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Kế thừa các phương thức CRUD cơ bản từ JpaRepository
}
