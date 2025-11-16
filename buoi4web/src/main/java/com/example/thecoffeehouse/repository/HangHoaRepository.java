package com.example.thecoffeehouse.repository;

import com.example.thecoffeehouse.model.HangHoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HangHoaRepository extends JpaRepository<HangHoa, Long> {

    /**
     * 📂 Lấy danh sách sản phẩm theo mã loại hàng
     */
    List<HangHoa> findByLoaiHang_Maloai(Long maloai);

    /**
     * 🕒 Lấy 8 sản phẩm mới nhất (sắp xếp theo ngày tạo giảm dần)
     */
    List<HangHoa> findTop8ByOrderByCreatedAtDesc();

    /**
     * 💸 Lấy sản phẩm đang có giá khuyến mãi (discountPrice khác null)
     */
    List<HangHoa> findByDiscountPriceNotNull();
}
