package com.example.korberconsumer.model.dto;

import java.util.List;

public class RideDto {
    private LocationDto start;
    private LocationDto end;
    private List<LocationDto> importantPlaces;
    private String startDate;
    private String endDate;
    private double price;

    public RideDto() {
    }

    public RideDto(LocationDto start, LocationDto end, List<LocationDto> importantPlaces, String startDate, String endDate, double price) {
        this.start = start;
        this.end = end;
        this.importantPlaces = importantPlaces;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public LocationDto getStart() {
        return start;
    }

    public void setStart(LocationDto start) {
        this.start = start;
    }

    public LocationDto getEnd() {
        return end;
    }

    public void setEnd(LocationDto end) {
        this.end = end;
    }

    public List<LocationDto> getImportantPlaces() {
        return importantPlaces;
    }

    public void setImportantPlaces(List<LocationDto> importantPlaces) {
        this.importantPlaces = importantPlaces;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}