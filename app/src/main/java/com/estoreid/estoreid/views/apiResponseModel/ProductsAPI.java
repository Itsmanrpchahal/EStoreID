package com.estoreid.estoreid.views.apiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsAPI {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("vendor_id")
        @Expose
        private Integer vendorId;
        @SerializedName("business_name")
        @Expose
        private String businessName;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("brand_id")
        @Expose
        private Integer brandId;
        @SerializedName("brand_name")
        @Expose
        private String brandName;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("sale_price")
        @Expose
        private String salePrice;
        @SerializedName("actual_price")
        @Expose
        private String actualPrice;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("image")
        @Expose
        private String image;

        public String getWish_status() {
            return wish_status;
        }

        public void setWish_status(String wish_status) {
            this.wish_status = wish_status;
        }

        @SerializedName("wish_status")
        @Expose
        private String wish_status;

        public String getCart_status() {
            return cart_status;
        }

        public void setCart_status(String cart_status) {
            this.cart_status = cart_status;
        }

        @SerializedName("cart_status")
        @Expose
        private String cart_status;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getVendorId() {
            return vendorId;
        }

        public void setVendorId(Integer vendorId) {
            this.vendorId = vendorId;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Integer getBrandId() {
            return brandId;
        }

        public void setBrandId(Integer brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(String salePrice) {
            this.salePrice = salePrice;
        }

        public String getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(String actualPrice) {
            this.actualPrice = actualPrice;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
}