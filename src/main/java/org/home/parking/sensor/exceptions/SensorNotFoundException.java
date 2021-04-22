package org.home.parking.sensor.exceptions;

public class SensorNotFoundException extends RuntimeException {
    public SensorNotFoundException(Long id) {
        super("Could not found sensor with id = " + id);
    }
}
