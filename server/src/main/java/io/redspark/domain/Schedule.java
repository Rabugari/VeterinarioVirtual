package io.redspark.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe base para agendar vacinas {@link VaccineSchedule} e consultas
 * {@link VeterinarySchedule}
 * 
 * @author massao
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDate scheduleDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
}
