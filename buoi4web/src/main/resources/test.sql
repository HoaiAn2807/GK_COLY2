-- ==========================================
-- ☕ DATABASE: THE COFFEE HOUSE (CÓ SIZE + MÔ TẢ ĐẦY ĐỦ)
-- ==========================================
CREATE DATABASE test CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE test;

-- ==========================================
-- 🧩 BẢNG LOẠI HÀNG (PHÂN CẤP CHA - CON)
-- ==========================================
CREATE TABLE loaihang (
  maloai    BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenloai   VARCHAR(100) NOT NULL,
  parent_id BIGINT DEFAULT NULL,
  FOREIGN KEY (parent_id) REFERENCES loaihang(maloai)
);

-- ==========================================
-- 🍶 BẢNG SIZE (SIZE NƯỚC)
-- ==========================================
CREATE TABLE size_nuoc (
  idsize  INT PRIMARY KEY AUTO_INCREMENT,
  tensize VARCHAR(50) NOT NULL,
  mota    VARCHAR(100)
);

-- ==========================================
-- 🍰 BẢNG HÀNG HÓA
-- ==========================================
CREATE TABLE hanghoa (
  mahang  BIGINT PRIMARY KEY AUTO_INCREMENT,
  tenhang VARCHAR(100) NOT NULL,
  hinhanh VARCHAR(255),
  mota    VARCHAR(500),
  maloai  BIGINT,
  FOREIGN KEY (maloai) REFERENCES loaihang(maloai)
);

-- ==========================================
-- 💧 BẢNG CHI TIẾT HÀNG HÓA (SIZE + GIÁ)
-- ==========================================
CREATE TABLE cthanghoa (
  mahang  BIGINT NOT NULL,
  idsize  INT NOT NULL,
  gia     DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (mahang, idsize),
  FOREIGN KEY (mahang) REFERENCES hanghoa(mahang),
  FOREIGN KEY (idsize) REFERENCES size_nuoc(idsize)
);

-- ==========================================
-- ☕ LOẠI CHA
-- ==========================================
INSERT INTO loaihang (tenloai, parent_id) VALUES
('Cà Phê', NULL),
('A-Mê', NULL),
('Trà Trái Cây', NULL),
('Trà Sữa', NULL),
('Matcha', NULL),
('Chocolate', NULL),
('Frappe', NULL),
('Bánh & Snack', NULL);

-- ==========================================
-- 🪜 LOẠI CON
-- ==========================================
INSERT INTO loaihang (tenloai, parent_id) VALUES
('Cà Phê Máy', 1),
('Cà Phê Việt Nam', 1),
('A-Mê Classic', 2),
('A-Mê Đào', 2),
('A-Mê Mơ', 2),
('Trà Đào Cam Sả', 3),
('Trà Sữa Oolong', 4),
('Trà Sữa Hồng Trà', 4),
('Matcha Latte', 5),
('Matcha Yến Mạch', 5),
('Chocolate Đá', 6),
('Chocolate Nóng', 6),
('Frappe', 7),
('Caramel Đá Xay', 7),
('Bánh Mặn', 8),
('Bánh Ngọt', 8),
('Bánh Pastry', 8),
('Cà Phê Tại Nhà', 1);

-- ==========================================
-- 🧃 SIZE MẶC ĐỊNH
-- ==========================================
INSERT INTO size_nuoc (tensize, mota) VALUES
('Nhỏ', 'Ly nhỏ 250ml'),
('Vừa', 'Ly vừa 350ml'),
('Lớn', 'Ly lớn 500ml');

-- ==========================================
-- 🍵 DỮ LIỆU HÀNG HÓA (ĐẦY ĐỦ)
-- ==========================================
INSERT INTO hanghoa (tenhang, hinhanh, mota, maloai) VALUES
-- ☕ Cà Phê Máy
('Espresso Đá', 'espresso_da.jpg.png', 'Hương vị cà phê đậm đà, chiết xuất từ máy espresso chuẩn Ý.', 9),
('Americano Nóng', 'americano_nong.jpg.png', 'Cà phê espresso pha loãng bằng nước nóng, vị dịu nhẹ.', 9),

