package com.example.korberconsumer.model;

import jakarta.persistence.*;
import org.hibernate.annotations.IndexColumn;

import java.util.List;


@Entity
@Table(name = "Ride", indexes = {@Index(name = "idx_price",  columnList="price")})
public class RideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LocationEntity> importantPlaces;

    private String startDate;
    private String endDate;

    private double price;

    public RideEntity() {
    }

    public RideEntity(Long id, List<LocationEntity> importantPlaces, String startDate, String endDate, double price) {
        this.id = id;
        this.importantPlaces = importantPlaces;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LocationEntity> getImportantPlaces() {
        return importantPlaces;
    }

    public void setImportantPlaces(List<LocationEntity> importantPlaces) {
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
