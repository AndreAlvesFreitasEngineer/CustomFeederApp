package com.example.korberconsumer.controller;


import com.example.korberconsumer.model.dto.RideDto;
import com.example.korberconsumer.service.RideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RideController {

    // Autowiring RideService to access its methods
    @Autowired
    private RideService rideService;

    /**
     * Retrieves a page of rides within the specified price range.
     *
     * @param minPrice Minimum price of the ride
     * @param maxPrice Maximum price of the ride
     * @param page     Page number for pagination (default: 0)
     * @param size     Page size for pagination (default: 10)
     * @return ResponseEntity containing a page of RideDto objects
     */
    @GetMapping("v1/api/rides")
    @Operation(summary = "Get rides by price range", description = "Get rides from the database based on a price range")
    public ResponseEntity<Page<RideDto>> getRidesByPriceRange(
            @Parameter(description = "Minimum price") @RequestParam("minPrice") double minPrice,
            @Parameter(description = "Maximum price") @RequestParam("maxPrice") double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Creating PageRequest for pagination
        PageRequest pageable = PageRequest.of(page, size);

        // Fetching rides within the specified price range using RideService
        Page<RideDto> rides = rideService.findRidesByPriceRange(minPrice, maxPrice, pageable);

        // Returning the fetched rides as ResponseEntity
        return ResponseEntity.ok(rides);
    }
}