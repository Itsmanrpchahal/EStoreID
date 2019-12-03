package com.estoreid.estoreid.views.apiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavVendorsResponse {

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

        @SerializedName("vendor_id")
        @Expose
        private Integer vendorId;
        @SerializedName("business_name")
        @Expose
        private String businessName;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("time_from")
        @Expose
        private String timeFrom;
        @SerializedName("time_to")
        @Expose
        private String timeTo;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("total_number_ratings")
        @Expose
        private Integer totalNumberRatings;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTimeFrom() {
            return timeFrom;
        }

        public void setTimeFrom(String timeFrom) {
            this.timeFrom = timeFrom;
        }

        public String getTimeTo() {
            return timeTo;
        }

        public void setTimeTo(String timeTo) {
            this.timeTo = timeTo;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public Integer getTotalNumberRatings() {
            return totalNumberRatings;
        }

        public void setTotalNumberRatings(Integer totalNumberRatings) {
            this.totalNumberRatings = totalNumberRatings;
        }

    }
}