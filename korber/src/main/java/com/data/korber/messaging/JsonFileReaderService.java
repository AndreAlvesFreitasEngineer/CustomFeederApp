package com.data.korber.messaging;
import com.data.korber.command.AddRideCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@Service
public class JsonFileReaderService {

    // Logger for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileReaderService.class);

    // KafkaTemplate for publishing messages to Kafka
    @Autowired
    private KafkaTemplate<String, AddRideCommand> kafkaTemplate;

    // Path to the JSON file
    private final String filePath = "taxi_records.json";

    // Scheduled task to monitor the directory and trigger ingestion when the file is present
    @Scheduled(fixedDelay = 60000) // Check every 60 seconds
    public void monitorDirectoryAndIngestData() {
        Path file = Paths.get(filePath);
        if (Files.exists(file)) {
            // If the file exists, read data from the file and publish to Kafka
            readDataFromFileAndPublishToKafka(filePath, "data_processor");
        }
    }

    /**
     * Reads data from a JSON file and publishes it to Kafka.
     *
     * @param filePath Path to the JSON file
     * @param topic    Kafka topic to publish the data to
     */
    public void readDataFromFileAndPublishToKafka(String filePath, String topic) {
        try {
            LOGGER.info("Data ingestion started successfully.");
            // Read JSON file
            byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
            // Parse JSON array into a list of AddRideCommand objects
            List<AddRideCommand> commands = Arrays.asList(new ObjectMapper().readValue(jsonData, AddRideCommand[].class));
            // Publish each command to Kafka topic
            commands.forEach(command -> kafkaTemplate.send(topic, command));
            // Delete the file after ingestion
            Files.deleteIfExists(Paths.get(filePath));
            LOGGER.info("Data ingestion completed successfully.");
        } catch (IOException e) {
            // Handle file read error
            LOGGER.error("Error reading JSON file: {}", e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            LOGGER.error("Error ingesting data to Kafka: {}", e.getMessage());
        }
    }
}