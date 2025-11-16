package com.example.thecoffeehouse.service;

import com.example.thecoffeehouse.model.BaiViet;
import com.example.thecoffeehouse.repository.BaiVietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BaiVietService {

    @Autowired
    private BaiVietRepository baiVietRepository;

    // ✅ Lấy tất cả bài viết (cũ)
    public List<BaiViet> getAllBaiViet() {
        return baiVietRepository.findAll();
    }

    // ✅ Lọc theo chủ đề (cũ)
    public List<BaiViet> getBaiVietByChude(String chude) {
        return baiVietRepository.findByChude(chude);
    }

    // ✅ Lấy tất cả bài viết có phân trang (mới)
    public Page<BaiViet> getAllBaiViet(Pageable pageable) {
        return baiVietRepository.findAll(pageable);
    }


    // ✅ Lọc theo chủ đề có phân trang (mới)
    public Page<BaiViet> getBaiVietByChude(String chude, Pageable pageable) {
        return baiVietRepository.findByChude(chude, pageable);
    }

    // ✅ Thêm bài viết
    public BaiViet save(BaiViet baiViet) {
        return baiVietRepository.save(baiViet);
    }

    // ✅ Xóa bài viết
    public void delete(Long id) {
        baiVietRepository.deleteById(id);
    }
}
