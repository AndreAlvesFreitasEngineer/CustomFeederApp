package com.example.korberconsumer.messaging;

import com.example.korberconsumer.service.RideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;



@Component
public class KafkaConsumer {

    // Logger instance for logging
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    // Reference to RideService for processing Kafka messages
    private RideService rideService;

    // Maximum number of retry attempts
    private static final int MAX_RETRIES = 3;

    // Constructor injection of RideService
    @Autowired
    public KafkaConsumer(RideService rideService) {
        this.rideService = rideService;
    }

    /**
     * Kafka listener method to consume messages from the "data_processor" topic.
     *
     * @param message Kafka message to be consumed
     */
    @KafkaListener(topics = "data_processor")
    public void consume(String message) {
        // Log the received message
        LOGGER.info("Received message: {}", message);

        try {
            // Attempt to process the message
            rideService.processRide(message);
        } catch (Exception e) {
            // Log the error and retry processing the message
            LOGGER.error("Error processing message: {}", message, e);
            retryAfterDelay(message, 1); // Start retry attempt 1
        }
    }

    /**
     * Method to retry processing the message after a delay in case of failure.
     *
     * @param message      Kafka message to be retried
     * @param retryAttempt Current retry attempt number
     */
    private void retryAfterDelay(String message, int retryAttempt) {
        // Check if maximum retry attempts exceeded
        if (retryAttempt > MAX_RETRIES) {
            LOGGER.error("Max retries reached. Unable to process message: {}", message);
            return;
        }

        // Sleep for a few minutes before retrying
        try {
            TimeUnit.MINUTES.sleep(5); // Retry after 5 minutes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Retry sleep interrupted", e);
        }

        // Retry processing the message
        try {
            rideService.processRide(message);
        } catch (Exception ex) {
            // Log the retry failure and retry with the next attempt
            LOGGER.error("Retry attempt {} failed. Unable to process message: {}", retryAttempt, message, ex);
            retryAfterDelay(message, retryAttempt + 1);
        }
    }
}