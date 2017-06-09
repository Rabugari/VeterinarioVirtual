package io.redspark;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Period;

import org.junit.Before;
import org.junit.Test;

import io.redspark.domain.VaccineSchedule;

public class ScheduleVaccineTest {

	VaccineSchedule vaccine1;
	VaccineSchedule vaccine2;

	@Before
	public void instacia() {
		vaccine1 = new VaccineSchedule();
		vaccine2 = new VaccineSchedule();

		vaccine1.setScheduleDate(LocalDate.now());
		vaccine2.setScheduleDate(LocalDate.now().plusDays(3));
	}

	@Test
	public void verificaDiferencaDeDias() {
		int days = Period.between(vaccine1.getScheduleDate(), vaccine2.getScheduleDate()).getDays();
		assertEquals(3, days);

	}

}