-- ☕ Cà Phê Việt Nam
('Bạc Xỉu Foam Dừa', 'bacxiu_foamdua.jpg.png', 'Cà phê sữa truyền thống kết hợp lớp foam dừa béo mịn hấp dẫn.', 10),
('Bạc Xỉu Caramel Muối', 'bacxiu_caramelmuoi.jpg.png', 'Hương caramel mặn ngọt quyện cùng cà phê sữa đậm đà.', 10),
('Bạc Xỉu', 'bacxiu.jpg.png', 'Thức uống quen thuộc với hương vị sữa ngọt ngào và cà phê nhẹ nhàng.', 10),

-- 🍑 A-MÊ
('A-Mê Classic', 'ame_classic.jpg.png', 'Trà hoa quả đặc trưng, vị chua nhẹ và thanh mát tự nhiên.', 11),
('A-Mê Đào', 'ame_dao.jpg.png', 'Vị đào thơm ngọt hòa quyện trong nền trà đậm đà, mát lạnh.', 12),
('A-Mê Mơ', 'ame_mo.jpg.png', 'Vị mơ thanh mát kết hợp cùng trà đen mang lại hương vị tinh tế.', 13),

-- 🍑 TRÀ TRÁI CÂY
('Trà Đào Cam Sả - Nóng', 'tra_dao_cam_sa_nong.jpg.png', 'Thức uống giải nhiệt kết hợp vị đào, cam và hương sả.', 14),
('Trà Đào Cam Sả - Đá', 'tra_dao_cam_sa_da.jpg.png', 'Phiên bản đá tươi mát, vị chua ngọt tự nhiên.', 14),

-- 🧋 TRÀ SỮA
('Trà Sữa Oolong Nướng Sương Sáo', 'trasua_oolong_nuong.jpg.png', 'Trà sữa oolong nướng đậm đà, sương sáo thanh mát.', 15),
('Trà Sữa Oolong Tứ Quý Sương Sáo', 'trasua_oolong_tuquy.jpg.png', 'Trà sữa oolong thơm béo cùng topping sương sáo đặc trưng.', 15),
('Hồng Trà Sữa Nóng', 'trasua_hongtra_nong.jpg.png', 'Hồng trà sữa thơm béo, uống nóng cực ngon.', 16),

-- 🍵 MATCHA
('Matcha Latte Tây Bắc', 'matcha_latte_tb.jpg.png', 'Matcha nguyên chất kết hợp sữa tươi thanh béo.', 17),
('Matcha Latte Tây Bắc (Nóng)', 'matcha_latte_tb_nong.jpg.png', 'Phiên bản nóng giúp cảm nhận rõ vị matcha nguyên bản.', 17),
('Matcha Latte Yến Mạch', 'matcha_latte_yenmach.jpg.png', 'Sự kết hợp mới lạ giữa matcha và sữa yến mạch bổ dưỡng.', 18),

-- 🍫 CHOCOLATE
('Chocolate Đá', 'choco_da.jpg.png', 'Thức uống chocolate mát lạnh, đậm đà vị cacao nguyên chất.', 19),
('Chocolate Nóng', 'choco_nong.jpg.png', 'Cốc chocolate nóng thơm lừng, phù hợp cho ngày se lạnh.', 20),
('Chocolate Mint', 'choco_da.jpg.png', 'Chocolate bạc hà tạo cảm giác mát lạnh sảng khoái.', 19),

-- ❄️ FRAPPE
('Floaty Vanilla Mocha', 'frappe_vanilla_mocha.jpg.png', 'Cà phê mocha xay đá cùng kem vanilla thơm béo.', 21),
('Floaty Bạc Xỉu', 'frappe_bacxiu.jpg.png', 'Phiên bản đá xay của bạc xỉu với lớp kem sữa mịn màng.', 21),
('Floaty Matcha Latte', 'frappe_matcha.jpg.png', 'Matcha xay đá hòa quyện cùng sữa tươi mát lạnh.', 22),

