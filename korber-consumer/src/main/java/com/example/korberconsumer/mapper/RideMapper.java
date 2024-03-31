package com.example.korberconsumer.mapper;

import com.example.korberconsumer.model.RideEntity;
import com.example.korberconsumer.model.dto.RideDto;
import org.mapstruct.Mapper;

@Mapper
public interface RideMapper {

    /**
     * Maps a string representation of a ride to a RideEntity object.
     *
     * @param ride String representation of a ride
     * @return RideEntity object representing the ride
     */
    RideEntity toEntity(String ride);

    /**
     * Maps a RideEntity object to a RideDto object.
     *
     * @param ride RideEntity object to be mapped
     * @return RideDto object representing the ride
     */
    RideDto toDto(RideEntity ride);
}
