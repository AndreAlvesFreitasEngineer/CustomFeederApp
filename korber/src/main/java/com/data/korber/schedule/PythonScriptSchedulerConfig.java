package com.data.korber.schedule;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class PythonScriptSchedulerConfig {

    @PostConstruct
    public void schedulePythonScriptExecution() {
        // Create a scheduled executor service
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // Define the task to run Python script
        Runnable pythonScriptTask = () -> {
            try {
                // Execute Python script
                Process process = Runtime.getRuntime().exec("python PythonCode.py");
                // You can add more code to handle the process if needed
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        // Schedule the task to run every 20 minutes (3 times an hour)
        executorService.scheduleAtFixedRate(pythonScriptTask, 0, 20, TimeUnit.MINUTES);
    }
}