-- 🍞 BÁNH & SNACK
('Butter Croissant', 'banhman_croissant.jpg.png', 'Croissant bơ giòn xốp, thơm vị bơ châu Âu.', 23),
('Bánh Mì Que Bò Nấm Xốt Bơ', 'banhman_bonam.jpg.png', 'Bánh mì que giòn, nhân bò nấm đậm vị.', 23),
('Bánh Mì Que Chà Bông Phô Mai Bơ Cay', 'banhman_chabong.jpg.png', 'Kết hợp vị phô mai béo và chà bông thơm ngon.', 23),
('Butter Croissant Sữa Đặc', 'banhngot_croissant_suada.jpg.png', 'Croissant mềm mịn, sữa đặc ngọt ngào.', 24),
('Matcha Burnt Cheesecake', 'banhngot_matcha_cheesecake.jpg.png', 'Cheesecake vị matcha đậm đà, béo mịn.', 24),
('Burnt Cheesecake', 'banhngot_burntcheesecake.jpg.png', 'Cheesecake nướng cháy cạnh, thơm béo.', 24),
('Mini Pastry Phô Mai', 'banhpastry_phomai.jpg.png', 'Pastry phô mai nhỏ xinh, giòn rụm thơm lừng.', 25),

-- ☕ CÀ PHÊ TẠI NHÀ
('Cà Phê Đen Đá Hộp (14 gói x 16g)', 'cf_den_da_hop.jpg.png', 'Cà phê hòa tan tiện lợi, vị đậm đà như pha phin.', 26),
('Cà Phê Sữa Đá Hòa Tan Túi 25x22G', 'cf_suada_tui25x22g.jpg.png', 'Cà phê sữa hòa tan thơm ngon, dễ pha.', 26),
('Cà Phê Sữa Đá Hòa Tan (10 gói x 22g)', 'cf_suada_10goi22g.jpg.png', 'Cà phê sữa đậm đà, tiện lợi mang đi.', 26),
('Cà Phê Rang Xay Original 1 250G', 'cf_rangxay_original250g.jpg.png', 'Cà phê rang xay nguyên chất, hương Việt.', 26);

-- ==========================================
-- 💵 CHI TIẾT GIÁ THEO SIZE (FULL)
-- ==========================================
INSERT INTO cthanghoa (mahang, idsize, gia)
SELECT mahang, s.idsize,
CASE s.idsize
  WHEN 1 THEN FLOOR(RAND() * 10000 + 39000)
  WHEN 2 THEN FLOOR(RAND() * 10000 + 45000)
  WHEN 3 THEN FLOOR(RAND() * 10000 + 49000)
END
FROM hanghoa h
CROSS JOIN size_nuoc s;

-- ==========================================
-- 🔎 KIỂM TRA DỮ LIỆU
-- ==========================================
SELECT h.tenhang, s.tensize, c.gia
FROM hanghoa h
JOIN cthanghoa c ON h.mahang = c.mahang
JOIN size_nuoc s ON c.idsize = s.idsize
ORDER BY h.mahang, s.idsize;

-- ==========================================
-- 📰 BẢNG BÀI VIẾT (TIN TỨC)
-- ==========================================
CREATE TABLE baiviet (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tieude VARCHAR(255),
  ngaydang DATE,
  noidung TEXT,
  anh VARCHAR(255),
  tacgia VARCHAR(100),
  chude VARCHAR(50)
);

INSERT INTO baiviet (tieude, ngaydang, noidung, anh, tacgia, chude) VALUES
('Cà phê sữa Espresso The Coffee House – Bật lon bật vị ngon', '2023-02-09',
 'Cà phê sữa Espresso là một lon cà phê sữa giải khát với hương vị đậm đà, mang đến năng lượng tươi mới cho ngày dài năng động.',
 'nhom.jpg', 'chuyencaphe', 'Coffeeholic'),

('SIGNATURE - Biểu tượng văn hoá cà phê của The Coffee House đã quay trở lại', '2023-02-12',
 'Các “tín đồ” cà phê đang bàn tán xôn xao về sự trở lại của SIGNATURE — không gian hiện đại pha nét cổ điển, nơi cảm hứng cà phê được thăng hoa.',
 'nhom4.jpg', 'chuyencaphe', 'Coffeeholic'),

