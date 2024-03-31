package com.data.korber.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class AddRideCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    public Location start;
    public Location end;
    public List<Location> importantPlaces;
    public String startDate;
    public String endDate;
    public double price;

    public AddRideCommand() { }

    @JsonCreator
    public AddRideCommand(@JsonProperty("start") Location start,
                          @JsonProperty("end") Location end,
                          @JsonProperty("important_places") List<Location> importantPlaces,
                          @JsonProperty("start_date") String startDate,
                          @JsonProperty("end_date") String endDate,
                          @JsonProperty("price") double price) {
        this.start = start;
        this.end = end;
        this.importantPlaces = importantPlaces;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public static class Location implements Serializable {
        public double latitude;
        public double longitude;
        public String place;

        public Location() {}

        @JsonCreator
        public Location(@JsonProperty("latitude") double latitude,
                        @JsonProperty("longitude") double longitude,
                        @JsonProperty("place") String place) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.place = place;
        }
    }
}
