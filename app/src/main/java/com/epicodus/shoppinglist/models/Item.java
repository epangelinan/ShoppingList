package com.epicodus.shoppinglist.models;

import org.parceler.Parcel;

@Parcel
public class Item {
    private String name;
    private String itemId;
    private double salePrice;
    private String shortDescription;
    private String mediumImage;
    private String stock;
    private String offerType;
    private String addToCartUrl;
    private String index;

    public Item() {}

    public Item(String name,
                String itemId,
                double salePrice,
                String shortDescription,
                String mediumImage,
                String stock,
                String offerType,
                String addToCartUrl) {

        this.name = name;
        this.itemId = itemId;
        this.salePrice = salePrice;
        this.shortDescription = shortDescription;
        this.mediumImage = mediumImage;
        this.stock = stock;
        this.offerType = offerType;
        this.addToCartUrl = addToCartUrl;
        this.index = "not_specified";
    }

    public String getName() {
        return name;
    }

    public String getItemId() {
        return itemId;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public String getStock() {
        return stock;
    }

    public String getOfferType() {
        return offerType;
    }

    public String getAddToCartUrl() {
        return addToCartUrl;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
