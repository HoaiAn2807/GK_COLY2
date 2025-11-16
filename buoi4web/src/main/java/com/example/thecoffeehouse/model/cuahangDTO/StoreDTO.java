package com.example.thecoffeehouse.model.cuahangDTO;

public class StoreDTO {
    private Integer id;
    private String name;
    private String address;
    private String hours;
    private String imageUrl;
    private String mapUrl;

    public StoreDTO() {}

    public StoreDTO(Integer id, String name, String address, String hours, String imageUrl, String mapUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hours = hours;
        this.imageUrl = imageUrl;
        this.mapUrl = mapUrl;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getHours() { return hours; }
    public void setHours(String hours) { this.hours = hours; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getMapUrl() { return mapUrl; }
    public void setMapUrl(String mapUrl) { this.mapUrl = mapUrl; }
}
