package com.example.thecoffeehouse.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "size_nuoc")
public class SizeNuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idsize;

    private String tensize;
    private String mota;

    @OneToMany(mappedBy = "sizeNuoc")
    private List<CTHangHoa> chiTiet;

    public Integer getIdsize() { return idsize; }
    public void setIdsize(Integer idsize) { this.idsize = idsize; }

    public String getTensize() { return tensize; }
    public void setTensize(String tensize) { this.tensize = tensize; }

    public String getMota() { return mota; }
    public void setMota(String mota) { this.mota = mota; }

    public List<CTHangHoa> getChiTiet() { return chiTiet; }
    public void setChiTiet(List<CTHangHoa> chiTiet) { this.chiTiet = chiTiet; }
}
