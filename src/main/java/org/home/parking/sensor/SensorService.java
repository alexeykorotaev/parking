package org.home.parking.sensor;

import org.home.parking.sensor.exceptions.SensorNotFoundException;

import java.util.List;

public interface SensorService {
    List<Sensor> getAllSensors();
    Sensor createNewSensor(Sensor sensor);
    Sensor getSensor(Long id) throws SensorNotFoundException;
    Sensor replaceSensor(Sensor newSensor, Long id);
    void deleteSensor(Long id);
}
