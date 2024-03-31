package com.example.korberconsumer.model.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomPage<T> {

    private double minPrice;
    private double maxPrice;
    private int pageSize;
    private List<T> content;

    public CustomPage() {
    }

    // Constructor
    public CustomPage(double minPrice, double maxPrice, int pageSize, List<T> content) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.pageSize = pageSize;
        this.content = content;
    }

    // Getters and setters
    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    // Map method to transform each element in the content list
    public <R> CustomPage<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mappedContent = this.content.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new CustomPage<>(this.minPrice, this.maxPrice, this.pageSize, mappedContent);
    }

}
