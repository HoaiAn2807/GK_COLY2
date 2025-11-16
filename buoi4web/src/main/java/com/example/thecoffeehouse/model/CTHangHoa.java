package com.example.thecoffeehouse.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cthanghoa")
public class CTHangHoa {
    @EmbeddedId
    private CTHangHoaId id;

    @ManyToOne
    @MapsId("mahang")
    @JoinColumn(name = "mahang")
    private HangHoa hangHoa;

    @ManyToOne
    @MapsId("idsize")
    @JoinColumn(name = "idsize")
    private SizeNuoc sizeNuoc;

    private Double gia;

    public CTHangHoaId getId() { return id; }
    public void setId(CTHangHoaId id) { this.id = id; }

    public HangHoa getHangHoa() { return hangHoa; }
    public void setHangHoa(HangHoa hangHoa) { this.hangHoa = hangHoa; }

    public SizeNuoc getSizeNuoc() { return sizeNuoc; }
    public void setSizeNuoc(SizeNuoc sizeNuoc) { this.sizeNuoc = sizeNuoc; }

    public Double getGia() { return gia; }
    public void setGia(Double gia) { this.gia = gia; }
}
