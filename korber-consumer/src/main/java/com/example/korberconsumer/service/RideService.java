package com.example.korberconsumer.service;

import com.example.korberconsumer.mapper.RideMapper;
import com.example.korberconsumer.model.RideEntity;
import com.example.korberconsumer.model.dto.CustomPage;
import com.example.korberconsumer.model.dto.RideDto;
import com.example.korberconsumer.repository.RideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RideService {

    // Logger instance for logging
    private static final Logger LOGGER = LoggerFactory.getLogger(RideService.class);

    // RideRepository for database operations
    private final RideRepository rideRepository;

    // RideMapper for mapping between entities and DTOs
    private final RideMapper rideMapper;

    // Constructor injection of RideRepository and RideMapper
    @Autowired
    public RideService(RideRepository rideRepository, RideMapper rideMapper) {
        this.rideRepository = rideRepository;
        this.rideMapper = rideMapper;
    }

    /**
     * Retrieves a page of rides within the specified price range.
     *
     * @param minPrice Minimum price of the ride
     * @param maxPrice Maximum price of the ride
     * @param pageable Pageable object for pagination
     * @return Page containing RideDto objects within the specified price range
     */
    public Page<RideDto> findRidesByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
        // Log the start of the method
        LOGGER.info("Finding rides by price range: minPrice={}, maxPrice={}, pageable={}", minPrice, maxPrice, pageable);

        // Find rides from the repository within the specified price range
        Page<RideEntity> rideEntities = rideRepository.findByPriceBetween(minPrice, maxPrice, pageable);

        // Map RideEntity objects to RideDto objects using RideMapper and return the page
        return rideEntities.map(rideMapper::toDto);
    }

    /**
     * Processes a ride message received from Kafka.
     *
     * @param ride JSON string representation of a ride
     */
    public void processRide(String ride) {
        // Log the start of the method and the received message
        LOGGER.info("Processing ride message: {}", ride);

        // Convert the ride message to a RideEntity object using RideMapper
        RideEntity rideEntity = rideMapper.toEntity(ride);
        // Save the RideEntity object to the database using RideRepository
        rideRepository.save(rideEntity);

        // Log the successful processing of the message
        LOGGER.info("Ride message processed successfully");
    }

}
