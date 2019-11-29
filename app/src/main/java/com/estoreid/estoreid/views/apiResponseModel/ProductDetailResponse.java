package com.estoreid.estoreid.views.apiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailResponse {

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
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("brand_id")
        @Expose
        private Integer brandId;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("sale_price")
        @Expose
        private Integer salePrice;
        @SerializedName("actual_price")
        @Expose
        private Integer actualPrice;
        @SerializedName("discount")
        @Expose
        private Integer discount;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("product_cat_id")
        @Expose
        private Integer productCatId;
        @SerializedName("color")
        @Expose
        private List<Color> color = null;
        @SerializedName("size")
        @Expose
        private List<Size> size = null;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("images")
        @Expose
        private List<Image> images = null;

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

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public Integer getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(Integer salePrice) {
            this.salePrice = salePrice;
        }

        public Integer getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(Integer actualPrice) {
            this.actualPrice = actualPrice;
        }

        public Integer getDiscount() {
            return discount;
        }

        public void setDiscount(Integer discount) {
            this.discount = discount;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getProductCatId() {
            return productCatId;
        }

        public void setProductCatId(Integer productCatId) {
            this.productCatId = productCatId;
        }

        public List<Color> getColor() {
            return color;
        }

        public void setColor(List<Color> color) {
            this.color = color;
        }

        public List<Size> getSize() {
            return size;
        }

        public void setSize(List<Size> size) {
            this.size = size;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }


        public class Color {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("image")
            @Expose
            private String image;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

        }

        public class Image {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("image")
            @Expose
            private String image;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

        }

        public class Size {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("description")
            @Expose
            private String description;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

        }
    }
}