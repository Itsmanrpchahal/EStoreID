package com.estoreid.estoreid.views.apiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartItemsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("cart")
        @Expose
        private List<Cart> cart = null;
        @SerializedName("price")
        @Expose
        private Price price;
        @SerializedName("related")
        @Expose
        private List<Related> related = null;

        public List<Cart> getCart() {
            return cart;
        }

        public void setCart(List<Cart> cart) {
            this.cart = cart;
        }

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public List<Related> getRelated() {
            return related;
        }

        public void setRelated(List<Related> related) {
            this.related = related;
        }

        public class Cart {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("cart_id")
            @Expose
            private Integer cartId;
            @SerializedName("product_name")
            @Expose
            private String productName;
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
            private String color;
            @SerializedName("size")
            @Expose
            private String size;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("selected_color_image")
            @Expose
            private String selectedColorImage;
            @SerializedName("selected_color")
            @Expose
            private String selectedColor;
            @SerializedName("selected_color_id")
            @Expose
            private Integer selectedColorId;
            @SerializedName("selected_size")
            @Expose
            private String selectedSize;
            @SerializedName("selected_size_id")
            @Expose
            private Integer selectedSizeId;
            @SerializedName("quantity")
            @Expose
            private Integer quantity;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("colors")
            @Expose
            private List<Color> colors = null;
            @SerializedName("sizes")
            @Expose
            private List<Size> sizes = null;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getCartId() {
                return cartId;
            }

            public void setCartId(Integer cartId) {
                this.cartId = cartId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
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

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getSelectedColorImage() {
                return selectedColorImage;
            }

            public void setSelectedColorImage(String selectedColorImage) {
                this.selectedColorImage = selectedColorImage;
            }

            public String getSelectedColor() {
                return selectedColor;
            }

            public void setSelectedColor(String selectedColor) {
                this.selectedColor = selectedColor;
            }

            public Integer getSelectedColorId() {
                return selectedColorId;
            }

            public void setSelectedColorId(Integer selectedColorId) {
                this.selectedColorId = selectedColorId;
            }

            public String getSelectedSize() {
                return selectedSize;
            }

            public void setSelectedSize(String selectedSize) {
                this.selectedSize = selectedSize;
            }

            public Integer getSelectedSizeId() {
                return selectedSizeId;
            }

            public void setSelectedSizeId(Integer selectedSizeId) {
                this.selectedSizeId = selectedSizeId;
            }

            public Integer getQuantity() {
                return quantity;
            }

            public void setQuantity(Integer quantity) {
                this.quantity = quantity;
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

            public List<Color> getColors() {
                return colors;
            }

            public void setColors(List<Color> colors) {
                this.colors = colors;
            }

            public List<Size> getSizes() {
                return sizes;
            }

            public void setSizes(List<Size> sizes) {
                this.sizes = sizes;
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

        public class Price {

            @SerializedName("sub_total")
            @Expose
            private Integer subTotal;
            @SerializedName("tax_amount")
            @Expose
            private String taxAmount;
            @SerializedName("delivery")
            @Expose
            private Integer delivery;
            @SerializedName("total")
            @Expose
            private String total;

            public Integer getSubTotal() {
                return subTotal;
            }

            public void setSubTotal(Integer subTotal) {
                this.subTotal = subTotal;
            }

            public String getTaxAmount() {
                return taxAmount;
            }

            public void setTaxAmount(String taxAmount) {
                this.taxAmount = taxAmount;
            }

            public Integer getDelivery() {
                return delivery;
            }

            public void setDelivery(Integer delivery) {
                this.delivery = delivery;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

        }

        public class Related {

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
            @SerializedName("cat_id")
            @Expose
            private Integer catId;
            @SerializedName("sub_cat_id")
            @Expose
            private Integer subCatId;
            @SerializedName("color")
            @Expose
            private String color;
                @SerializedName("size")
            @Expose
            private String size;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("business_name")
            @Expose
            private String businessName;
            @SerializedName("brand_name")
            @Expose
            private String brandName;

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

            public Integer getCatId() {
                return catId;
            }

            public void setCatId(Integer catId) {
                this.catId = catId;
            }

            public Integer getSubCatId() {
                return subCatId;
            }

            public void setSubCatId(Integer subCatId) {
                this.subCatId = subCatId;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
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

            public String getBusinessName() {
                return businessName;
            }

            public void setBusinessName(String businessName) {
                this.businessName = businessName;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

        }
    }
}