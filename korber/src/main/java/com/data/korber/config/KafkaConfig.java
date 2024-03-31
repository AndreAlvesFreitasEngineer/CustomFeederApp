package com.data.korber.config;
import com.data.korber.command.AddRideCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    // Kafka bootstrap servers configuration
    private static final String BOOTSTRAP_SERVERS = "localhost:29092";

    /**
     * Configures a KafkaTemplate for sending messages to Kafka.
     *
     * @return KafkaTemplate instance
     */
    @Bean
    public KafkaTemplate<String, AddRideCommand> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Configures a ProducerFactory for creating Kafka producers.
     *
     * @return ProducerFactory instance
     */
    @Bean
    public ProducerFactory<String, AddRideCommand> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }
}


