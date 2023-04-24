package com.aprendizado.dscommerce.dto;

import com.aprendizado.dscommerce.entities.Product;
import jakarta.persistence.Column;

public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double Price;
    private String imgUrl;

    public ProductDTO(){

    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.Price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        Price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return Price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