('Uống gì khi tới Signature by The Coffee House?', '2023-02-09',
 'Vừa qua, The Coffee House chính thức khai trương cửa hàng SIGNATURE – nơi thưởng thức những tách cà phê được pha chế tinh tế nhất.',
 'nhom2.jpg', 'chuyencaphe', 'Blog'),

('Câu chuyện về Trà Sen Vàng', '2023-02-14',
 'Trà Sen Vàng là sự kết hợp hài hoà giữa hương sen thanh khiết và vị trà đậm đà, mang đến cảm giác thư giãn, tươi mát.',
 'nhom3.jpg', 'chuyenvetra', 'Teaholic'),

('Pha Trà Như Một Nghệ Thuật', '2023-02-15',
 'Pha trà không chỉ là công việc, mà là nghệ thuật của sự kiên nhẫn và tinh tế trong từng động tác.',
 'nhom5.jpg', 'chuyenvetra', 'Teaholic'),
 
 ('Cà phê sữa Espresso The Coffee House – Bật lon bật vị ngon', '2023-02-09',
 'Cà phê sữa Espresso là một lon cà phê sữa giải khát với hương vị đậm đà, mang đến năng lượng tươi mới cho ngày dài năng động.',
 'nhom6.jpg', 'chuyencaphe', 'Coffeeholic'),

('SIGNATURE - Biểu tượng văn hoá cà phê của The Coffee House đã quay trở lại', '2023-02-12',
 'Các “tín đồ” cà phê đang bàn tán xôn xao về sự trở lại của SIGNATURE — không gian hiện đại pha nét cổ điển, nơi cảm hứng cà phê được thăng hoa.',
 'nhom7.jpg', 'chuyencaphe', 'Coffeeholic'),

('Uống gì khi tới Signature by The Coffee House?', '2023-02-09',
 'Vừa qua, The Coffee House chính thức khai trương cửa hàng SIGNATURE – nơi thưởng thức những tách cà phê được pha chế tinh tế nhất.',
 'nhom8.jpg', 'chuyencaphe', 'Blog'),

('Câu chuyện về Trà Sen Vàng', '2023-02-14',
 'Trà Sen Vàng là sự kết hợp hài hoà giữa hương sen thanh khiết và vị trà đậm đà, mang đến cảm giác thư giãn, tươi mát.',
 'nhom9.jpg', 'chuyenvetra', 'Teaholic'),

('Pha Trà Như Một Nghệ Thuật', '2023-02-15',
 'Pha trà không chỉ là công việc, mà là nghệ thuật của sự kiên nhẫn và tinh tế trong từng động tác.',
 'nhom10.jpg', 'chuyenvetra', 'Teaholic'),
 ('Cà phê sữa Espresso The Coffee House – Bật lon bật vị ngon', '2023-02-09',
 'Cà phê sữa Espresso là một lon cà phê sữa giải khát với hương vị đậm đà, mang đến năng lượng tươi mới cho ngày dài năng động.',
 'nhom11.jpg', 'chuyencaphe', 'Coffeeholic'),

('SIGNATURE - Biểu tượng văn hoá cà phê của The Coffee House đã quay trở lại', '2023-02-12',
 'Các “tín đồ” cà phê đang bàn tán xôn xao về sự trở lại của SIGNATURE — không gian hiện đại pha nét cổ điển, nơi cảm hứng cà phê được thăng hoa.',
 '12.jpg', 'chuyencaphe', 'Coffeeholic'),

('Uống gì khi tới Signature by The Coffee House?', '2023-02-09',
 'Vừa qua, The Coffee House chính thức khai trương cửa hàng SIGNATURE – nơi thưởng thức những tách cà phê được pha chế tinh tế nhất.',
 '13.jpg', 'chuyencaphe', 'Blog'),

('Câu chuyện về Trà Sen Vàng', '2023-02-14',
 'Trà Sen Vàng là sự kết hợp hài hoà giữa hương sen thanh khiết và vị trà đậm đà, mang đến cảm giác thư giãn, tươi mát.',
 'TD1.jpg', 'chuyenvetra', 'Teaholic'),

