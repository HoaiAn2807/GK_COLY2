// Định nghĩa các biến toàn cục
const API_BASE_URL = '/api/stores'; // <-- Đảm bảo dòng này ĐÚNG
let activeRegionId = null;
let activeRegionName = '';

// Lấy các phần tử DOM
const regionListEl = document.getElementById('region-list');
const districtFilterEl = document.getElementById('district-filter');
const storeListEl = document.getElementById('store-list');
const mainTitleEl = document.getElementById('main-title');
const noStoresMessageEl = document.getElementById('no-stores-message');

/**
 * Hàm khởi tạo, chạy khi trang được tải
 */
async function init() {
    try {
        console.log("Hàm init() bắt đầu."); // Thêm log
        const regions = await fetchApi('/regions');
        console.log("Đã lấy regions:", regions); // Thêm log
        populateRegionList(regions);

        // Tự động chọn khu vực đầu tiên
        if (regions.length > 0) {
            const firstRegion = regions[0];
            console.log("Tự động chọn khu vực đầu tiên:", firstRegion.name); // Thêm log
            // Cần lấy phần tử 'a' đầu tiên để truyền vào handleRegionClick
            const firstRegionElement = regionListEl.querySelector('a');
            if (firstRegionElement) {
                await handleRegionClick(firstRegion.id, firstRegion.name, firstRegionElement);
            } else {
                console.error("Không tìm thấy phần tử 'a' đầu tiên trong region list.");
            }
        }
    } catch (error) {
        console.error("Lỗi khi khởi tạo:", error);
        mainTitleEl.textContent = "Không thể tải dữ liệu khu vực.";
    }

    // Thêm sự kiện cho bộ lọc quận
    districtFilterEl.addEventListener('change', handleDistrictChange);
    console.log("Đã thêm event listener cho district filter."); // Thêm log
}

/**
 * Hàm gọi API chung
 */
async function fetchApi(endpoint) {
    // Đảm bảo URL được ghép đúng: API_BASE_URL + endpoint
    const fullUrl = `${API_BASE_URL}${endpoint}`;
    console.log("Đang gọi API:", fullUrl); // Log để kiểm tra

    const response = await fetch(fullUrl); // Sử dụng fullUrl đã ghép
    if (!response.ok) {
        // Ném lỗi với thông tin chi tiết hơn
        throw new Error(`Lỗi API (${response.status}): ${response.statusText} khi gọi ${fullUrl}`);
    }
    // Kiểm tra xem phản hồi có phải JSON không trước khi parse
    const contentType = response.headers.get("content-type");
    if (contentType && contentType.indexOf("application/json") !== -1) {
        return response.json();
    } else {
        // Nếu không phải JSON, trả về text để tránh lỗi parse
        console.warn(`Phản hồi từ ${fullUrl} không phải JSON.`);
        return response.text();
    }
}

/**
 * Hiển thị danh sách khu vực (cột trái)
 */
function populateRegionList(regions) {
    regionListEl.innerHTML = ''; // Xóa nội dung "Đang tải..."
    if (!regions || regions.length === 0) {
        regionListEl.innerHTML = '<p class="text-secondary px-2">Không có khu vực nào.</p>';
        return;
    }
    regions.forEach(region => {
        const a = document.createElement('a');
        a.href = '#';
        a.textContent = region.name;
        a.className = 'list-group-item list-group-item-action border-0 px-2'; // Thêm class Bootstrap

        // Thêm sự kiện click
        a.addEventListener('click', (e) => {
            e.preventDefault();
            console.log(`Đã click vào khu vực: ${region.name} (ID: ${region.id})`); // Thêm log
            handleRegionClick(region.id, region.name, a);
        });

        regionListEl.appendChild(a);
    });
    console.log("Đã hiển thị danh sách khu vực."); // Thêm log
}

/**
 * Xử lý khi người dùng nhấp vào một khu vực
 */
