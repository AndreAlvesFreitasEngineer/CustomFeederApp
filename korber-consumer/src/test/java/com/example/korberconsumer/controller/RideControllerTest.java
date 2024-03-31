package com.example.korberconsumer.controller;

import com.example.korberconsumer.model.dto.CustomPage;
import com.example.korberconsumer.model.dto.RideDto;
import com.example.korberconsumer.service.RideService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RideController.class)
public class RideControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RideService rideService;

    @Test
    public void testGetRidesByPriceRange() throws Exception {
        // Mocking service response
        Page<RideDto> mockRidesPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
        when(rideService.findRidesByPriceRange(10.0, 20.0, PageRequest.of(0, 10))).thenReturn(mockRidesPage);

        // Performing GET request and asserting response
        mockMvc.perform(get("/v1/api/rides")
                        .param("minPrice", "10.0")
                        .param("maxPrice", "20.0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content").isEmpty());
    }
}