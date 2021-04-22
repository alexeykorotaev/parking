package org.home.parking.sensor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SensorModelAssembler implements RepresentationModelAssembler<Sensor, EntityModel<Sensor>> {
    @Override
    public EntityModel<Sensor> toModel(Sensor sensor) {
        EntityModel<Sensor> sensorModel = EntityModel.of(sensor,
                linkTo(methodOn(SensorController.class).getSensor(sensor.getId())).withSelfRel(),
                linkTo(methodOn(SensorController.class).getAllSensors()).withRel("sensors"));
        if (sensor.getState() == State.FREE) {
            sensorModel.add(linkTo(methodOn(SensorController.class).occupy(sensor.getId())).withRel("occupied"));
        } else if (sensor.getState() == State.OCCUPIED) {
            sensorModel.add(linkTo(methodOn(SensorController.class).free(sensor.getId())).withRel("free"));
        }

        return sensorModel;
    }
}
