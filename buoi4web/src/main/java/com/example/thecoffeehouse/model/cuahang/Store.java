package com.example.thecoffeehouse.model.cuahang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String hours;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "map_url")
    private String mapUrl;

    // Quan hệ Nhiều-1: Nhiều cửa hàng (Store) thuộc một quận (District)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    @JsonIgnoreProperties("stores") // Ngăn lặp vô hạn
    private District district;

    // Quan hệ Nhiều-Nhiều: Một cửa hàng có nhiều tiện ích
    @ManyToMany(fetch = FetchType.EAGER) // Tải EAGER để lấy tiện ích ngay lập tức
    @JoinTable(
            name = "store_amenities",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities;

    // Constructors
    public Store() {}

    public Store(String name, String address, String hours, String imageUrl, String mapUrl, District district, Set<Amenity> amenities) {
        this.name = name;
        this.address = address;
        this.hours = hours;
        this.imageUrl = imageUrl;
        this.mapUrl = mapUrl;
        this.district = district;
        this.amenities = amenities;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenity> amenities) {
        this.amenities = amenities;
    }
}