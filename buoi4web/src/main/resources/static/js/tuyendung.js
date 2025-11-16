document.addEventListener("DOMContentLoaded", () => {
    const dropdownBtn = document.getElementById("dropdownMenuButton");
    const dropdownList = document.getElementById("dropdown-list");
    const searchInput = document.getElementById("location-search");
    const options = document.querySelectorAll("#location-options li");

    if (!dropdownBtn || !dropdownList) return;

    // Hiện/ẩn dropdown
    dropdownBtn.addEventListener("click", (e) => {
        e.stopPropagation();
        dropdownList.classList.toggle("show");
    });

    // Chọn địa điểm
    window.selectLocation = (value) => {
        dropdownBtn.querySelector("span").textContent = value;
        dropdownList.classList.remove("show");
    };

    // Tìm kiếm trong danh sách
    searchInput.addEventListener("keyup", () => {
        const filter = searchInput.value.toLowerCase();
        options.forEach(option => {
            const text = option.textContent.toLowerCase();
            option.style.display = text.includes(filter) ? "" : "none";
        });
    });

    // Ẩn dropdown khi click ra ngoài
    document.addEventListener("click", (e) => {
        if (!dropdownList.contains(e.target) && !dropdownBtn.contains(e.target)) {
            dropdownList.classList.remove("show");
        }
    });
});
