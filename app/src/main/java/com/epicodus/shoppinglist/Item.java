package com.epicodus.shoppinglist;


public class Item {
    private String mName;
    private String mItemId;
    private double mSalePrice;
    private String mLongDescription;
    private String mMediumImage;
    private double mCustomerRating;
    private String mStock;
    private String mOfferType;

    public Item(String name,
                String itemId,
                double salePrice,
                String longDescription,
                String mediumImage,
                double customerRating,
                String stock,
                String offerType) {

        this.mName = name;
        this.mItemId = itemId;
        this.mSalePrice = salePrice;
        this.mLongDescription = longDescription;
        this.mMediumImage = mediumImage;
        this.mCustomerRating = customerRating;
        this.mStock = stock;
        this.mOfferType = offerType;
    }

    public String getName() {
        return mName;
    }

    public String getItemId() {
        return mItemId;
    }

    public double getSalePrice() {
        return mSalePrice;
    }

    public String getLongDescription() {
        return mLongDescription;
    }

    public String getMediumImage() {
        return mMediumImage;
    }

    public double getCustomerRating() {
        return mCustomerRating;
    }

    public String getStock() {
        return mStock;
    }

    public String getOfferType() {
        return mOfferType;
    }
}
