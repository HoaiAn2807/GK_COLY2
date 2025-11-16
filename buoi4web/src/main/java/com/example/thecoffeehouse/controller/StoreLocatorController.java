package com.example.thecoffeehouse.controller;


import com.example.thecoffeehouse.model.cuahangDTO.AmenityDTO;
import com.example.thecoffeehouse.model.cuahangDTO.DistrictDTO;
import com.example.thecoffeehouse.model.cuahangDTO.RegionDTO;
import com.example.thecoffeehouse.model.cuahangDTO.StoreDTO;
import com.example.thecoffeehouse.service.StoreLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores") // Tiền tố chung cho tất cả API
@CrossOrigin(origins = "*") // Cho phép gọi từ mọi domain (thay * bằng domain của bạn khi deploy)
public class StoreLocatorController {
    @Autowired
    private StoreLocatorService storeLocatorService;

    // API: Lấy tất cả các khu vực
    // Ví dụ: GET http://localhost:8080/api/stores/regions
    @GetMapping("/regions")
    public List<RegionDTO> getAllRegions() {
        return storeLocatorService.getAllRegions();
    }

    // API: Lấy các quận theo khu vực
    // Ví dụ: GET http://localhost:8080/api/stores/districts?regionId=1
    @GetMapping("/districts")
    public List<DistrictDTO> getDistrictsByRegion(@RequestParam Integer regionId) {
        return storeLocatorService.getDistrictsByRegion(regionId);
    }

    // API: Lấy các cửa hàng theo quận
    // Ví dụ: GET http://localhost:8080/api/stores?districtId=2
    @GetMapping
    public List<StoreDTO> getStoresByDistrict(@RequestParam Integer districtId) {
        return storeLocatorService.getStoresByDistrict(districtId);
    }

    // API: Lấy tất cả tiện ích
    // Ví dụ: GET http://localhost:8080/api/stores/amenities
    @GetMapping("/amenities")
    public List<AmenityDTO> getAllAmenities() {
        return storeLocatorService.getAllAmenities();
    }

    // API: Lấy chi tiết một cửa hàng
    // Ví dụ: GET http://localhost:8080/api/stores/3
    @GetMapping("/{storeId}")
    public StoreDTO getStoreDetails(@PathVariable Integer storeId) {
        return storeLocatorService.getStoreDetails(storeId);
    }

}
