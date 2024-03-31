package com.example.korberconsumer.mapper;

import com.example.korberconsumer.model.LocationEntity;
import com.example.korberconsumer.model.RideEntity;
import com.example.korberconsumer.model.dto.LocationDto;
import com.example.korberconsumer.model.dto.RideDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RideMapperImpl implements RideMapper{

    // Autowiring ObjectMapper to handle JSON conversion
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Converts a JSON string representation of a ride to a RideEntity object.
     *
     * @param ride JSON string representation of a ride
     * @return RideEntity object representing the ride
     */
    @Override
    public RideEntity toEntity(String ride) {
        // Convert JSON string to RideDto object
        RideDto rideDto = convertToRideDto(ride);

        // Create a new RideEntity and set its attributes based on RideDto
        RideEntity rideEntity = new RideEntity();
        rideEntity.setStartDate(rideDto.getStartDate());
        rideEntity.setEndDate(rideDto.getEndDate());
        rideEntity.setPrice(rideDto.getPrice());

        // Convert list of LocationDto to list of LocationEntity
        List<LocationEntity> locationEntities = rideDto.getImportantPlaces().stream()
                .map(this::toLocationEntity)
                .collect(Collectors.toList());
        rideEntity.setImportantPlaces(locationEntities);
        return rideEntity;
    }

    /**
     * Converts a RideEntity object to a RideDto object.
     *
     * @param ride RideEntity object to be converted
     * @return RideDto object representing the ride
     */
    @Override
    public RideDto toDto(RideEntity ride){
        // Create a new RideDto and set its attributes based on RideEntity
        RideDto rideDto = new RideDto();
        rideDto.setStartDate(ride.getStartDate());
        rideDto.setEndDate(ride.getEndDate());
        rideDto.setPrice(ride.getPrice());

        // Convert list of LocationEntity to list of LocationDto
        List<LocationDto> locationDtos = ride.getImportantPlaces().stream()
                .map(location -> {
                    LocationDto locationDto = new LocationDto();
                    locationDto.setLatitude(location.getLatitude());
                    locationDto.setLongitude(location.getLongitude());
                    locationDto.setPlace(location.getPlace());
                    return locationDto;
                })
                .collect(Collectors.toList());
        rideDto.setImportantPlaces(locationDtos);
        return rideDto;
    }

    /**
     * Converts a LocationDto object to a LocationEntity object.
     *
     * @param location LocationDto object to be converted
     * @return LocationEntity object representing the location
     */
    public LocationEntity toLocationEntity(LocationDto location) {
        // Create a new LocationEntity and set its attributes based on LocationDto
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLatitude(location.getLatitude());
        locationEntity.setLongitude(location.getLongitude());
        locationEntity.setPlace(location.getPlace());
        return locationEntity;
    }

    /**
     * Converts a JSON string representation of a ride to a RideDto object.
     *
     * @param message JSON string representation of a ride
     * @return RideDto object representing the ride
     */
    public RideDto convertToRideDto(String message) {
        RideDto rideDto = null;
        try {
            // Convert JSON string to RideDto object using ObjectMapper
            rideDto = objectMapper.readValue(message, RideDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rideDto;
    }

}