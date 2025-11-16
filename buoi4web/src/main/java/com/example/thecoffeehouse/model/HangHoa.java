package com.example.thecoffeehouse.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hanghoa")
public class HangHoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mahang;

    @Column(nullable = false)
    private String tenhang;

    private String hinhanh;

    @Column(length = 500)
    private String mota; // mô tả

    // Giá cơ bản và giá khuyến mãi
    @Column(name = "base_price", precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "discount_price", precision = 10, scale = 2)
    private BigDecimal discountPrice;

    // Cờ đánh dấu sản phẩm nổi bật
    @Column(name = "is_featured", nullable = false)
    private boolean isFeatured = false;

    // Loại hàng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maloai")
    private LoaiHang loaiHang;

    // Danh sách chi tiết (size, giá)
    @OneToMany(mappedBy = "hangHoa", cascade = CascadeType.ALL)
    private List<CTHangHoa> chiTiet;

    // Ngày tạo tự động
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Tính giá nhỏ nhất để hiển thị nhanh (không lưu DB)
    @Transient
    public Double getGiaMin() {
        if (chiTiet == null || chiTiet.isEmpty()) return 0.0;
        return chiTiet.stream()
                .mapToDouble(CTHangHoa::getGia)
                .min()
                .orElse(0.0);
    }

    // ===== CONSTRUCTORS =====
    public HangHoa() {}

    // ===== GETTERS & SETTERS =====
    public Long getMahang() { return mahang; }
    public void setMahang(Long mahang) { this.mahang = mahang; }

    public String getTenhang() { return tenhang; }
    public void setTenhang(String tenhang) { this.tenhang = tenhang; }

    public String getHinhanh() { return hinhanh; }
    public void setHinhanh(String hinhanh) { this.hinhanh = hinhanh; }

    public String getMota() { return mota; }
    public void setMota(String mota) { this.mota = mota; }

    public BigDecimal getBasePrice() { return basePrice; }
    public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }

    public BigDecimal getDiscountPrice() { return discountPrice; }
    public void setDiscountPrice(BigDecimal discountPrice) { this.discountPrice = discountPrice; }

    public boolean isFeatured() { return isFeatured; }
    public void setFeatured(boolean featured) { isFeatured = featured; }

    public LoaiHang getLoaiHang() { return loaiHang; }
    public void setLoaiHang(LoaiHang loaiHang) { this.loaiHang = loaiHang; }

    public List<CTHangHoa> getChiTiet() { return chiTiet; }
    public void setChiTiet(List<CTHangHoa> chiTiet) { this.chiTiet = chiTiet; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
