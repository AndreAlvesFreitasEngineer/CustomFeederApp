package com.example.korberconsumer.repository;

import com.example.korberconsumer.model.RideEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<RideEntity, Long> {

    /**
     * Retrieves a page of rides within the specified price range.
     *
     * @param minPrice Minimum price of the ride
     * @param maxPrice Maximum price of the ride
     * @param pageable Pageable object for pagination
     * @return Page containing RideEntity objects within the specified price range
     */
    Page<RideEntity> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable);
}
