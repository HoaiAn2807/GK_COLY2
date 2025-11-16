package com.example.thecoffeehouse.repository;
import com.example.thecoffeehouse.model.HangHoa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<HangHoa, Integer> {

    // Lấy sản phẩm mới nhất (sắp xếp theo mã hàng hóa giảm dần)
    @Query("SELECT h FROM HangHoa h ORDER BY h.id DESC")
    List<HangHoa> findLatest(Pageable pageable);

    // Lấy sản phẩm khuyến mãi (giamgia > 0)
    @Query("SELECT h FROM HangHoa h WHERE h.discountPrice > 0 ORDER BY h.discountPrice DESC")
    List<HangHoa> findSale(Pageable pageable);
}

