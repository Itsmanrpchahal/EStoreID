package com.estoreid.estoreid.views.apiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VendorAPIResponse {

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

        public String getFollow_status() {
            return follow_status;
        }

        public void setFollow_status(String follow_status) {
            this.follow_status = follow_status;
        }

        @SerializedName("follow_status")
        @Expose
        private String follow_status;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        @SerializedName("vendor_id")
        @Expose
        private String vendor_id;
        @SerializedName("distance")
        @Expose
        private Double distance;

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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

    }
}