async function handleRegionClick(regionId, regionName, clickedElement) {
    // Cập nhật trạng thái
    activeRegionId = regionId;
    activeRegionName = regionName;

    // Cập nhật giao diện (highlight)
    document.querySelectorAll('#region-list a').forEach(a => a.classList.remove('active'));
    clickedElement.classList.add('active');

    // Cập nhật tiêu đề chính
    mainTitleEl.textContent = `Khám phá cửa hàng ở ${activeRegionName}`;
    console.log(`Đang tải dữ liệu cho khu vực: ${regionName}`); // Thêm log

    // Tải danh sách quận/huyện cho khu vực này
    await loadDistricts(regionId);

    // Tải tất cả cửa hàng cho khu vực này (bằng cách chọn "Tất cả Quận/Huyện")
    // Đặt districtFilterEl.value = "0" trước khi gọi handleDistrictChange
    districtFilterEl.value = "0";
    await handleDistrictChange();
    console.log(`Đã hoàn tất tải dữ liệu ban đầu cho khu vực: ${regionName}`); // Thêm log
}

/**
 * Tải và hiển thị danh sách quận/huyện vào dropdown
 */
async function loadDistricts(regionId) {
    districtFilterEl.innerHTML = '<option value="0">Tất cả Quận/Huyện</option>'; // Reset
    try {
        console.log(`Đang gọi API districts cho regionId=${regionId}...`); // Thêm log
        const districts = await fetchApi(`/districts?regionId=${regionId}`);
        console.log(`Đã nhận districts cho regionId=${regionId}:`, districts); // Thêm log
        if (districts && districts.length > 0) {
            districts.forEach(district => {
                const option = document.createElement('option');
                option.value = district.id;
                option.textContent = district.name;
                districtFilterEl.appendChild(option);
            });
        }
        console.log("Đã hiển thị danh sách quận/huyện."); // Thêm log
    } catch (error) {
        console.error(`Lỗi khi tải quận/huyện cho regionId=${regionId}:`, error);
    }
}

/**
 * Xử lý khi người dùng thay đổi giá trị trong dropdown quận/huyện
 */
async function handleDistrictChange() {
    const selectedDistrictId = districtFilterEl.value;
    console.log("Dropdown quận/huyện thay đổi, giá trị mới:", selectedDistrictId); // Thêm log

    // Hiển thị loading indicator (nếu có)
    storeListEl.innerHTML = '<p class="text-center text-secondary">Đang tải cửa hàng...</p>';
    noStoresMessageEl.classList.add('d-none');

    try {
        if (selectedDistrictId === "0") {
            // "Tất cả Quận/Huyện" được chọn
            console.log("Đang lấy ID của tất cả các quận hiển thị..."); // Thêm log
            const options = Array.from(districtFilterEl.options);
            const districtIds = options.filter(opt => opt.value !== "0").map(opt => opt.value);
            console.log("IDs của các quận:", districtIds); // Thêm log

            if (districtIds.length === 0) {
                console.log("Không có quận nào để tải cửa hàng."); // Thêm log
                populateStoreList([]); // Hiển thị không có cửa hàng
                return;
            }

            console.log("Bắt đầu gọi API song song cho các cửa hàng..."); // Thêm log
            // SỬA LỖI Ở ĐÂY: Chỉ truyền query string, không truyền '/stores'
            const storePromises = districtIds.map(id => fetchApi(`?districtId=${id}`));
            const storesByDistrict = await Promise.all(storePromises);
            const allStores = storesByDistrict.flat(); // Gộp các mảng cửa hàng lại
            console.log("Đã nhận tất cả cửa hàng (gộp lại):", allStores); // Thêm log
            populateStoreList(allStores);

        } else {
            // Một quận cụ thể được chọn
            console.log(`Đang gọi API cho districtId=${selectedDistrictId}...`); // Thêm log
            // SỬA LỖI Ở ĐÂY: Chỉ truyền query string, không truyền '/stores'
            const stores = await fetchApi(`?districtId=${selectedDistrictId}`);
            console.log(`Đã nhận stores cho districtId=${selectedDistrictId}:`, stores); // Thêm log
            populateStoreList(stores);
        }
    } catch (error) {
        console.error("Lỗi khi tải danh sách cửa hàng:", error);
        storeListEl.innerHTML = '<p class="text-center text-danger">Không thể tải danh sách cửa hàng.</p>';
        noStoresMessageEl.classList.add('d-none');
    }
}

