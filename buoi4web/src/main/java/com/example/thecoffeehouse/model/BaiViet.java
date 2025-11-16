package com.example.thecoffeehouse.model;

import jakarta.persistence.*;

@Entity
@Table(name = "baiviet")
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tieude;
    private String ngaydang;

    @Column(columnDefinition = "TEXT")
    private String noidung;

    private String anh;
    private String tacgia;
    private String chude;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTieude() { return tieude; }
    public void setTieude(String tieude) { this.tieude = tieude; }

    public String getNgaydang() { return ngaydang; }
    public void setNgaydang(String ngaydang) { this.ngaydang = ngaydang; }

    public String getNoidung() { return noidung; }
    public void setNoidung(String noidung) { this.noidung = noidung; }

    public String getAnh() { return anh; }
    public void setAnh(String anh) { this.anh = anh; }

    public String getTacgia() { return tacgia; }
    public void setTacgia(String tacgia) { this.tacgia = tacgia; }

    public String getChude() { return chude; }
    public void setChude(String chude) { this.chude = chude; }
}
