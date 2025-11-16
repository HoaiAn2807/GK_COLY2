package com.example.thecoffeehouse.repository;

import com.example.thecoffeehouse.model.CTHangHoa;
import com.example.thecoffeehouse.model.CTHangHoaId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CTHangHoaRepository extends JpaRepository<CTHangHoa, CTHangHoaId> {
    List<CTHangHoa> findByHangHoa_Mahang(Long mahang);
}
