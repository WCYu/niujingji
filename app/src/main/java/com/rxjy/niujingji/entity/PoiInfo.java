package com.rxjy.niujingji.entity;

import java.util.List;

/**
 * Created by AAA on 2017/9/12.
 */

public class PoiInfo {

    private int status;
    private String message;
    private List<SiteInfo> results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SiteInfo> getResults() {
        return results;
    }

    public void setResults(List<SiteInfo> results) {
        this.results = results;
    }

    public static class SiteInfo {

        private String name;
        private LocationBean location;
        private String address;
        private int detail;
        private String uid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getDetail() {
            return detail;
        }

        public void setDetail(int detail) {
            this.detail = detail;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public static class LocationBean {
            /**
             * lat : 39.924097
             * lng : 116.456935
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }
    }
}
