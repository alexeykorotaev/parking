package org.home.parking.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SensorController {

    private final SensorService sensorService;
    private final SensorModelAssembler assembler;

    @Autowired
    public SensorController(SensorService sensorService, SensorModelAssembler assembler) {
        this.sensorService = sensorService;
        this.assembler = assembler;
    }

    @GetMapping("/sensors")
    public CollectionModel<EntityModel<Sensor>> getAllSensors() {
        List<EntityModel<Sensor>> sensors = sensorService.getAllSensors().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(sensors,
                linkTo(methodOn(SensorController.class).getAllSensors()).withSelfRel());
    }

    @PostMapping("/sensors")
    public ResponseEntity<?> createNewOne(@RequestBody Sensor sensor) {
        EntityModel<Sensor> entityModel = assembler.toModel(sensorService.createNewSensor(sensor));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/sensors/{id}")
    public EntityModel<Sensor> getSensor(@PathVariable Long id) {
        Sensor sensor = sensorService.getSensor(id);
        return assembler.toModel(sensor);
    }

    @PutMapping("/sensors/{id}")
    public ResponseEntity<?> replaceSensor(@RequestBody Sensor newSensor, @PathVariable Long id) {
        Sensor sensor = sensorService.replaceSensor(newSensor, id);
        EntityModel<Sensor> entityModel = assembler.toModel(sensor);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/sensors/{id}/occupied")
    public ResponseEntity<?> occupy(@PathVariable Long id) {
        Sensor sensor = sensorService.getSensor(id);

        if (sensor.getState() == State.FREE) {
            sensor.setState(State.OCCUPIED);
            return ResponseEntity.ok(assembler.toModel(sensorService.createNewSensor(sensor)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method now allowed")
                        .withDetail("You can't occupy parking place which is already occupied"));
    }

    @PutMapping("/sensors/{id}/free")
    public ResponseEntity<?> free(@PathVariable Long id) {
        Sensor sensor = sensorService.getSensor(id);

        if (sensor.getState() == State.OCCUPIED) {
            sensor.setState(State.FREE);
            return ResponseEntity.ok(assembler.toModel(sensorService.createNewSensor(sensor)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method now allowed")
                        .withDetail("You can't free parking place which is already free"));
    }

    @DeleteMapping("/sensors/{id}")
    public ResponseEntity<?> deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }
}
