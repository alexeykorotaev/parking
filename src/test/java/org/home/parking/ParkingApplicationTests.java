package org.home.parking;

import org.home.parking.info.InfoTableController;
import org.home.parking.info.InfoTableModelAssembler;
import org.home.parking.info.InfoTableService;
import org.home.parking.info.InfoTableServiceImpl;
import org.home.parking.sensor.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingApplicationTests {

	@Test
	void givenListOfSensors_whenGetNumberOfFreePlaces_thenReturnCorrectNumber() {
		SensorRepository sensorRepository = Mockito.mock(SensorRepository.class);
		Mockito.when(sensorRepository.findAll())
				.thenReturn(Arrays.asList(new Sensor(State.FREE), new Sensor(State.OCCUPIED)));

		SensorService sensorService = new SensorServiceImpl(sensorRepository);
		InfoTableService infoTableService = new InfoTableServiceImpl(sensorService);
		InfoTableController controller = new InfoTableController(infoTableService, new InfoTableModelAssembler());

		assertThat(Objects.requireNonNull(controller.getInfoTable().getContent()).getNumberOfFreePlaces())
				.isEqualTo(1L);
	}
}
