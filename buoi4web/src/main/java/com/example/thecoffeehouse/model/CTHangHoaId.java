package com.example.thecoffeehouse.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CTHangHoaId implements Serializable {
    private Long mahang;
    private Integer idsize;

    public CTHangHoaId() {}
    public CTHangHoaId(Long mahang, Integer idsize) {
        this.mahang = mahang;
        this.idsize = idsize;
    }

    public Long getMahang() { return mahang; }
    public void setMahang(Long mahang) { this.mahang = mahang; }

    public Integer getIdsize() { return idsize; }
    public void setIdsize(Integer idsize) { this.idsize = idsize; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CTHangHoaId)) return false;
        CTHangHoaId that = (CTHangHoaId) o;
        return Objects.equals(mahang, that.mahang) &&
                Objects.equals(idsize, that.idsize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mahang, idsize);
    }
}
