package com.example.thecoffeehouse.model.cuahang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // Quan hệ 1-Nhiều: Một khu vực (Region) có nhiều quận (District)
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("region") // Ngăn lặp vô hạn khi serialize JSON
    private Set<com.example.thecoffeehouse.model.cuahang.District> districts;

    // Constructors
    public Region() {}

    public Region(String name) {
        this.name = name;
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

    public Set<com.example.thecoffeehouse.model.cuahang.District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<com.example.thecoffeehouse.model.cuahang.District> districts) {
        this.districts = districts;
    }
}

