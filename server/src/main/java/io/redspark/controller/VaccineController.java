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

import io.redspark.domain.Vaccine;
import io.redspark.service.VaccineService;

@RequestMapping(ControllerConstants.VACCINE)
@Controller
public class VaccineController {

	private static final ModelAndView MODEL_VACINE = new ModelAndView("redirect:/" + ControllerConstants.VACCINE);
	@Autowired
	private VaccineService service;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getVaccines(final Vaccine vaccine) {
		final List<Vaccine> vaccines = service.findAll();

		ModelAndView modelAndView = new ModelAndView("vacinas");
		modelAndView.addObject("vacinas", vaccines);
		modelAndView.addObject("vaccine", vaccine);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveVaccine(final @Valid Vaccine vaccine, final BindingResult bindingResult,
			final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Erro ao salvar a vacina");
			return MODEL_VACINE;
		}
		boolean isSave = service.salvar(vaccine);

		if (!isSave)
			redirectAttributes.addFlashAttribute("error", "Vacina já cadastrada");
		else
			redirectAttributes.addFlashAttribute("success", vaccine.getName() + " cadastrado com sucesso");

		return MODEL_VACINE;
	}

	@RequestMapping(value = "/remove/{id}")
	public ModelAndView removeVaccine(final @PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
		final Vaccine vacina = service.findOne(id);
		if (vacina != null)
			service.delete(id);

		redirectAttributes.addFlashAttribute("success", vacina.getName() + " removido com sucesso");
		return MODEL_VACINE;
	}

}
