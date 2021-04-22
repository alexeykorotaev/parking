package org.home.parking.sensor;

import org.home.parking.sensor.exceptions.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {
    private final SensorRepository repository;

    @Autowired
    public SensorServiceImpl(SensorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Sensor> getAllSensors() {
        return repository.findAll();
    }

    @Override
    public Sensor createNewSensor(Sensor sensor) {
        return repository.save(sensor);
    }

    @Override
    public Sensor getSensor(Long id) throws SensorNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new SensorNotFoundException(id));
    }

    @Override
    public Sensor replaceSensor(Sensor newSensor, Long id) {
        return repository.findById(id)
                .map(sensor -> {
                    sensor.setState(newSensor.getState());
                    return repository.save(sensor);
                })
                .orElseGet(() -> {
                    newSensor.setId(id);
                    return repository.save(newSensor);
                });
    }

    @Override
    public void deleteSensor(Long id) {
        repository.deleteById(id);
    }
}