('Pha Trà Như Một Nghệ Thuật', '2023-02-15',
 'Pha trà không chỉ là công việc, mà là nghệ thuật của sự kiên nhẫn và tinh tế trong từng động tác.',
 'TD2.jpg', 'chuyenvetra', 'Teaholic'),
 
 ('Cà phê sữa Espresso The Coffee House – Bật lon bật vị ngon', '2023-02-09',
 'Cà phê sữa Espresso là một lon cà phê sữa giải khát với hương vị đậm đà, mang đến năng lượng tươi mới cho ngày dài năng động.',
 'nhom.jpg', 'chuyencaphe', 'Coffeeholic'),

('SIGNATURE - Biểu tượng văn hoá cà phê của The Coffee House đã quay trở lại', '2023-02-12',
 'Các “tín đồ” cà phê đang bàn tán xôn xao về sự trở lại của SIGNATURE — không gian hiện đại pha nét cổ điển, nơi cảm hứng cà phê được thăng hoa.',
 'nhom4.jpg', 'chuyencaphe', 'Coffeeholic'),

('Uống gì khi tới Signature by The Coffee House?', '2023-02-09',
 'Vừa qua, The Coffee House chính thức khai trương cửa hàng SIGNATURE – nơi thưởng thức những tách cà phê được pha chế tinh tế nhất.',
 'nhom2.jpg', 'chuyencaphe', 'Blog'),

('Câu chuyện về Trà Sen Vàng', '2023-02-14',
 'Trà Sen Vàng là sự kết hợp hài hoà giữa hương sen thanh khiết và vị trà đậm đà, mang đến cảm giác thư giãn, tươi mát.',
 'nhom3.jpg', 'chuyenvetra', 'Teaholic'),

('Pha Trà Như Một Nghệ Thuật', '2023-02-15',
 'Pha trà không chỉ là công việc, mà là nghệ thuật của sự kiên nhẫn và tinh tế trong từng động tác.',
 'nhom5.jpg', 'chuyenvetra', 'Teaholic');

-- (INSERT INTO baiviet ...)  
-- (DỮ LIỆU GIỮ NGUYÊN — QUÁ DÀI NÊN GIỮ NHƯ EM GỬI)

