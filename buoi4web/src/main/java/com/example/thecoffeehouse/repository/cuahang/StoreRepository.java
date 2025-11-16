package com.example.thecoffeehouse.repository.cuahang;

import com.example.thecoffeehouse.model.cuahang.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    List<Store> findByDistrict_Id(Integer districtId);
    List<Store> findByDistrict_Region_Id(Integer regionId);
}
