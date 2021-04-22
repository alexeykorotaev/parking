package org.home.parking.info;

import org.home.parking.sensor.SensorService;
import org.home.parking.sensor.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InfoTableServiceImpl implements InfoTableService {
    private final SensorService sensorService;

    @Autowired
    public InfoTableServiceImpl(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    private Long getNumberOfFreePlaces() {
        return sensorService.getAllSensors().stream()
                .filter(sensor -> sensor.getState() == State.FREE)
                .count();
    }

    @Override
    public InfoTable getInfoTable() {
        return new InfoTable(getNumberOfFreePlaces());
    }
}