/**
 * Hiển thị danh sách cửa hàng (cột phải)
 */
function populateStoreList(stores) {
    console.log("Bắt đầu hiển thị stores:", stores ? stores.length : 0, "cửa hàng"); // Thêm log
    storeListEl.innerHTML = ''; // Xóa danh sách cũ hoặc thông báo "Đang tải..."

    if (!stores || stores.length === 0) {
        console.log("Không có cửa hàng nào để hiển thị."); // Thêm log
        noStoresMessageEl.classList.remove('d-none'); // Hiển thị thông báo không có cửa hàng
    } else {
        noStoresMessageEl.classList.add('d-none'); // Ẩn thông báo không có cửa hàng
        stores.forEach(store => {
            const cardWrapper = createStoreCard(store);
            storeListEl.appendChild(cardWrapper);
        });
        console.log(`Đã hiển thị ${stores.length} cửa hàng.`); // Thêm log
    }

    // Cập nhật lại số lượng cửa hàng trên tiêu đề
    const storeCount = stores ? stores.length : 0;
    mainTitleEl.textContent = `Khám phá ${storeCount} cửa hàng ở ${activeRegionName}`;
}

/**
 * Hàm trợ giúp tạo HTML cho một thẻ cửa hàng
 */
function createStoreCard(store) {
    // Tạo thẻ bọc ngoài cho cột của grid
    const colWrapper = document.createElement('div');
    colWrapper.className = "col"; // Bootstrap sẽ tự xử lý responsive qua .row-cols-*

    // Tạo HTML cho các tiện ích, kiểm tra amenities có tồn tại không
    const amenitiesHtml = store.amenities && store.amenities.length > 0
        ? store.amenities.map(amenity => `
            <div class="d-flex align-items-center text-sm text-secondary">
                <i class="${amenity.icon ? amenity.icon : 'fas fa-check'} text-brand" style="width: 24px;"></i>
                <span>${amenity.name ? amenity.name : 'Tiện ích'}</span>
            </div>
        `).join('')
        : '<p class="text-sm text-secondary">Không có thông tin tiện ích.</p>'; // Thông báo nếu không có tiện ích

    // Tạo nội dung card
    colWrapper.innerHTML = `
        <div class="card h-100 shadow-sm border-0 rounded-3 overflow-hidden transition-all duration-300 hover-shadow-lg">
            <a href="${store.mapUrl || '#'}" target="_blank" rel="noopener noreferrer">
                <img src="${store.imageUrl || 'https://placehold.co/600x400/EFEFEF/AAAAAA?text=No+Image'}" alt="${store.name || 'Cửa hàng'}" class="card-img-top" style="height: 200px; object-fit: cover;"
                     onerror="this.onerror=null; this.src='https://placehold.co/600x400/EFEFEF/AAAAAA?text=Image+Error';">
            </a>
            <div class="card-body p-4">
                <h3 class="fw-bold fs-5 mb-3 text-gray-900">${store.name || 'Chưa có tên'}</h3>

                <a href="${store.mapUrl || '#'}" target="_blank" rel="noopener noreferrer" class="btn btn-map btn-sm py-2 px-3 mb-4 rounded-2 ${!store.mapUrl ? 'disabled' : ''}">
                    Xem bản đồ
                </a>

                <div class="text-sm d-grid gap-3">
                    <p class="text-secondary d-flex align-items-start mb-0">
                        <i class="fas fa-map-marker-alt text-brand pt-1" style="width: 20px;"></i>
                        <span>${store.address || 'Chưa có địa chỉ'}</span>
                    </p>
                    <p class="text-secondary d-flex align-items-center mb-0">
                        <i class="fas fa-clock text-brand" style="width: 20px;"></i>
                        <span>${store.hours || 'Chưa có giờ mở cửa'}</span>
                    </p>
                </div>

                <hr class="my-4">

                <div class="d-grid gap-2">
                    ${amenitiesHtml}
                </div>
            </div>
        </div>
    `;
    return colWrapper;
}

// Chạy hàm khởi tạo khi DOM đã tải xong
document.addEventListener('DOMContentLoaded', init);

