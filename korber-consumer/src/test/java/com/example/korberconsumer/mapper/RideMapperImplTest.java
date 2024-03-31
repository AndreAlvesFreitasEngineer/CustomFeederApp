package com.example.korberconsumer.mapper;

import com.example.korberconsumer.model.LocationEntity;
import com.example.korberconsumer.model.RideEntity;
import com.example.korberconsumer.model.dto.LocationDto;
import com.example.korberconsumer.model.dto.RideDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RideMapperImplTest {

    @InjectMocks
    private RideMapperImpl rideMapper;

    @Test
    void toDto_shouldConvertRideEntityToRideDto() {
        // Setup test data
        RideEntity rideEntity = new RideEntity();
        rideEntity.setStartDate("2024-03-25");
        rideEntity.setEndDate("2024-03-30");
        rideEntity.setPrice(120.0);
        rideEntity.setImportantPlaces(List.of(createLocationEntity(37.7749, -122.4194, "Golden Gate Bridge")));

        // Call the method to test
        RideDto rideDto = rideMapper.toDto(rideEntity);

        // Assert the expected results
        assertAll(() -> {
            assertEquals(rideEntity.getStartDate(), rideDto.getStartDate());
            assertEquals(rideEntity.getEndDate(), rideDto.getEndDate());
            assertEquals(rideEntity.getPrice(), rideDto.getPrice());
            assertEquals(rideEntity.getImportantPlaces().size(), rideDto.getImportantPlaces().size());

            LocationDto locationDto = rideDto.getImportantPlaces().get(0);
            LocationEntity locationEntity = rideEntity.getImportantPlaces().get(0);
            assertEquals(locationEntity.getLatitude(), locationDto.getLatitude());
            assertEquals(locationEntity.getLongitude(), locationDto.getLongitude());
            assertEquals(locationEntity.getPlace(), locationDto.getPlace());
        });
    }

    @Test
    void toLocationEntity_shouldConvertLocationDtoToLocationEntity() {
        // Setup test data
        LocationDto locationDto = new LocationDto(37.7749, -122.4194, "Golden Gate Bridge");

        // Call the method to test
        LocationEntity locationEntity = rideMapper.toLocationEntity(locationDto);

        // Assert the expected results
        assertEquals(locationDto.getLatitude(), locationEntity.getLatitude());
        assertEquals(locationDto.getLongitude(), locationEntity.getLongitude());
        assertEquals(locationDto.getPlace(), locationEntity.getPlace());
    }


    @Test
    public void testToDto() {
        // Mocking data
        RideEntity rideEntity = new RideEntity();
        rideEntity.setStartDate("2024-03-31");
        rideEntity.setEndDate("2024-04-01");
        rideEntity.setPrice(25.0);
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLatitude(37.7749);
        locationEntity.setLongitude(-122.4194);
        locationEntity.setPlace("San Francisco");
        List<LocationEntity> importantPlaces = new ArrayList<>();
        importantPlaces.add(locationEntity);
        rideEntity.setImportantPlaces(importantPlaces);

        // Test method
        RideDto rideDto = rideMapper.toDto(rideEntity);

        // Assertions
        assertEquals("2024-03-31", rideDto.getStartDate());
        assertEquals("2024-04-01", rideDto.getEndDate());
        assertEquals(25.0, rideDto.getPrice());
        assertEquals(1, rideDto.getImportantPlaces().size());
        assertEquals("San Francisco", rideDto.getImportantPlaces().get(0).getPlace());
    }

    public LocationEntity createLocationEntity(double latitude, double longitude, String place) {
        return new LocationEntity(1L, latitude, longitude, place);
    }
}