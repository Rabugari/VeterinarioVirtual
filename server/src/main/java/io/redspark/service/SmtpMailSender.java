package io.redspark.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.redspark.controller.MailController;
import io.redspark.domain.Animal;
import io.redspark.domain.Owner;
import io.redspark.domain.Vaccine;
import io.redspark.domain.VaccineSchedule;
import io.redspark.repository.VaccineScheduleRepository;

/**
 * Serviço para enviar e-mails. Lembrando a vacinação registrada
 * 
 * @author massao
 */
@Component
public class SmtpMailSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private VaccineScheduleRepository vaccineScheduleRepository;

	/**
	 * Método executado toda 00:00, que enviará um e-mail para consultas de
	 * vacinas nos próximos 3 dias
	 */
	@Scheduled(cron = "0 0 * * * *")
	public void cronJob() {
		final List<VaccineSchedule> vaccines = (List<VaccineSchedule>) vaccineScheduleRepository.findAll();
		vaccines.stream().filter(vaccine -> Period.between(vaccine.getScheduleDate(), LocalDate.now()).getDays() < 3)
				.forEach(vaccine -> {
					try {
						sendEmail(vaccine);
					} catch (MessagingException e) {
						LOGGER.error("Erro ao enviar e-mail", e);
					}
				});
	}

	/**
	 * Constrói o e-mail de acordo com os dados registrados
	 * 
	 * @param vaccineSchedule
	 * @return
	 * @throws MessagingException
	 */
	private VaccineSchedule sendEmail(final VaccineSchedule vaccineSchedule) throws MessagingException {
		final DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		final Vaccine vaccine = vaccineSchedule.getVaccine();
		final Animal animal = vaccineSchedule.getAnimal();
		final Owner owner = animal.getOwner();

		StringBuilder body = new StringBuilder();
		body.append("Olá " + owner.getName());
		body.append(" está chegando a hora de vacinar o(a) " + animal.getName() + " com a vacina " + vaccine.getName()
				+ ".");
		body.append("\n Fique atento. Data de vacinação: " + dtFormat.format(vaccineSchedule.getScheduleDate()));

		send(owner.getEmail(), "Lembrete: Dia de vacinação chegando. Vacine o(a) " + animal.getName(), body.toString());

		return vaccineSchedule;
	}

	public void send(final String to, final String subject, final String body) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		javaMailSender.send(message);
	}
}
