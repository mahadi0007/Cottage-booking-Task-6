package com.example;

public class RequestParams {
    private String name;
    private int noOfPeople;
    private int bedroomCount;
    private int maxLakeDistance;
    private String city;
    private int maxCityDistance;
    private int dayCount;
    private String startDate;
    private String maxDayShifts;
    private String isAvailableFrom;
    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public int getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(int bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public int getMaxLakeDistance() {
        return maxLakeDistance;
    }

    public void setMaxLakeDistance(int maxLakeDistance) {
        this.maxLakeDistance = maxLakeDistance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMaxCityDistance() {
        return maxCityDistance;
    }

    public void setMaxCityDistance(int maxCityDistance) {
        this.maxCityDistance = maxCityDistance;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getMaxDayShifts() {
        return maxDayShifts;
    }

    public void setMaxDayShifts(String maxDayShifts) {
        this.maxDayShifts = maxDayShifts;
    }
    
    public String getisAvailableFrom() {
        return isAvailableFrom;
    }

    public void setisAvailableFrom(String isAvailableFrom) {
        this.isAvailableFrom = isAvailableFrom;
    }
    public String getimgUrl() {
        return imgUrl;
    }

    public void setimgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
