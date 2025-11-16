package com.example.thecoffeehouse.model.cuahang;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // Một quận thuộc một khu vực (Region)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties("districts") // tránh lặp khi serialize JSON
    private Region region;

    // Một quận có thể có nhiều cửa hàng
    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("district") // tránh vòng lặp khi trả JSON
    private Set<Store> stores;

    // Constructors
    public District() {}

    public District(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    // Getters & Setters
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }
}
