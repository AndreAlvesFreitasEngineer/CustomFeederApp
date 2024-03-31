package com.data.korber.schedule;
import com.data.korber.messaging.JsonFileReaderService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class PythonScriptSchedulerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileReaderService.class);

    @PostConstruct
    public void schedulePythonScriptExecution() {
        // Create a scheduled executor service
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // Define the task to run Python script
        Runnable pythonScriptTask = () -> {
            try {
                LOGGER.info("Start Generate File");
                // Execute Python script
                Process process = Runtime.getRuntime().exec("python PythonCode.py");
                // You can add more code to handle the process if needed
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        // Schedule the task to run every 10 minutes (6 times an hour)
        executorService.scheduleAtFixedRate(pythonScriptTask, 0, 10, TimeUnit.MINUTES);
    }
}