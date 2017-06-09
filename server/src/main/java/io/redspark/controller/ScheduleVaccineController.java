package io.redspark.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.redspark.domain.Animal;
import io.redspark.domain.Vaccine;
import io.redspark.domain.VaccineSchedule;
import io.redspark.service.AnimalService;
import io.redspark.service.ScheduleService;
import io.redspark.service.VaccineService;

@Controller
@RequestMapping(ControllerConstants.SCHEDULE_VACCINE)
public class ScheduleVaccineController {

	private final ModelAndView MODEL_AND_VIEW = new ModelAndView("redirect:/" + ControllerConstants.SCHEDULE_VACCINE);

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private AnimalService animalService;

	@Autowired
	private VaccineService vacineService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getVaccineSchedule(final VaccineSchedule vaccineSchedule) {
		final List<VaccineSchedule> vaccinesSchedule = scheduleService.findAllVaccineSchedule();
		final List<Animal> animals = animalService.findAll();
		final List<Vaccine> vaccines = vacineService.findAll();

		ModelAndView modelAndView = new ModelAndView("agenda-vacina");
		modelAndView.addObject("vaccinesSchedule", vaccinesSchedule);
		modelAndView.addObject("vaccineSchedule", vaccineSchedule);
		modelAndView.addObject("animals", animals);
		modelAndView.addObject("vaccines", vaccines);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveVaccineSchedule(final @Valid VaccineSchedule consultaVacina,
			final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Erro ao agendar vacina");
			return MODEL_AND_VIEW;
		}

		scheduleService.schedule(consultaVacina);

		redirectAttributes.addFlashAttribute("success", "Sucesso ao agendar vacina");
		return MODEL_AND_VIEW;
	}

	@RequestMapping("/remove/{id}")
	public ModelAndView removeVaccineSchedule(final @PathVariable("id") Long id,
			final RedirectAttributes redirectAttributes) {
		VaccineSchedule consultaVacina = scheduleService.findOneVaccineSchedule(id);

		if (consultaVacina != null)
			scheduleService.deleteVaccineSchedule(id);

		redirectAttributes.addFlashAttribute("success", "Consulta removido com sucesso");
		return MODEL_AND_VIEW;
	}
}
