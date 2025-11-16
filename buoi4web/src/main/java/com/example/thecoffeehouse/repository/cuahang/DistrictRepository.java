package com.example.thecoffeehouse.repository.cuahang;

import com.example.thecoffeehouse.model.cuahang.District;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findByRegion_Id(Integer regionId);
}
