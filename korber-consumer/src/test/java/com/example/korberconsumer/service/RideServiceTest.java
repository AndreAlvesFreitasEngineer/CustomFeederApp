package com.example.korberconsumer.service;

import com.example.korberconsumer.mapper.RideMapper;
import com.example.korberconsumer.model.RideEntity;
import com.example.korberconsumer.model.dto.CustomPage;
import com.example.korberconsumer.model.dto.RideDto;
import com.example.korberconsumer.repository.RideRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RideServiceTest {

    @InjectMocks
    private RideService rideService;

    @Mock
    private RideRepository rideRepository;

    @Mock
    private RideMapper rideMapper;

    @Test
    void findRidesByPriceRangeTest() {
        double minPrice = 10.0;
        double maxPrice = 50.0;
        Pageable pageable = PageRequest.of(0, 5);

        List<RideEntity> content = new ArrayList<>();
        Page<RideEntity> customPage = new PageImpl<RideEntity>(content, pageable, 5);

        when(rideRepository.findByPriceBetween(minPrice, maxPrice, pageable))
                .thenReturn(customPage);

        // Call the method under test
        rideService.findRidesByPriceRange(minPrice, maxPrice, pageable);

        // Verify the interactions with the mock objects
        verify(rideRepository, times(1)).findByPriceBetween(minPrice, maxPrice, pageable);
        verifyNoMoreInteractions(rideRepository);
    }


    @Test
    public void testProcessRide() {
        // Mocking mapper response
        RideEntity mockRideEntity = new RideEntity();
        when(rideMapper.toEntity(any(String.class))).thenReturn(mockRideEntity);

        // Call the method
        rideService.processRide("test_ride");

        // Verify that the mapper method was called with the correct parameter
        verify(rideMapper).toEntity("test_ride");

        // Verify that the repository method was called to save the entity
        verify(rideRepository).save(mockRideEntity);
    }
}