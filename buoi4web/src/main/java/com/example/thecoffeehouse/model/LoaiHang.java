package com.example.thecoffeehouse.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "loaihang")
public class LoaiHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maloai;

    private String tenloai;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private LoaiHang parent;

    @OneToMany(mappedBy = "parent")
    private List<LoaiHang> children;

    public Long getMaloai() { return maloai; }
    public void setMaloai(Long maloai) { this.maloai = maloai; }

    public String getTenloai() { return tenloai; }
    public void setTenloai(String tenloai) { this.tenloai = tenloai; }

    public LoaiHang getParent() { return parent; }
    public void setParent(LoaiHang parent) { this.parent = parent; }

    public List<LoaiHang> getChildren() { return children; }
    public void setChildren(List<LoaiHang> children) { this.children = children; }
}
