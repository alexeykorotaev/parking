package org.home.parking;

import org.home.parking.sensor.Sensor;
import org.home.parking.sensor.SensorRepository;
import org.home.parking.sensor.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SensorRepository sensorRepository) {
        return args -> {
            sensorRepository.save(new Sensor(State.FREE));
            sensorRepository.save(new Sensor(State.FREE));
            sensorRepository.save(new Sensor(State.OCCUPIED));

            sensorRepository.findAll().forEach(sensor -> logger.info("Preloaded {}", sensor));
        };
    }
}