-- ==========================================
-- 🏪 CỬA HÀNG & TIỆN ÍCH
-- ==========================================
CREATE TABLE IF NOT EXISTS amenities (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  icon VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS regions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS districts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  region_id INT,
  FOREIGN KEY (region_id) REFERENCES regions(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS stores (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address TEXT,
  hours VARCHAR(100),
  image_url VARCHAR(255),
  map_url VARCHAR(255),
  district_id INT,
  FOREIGN KEY (district_id) REFERENCES districts(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS store_amenities (
  store_id INT NOT NULL,
  amenity_id INT NOT NULL,
  PRIMARY KEY (store_id, amenity_id),
  FOREIGN KEY (store_id) REFERENCES stores(id),
  FOREIGN KEY (amenity_id) REFERENCES amenities(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ==========================================
-- 🌐 DỮ LIỆU TIỆN ÍCH & CỬA HÀNG
-- ==========================================
INSERT INTO amenities (id, name, icon) VALUES
(1, 'Phục vụ tại chỗ', 'fa-solid fa-utensils'),
(2, 'Mua mang đi', 'fa-solid fa-bag-shopping'),
(3, 'Có chỗ đỗ xe hơi', 'fa-solid fa-car'),
(4, 'Thân thiện với gia đình', 'fa-solid fa-child-reaching')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO regions (id, name) VALUES
(1, 'TP. Hồ Chí Minh'),
(2, 'Hà Nội')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO districts (id, name, region_id) VALUES
(1, 'Quận 1', 1),
(2, 'Quận 7', 1),
(3, 'Quận 10', 1),
(4, 'Quận Thanh Xuân', 2),
(5, 'Quận Ba Đình', 2)
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO stores (id, name, address, hours, image_url, map_url, district_id) VALUES
(1, 'HCM The Grace Tower', '71 Hoàng Văn Thái, P. Tân Phú, Quận 7', '07:00 - 22:00', 
 'https://file.hstatic.net/1000075078/file/_kh_9431__1__e19a7a49963245b39b280271da3cd9fb.jpg',
 'https://maps.google.com/?q=71 Hoàng Văn Thái, Quận 7', 2),
(2, 'HCM SIGNATURE', 'TTTM Crescent Mall, 101 Tôn Dật Tiên, P. Tân Phú, Quận 7', '07:00 - 17:00',
 'https://file.hstatic.net/1000075078/file/_kh_9290_df84171506554f16b8e55bff9a6c0dd1.jpg',
 'https://maps.google.com/?q=101 Tôn Dật Tiên, Quận 7', 2),
(3, 'HCM CMT8', '569 Cách Mạng Tháng 8, P. 15, Quận 10', '07:00 - 22:30',
 'https://file.hstatic.net/1000075078/file/hcm-lu-gia1__1__e0a622da07ab48b8bb7a542f088b7233.jpg',
 'https://maps.google.com/?q=569 Cách Mạng Tháng 8, Quận 10', 3),
(4, 'Hà Nội Royal City', '72A Nguyễn Trãi, P. Thượng Đình, Q. Thanh Xuân', '08:00 - 22:00',
 'https://file.hstatic.net/1000075078/file/hn-le-thanh-nghi1_8f78c6d07364406e87d5a6672220ef40.jpg',
 'https://maps.google.com/?q=72A Nguyễn Trãi, Thanh Xuân', 4),
(5, 'Hà Nội Láng Hạ', '10 Láng Hạ, P. Thành Công, Q. Ba Đình', '07:30 - 22:30',
 'https://file.hstatic.net/1000075078/file/hn_victoria_van_phu_01_d8e2c7eab3e142cb95bb4696b0c34111.jpg',
 'https://maps.google.com/?q=10 Láng Hạ, Ba Đình', 5)
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO store_amenities (store_id, amenity_id) VALUES
(1, 1), (1, 2),
(2, 2), (2, 3), (2, 4),
(3, 1), (3, 2),
(4, 3), (4, 4),
(5, 1), (5, 2)
ON DUPLICATE KEY UPDATE store_id=store_id;

-- ==========================================
-- ✅ HOÀN THÀNH CƠ SỞ DỮ LIỆU "THE COFFEE HOUSE"
-- ==========================================
CREATE TABLE `khachhang` (
  `makh` INT NOT NULL AUTO_INCREMENT,
  `tenkh` VARCHAR(50) NOT NULL,
  `username` VARCHAR(25) NOT NULL,
  `matkhau` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `diachi` TEXT DEFAULT NULL,
  `sodienthoai` VARCHAR(12) DEFAULT NULL,
  PRIMARY KEY (`makh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `khachhang` (`makh`, `tenkh`, `username`, `matkhau`, `email`, `diachi`, `sodienthoai`) VALUES
(1, 'tú trần', 'tutran', '8f8e2909a8f683c31159ee51d593a642', 'tu@gmail.com', 'hcm', '9090789678'),
(2, 'minh minh', 'minhminh', '8f8e2909a8f683c31159ee51d593a642', 'minh@gmail.com', 'bình định', '90907896789'),
(3, 'teo', 'teoteo', '3ff19fad9f5844248f601ab23381cc88', 'teo123@gmail.com', 'hcm', '9090789698'),
(4, 'ý nhi', 'nhinhi', '87f038af05196e3dfa958a521f6f400e', 'ngvynhi.itc@gmail.com', 'thoại ngọc hầu', '9090789699'),
(5, 'an new', 'namnam', '206a93184bcd24a5625312acf1a03922', 'nam@gmail.com', 'hcm', '13245'),
(6, 'an an', 'namnamnew', 'af57f975857768de31f3c083a1c163cc', 'chautrantrucly@gmail.com', 'hcm', '13245'),
(7, 'tuấn', 'tuantuan', '206a93184bcd24a5625312acf1a03922', 'tuan@gmail.com', 'hcm', '23455'),
(8, 'thúy', 'thuybui', '206a93184bcd24a5625312acf1a03922', 'thuy@gmail.com', 'hcm', '124324234'),
(9, 'nam anh', 'namnamanh', '206a93184bcd24a5625312acf1a03922', 'namanh@gmail.com', 'hcm', '123456');