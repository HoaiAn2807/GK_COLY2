package com.example.thecoffeehouse.service;

import com.example.thecoffeehouse.model.cuahang.*;
import com.example.thecoffeehouse.model.cuahangDTO.*;
import com.example.thecoffeehouse.repository.cuahang.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreLocatorService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final AmenityRepository amenityRepository;

    public StoreLocatorService(StoreRepository storeRepository,
                               RegionRepository regionRepository,
                               DistrictRepository districtRepository,
                               AmenityRepository amenityRepository) {
        this.storeRepository = storeRepository;
        this.regionRepository = regionRepository;
        this.districtRepository = districtRepository;
        this.amenityRepository = amenityRepository;
    }

    // ✅ 1. Lấy tất cả khu vực
    public List<RegionDTO> getAllRegions() {
        return regionRepository.findAll()
                .stream()
                .map(r -> new RegionDTO(r.getId(), r.getName()))
                .collect(Collectors.toList());
    }

    // ✅ 2. Lấy danh sách quận theo khu vực
    public List<DistrictDTO> getDistrictsByRegion(Integer regionId) {
        return districtRepository.findByRegion_Id(regionId)
                .stream()
                .map(d -> new DistrictDTO(d.getId(), d.getName(), d.getRegion().getId()))
                .collect(Collectors.toList());
    }

    // ✅ 3. Lấy danh sách cửa hàng theo quận
    public List<StoreDTO> getStoresByDistrict(Integer districtId) {
        return storeRepository.findByDistrict_Id(districtId)
                .stream()
                .map(s -> new StoreDTO(
                        s.getId(),
                        s.getName(),
                        s.getAddress(),
                        s.getHours(),
                        s.getImageUrl(),
                        s.getMapUrl()))
                .collect(Collectors.toList());
    }

    // ✅ 4. Lấy tất cả tiện ích
    public List<AmenityDTO> getAllAmenities() {
        return amenityRepository.findAll()
                .stream()
                .map(a -> new AmenityDTO(a.getId(), a.getName(), a.getIcon()))
                .collect(Collectors.toList());
    }

    // ✅ 5. Lấy chi tiết cửa hàng
    public StoreDTO getStoreDetails(Integer storeId) {
        Store s = storeRepository.findById(storeId).orElse(null);
        if (s == null) return null;
        return new StoreDTO(
                s.getId(),
                s.getName(),
                s.getAddress(),
                s.getHours(),
                s.getImageUrl(),
                s.getMapUrl());
    }
}
