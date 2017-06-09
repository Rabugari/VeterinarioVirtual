package io.redspark.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.redspark.domain.VeterinarySchedule;
import io.redspark.service.AnimalService;
import io.redspark.service.ScheduleService;
import io.redspark.service.VeterinaryService;

@Controller
@RequestMapping(ControllerConstants.SCHEDULE_VETERINARY)
public class ScheduleVeterinaryController {

	private final ModelAndView MODEL_AND_VIEW = new ModelAndView(
			"redirect:/" + ControllerConstants.SCHEDULE_VETERINARY);

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private AnimalService animalService;

	@Autowired
	private VeterinaryService veterinaryService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getVeterinarySchedule(final VeterinarySchedule veterinarySchedule) {

		ModelAndView modelVeterinarySchedule = new ModelAndView("agenda-veterinario");
		modelVeterinarySchedule.addObject("agendaConsultas", scheduleService.findAllVeterinarySchedule());
		modelVeterinarySchedule.addObject("veterinarySchedule", veterinarySchedule);
		modelVeterinarySchedule.addObject("veterinaries", veterinaryService.findAll());
		modelVeterinarySchedule.addObject("animals", animalService.findAll());
		return modelVeterinarySchedule;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveVeterinarySchedule(final @Valid VeterinarySchedule consultaVacina,
			final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Erro ao agendar consulta");
			return MODEL_AND_VIEW;
		}

		scheduleService.schedule(consultaVacina);

		redirectAttributes.addFlashAttribute("success", "Sucesso ao agendar consulta");
		return MODEL_AND_VIEW;
	}

	@RequestMapping("/remove/{id}")
	public ModelAndView removeVeterinarySchedule(final @PathVariable("id") Long id,
			final RedirectAttributes redirectAttributes) {
		VeterinarySchedule consultaVeterinario = scheduleService.findOneVeterinarySchedule(id);

		if (consultaVeterinario != null)
			scheduleService.deleteVeterinarySchedule(id);

		redirectAttributes.addFlashAttribute("success", "Consulta removida com sucesso");
		return MODEL_AND_VIEW;
	}
}
