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
import io.redspark.service.AnimalService;

@Controller
@RequestMapping(ControllerConstants.ANIMAL)
public class AnimalController {

	private final ModelAndView MODEL_AND_VIEW = new ModelAndView("redirect:/" + ControllerConstants.ANIMAL);

	@Autowired
	private AnimalService service;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(final Animal animal) {
		final List<Animal> animals = service.findAll();

		ModelAndView modelAndView = new ModelAndView("animais");
		modelAndView.addObject("animals", animals);
		modelAndView.addObject("animal", animal);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveAnimal(final @Valid Animal animal, final BindingResult bindingResult,
			final RedirectAttributes redirectAttributes) {
		try {
			if (bindingResult.hasErrors()) {
				redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar " + animal.getName());
				return MODEL_AND_VIEW;
			}
			service.save(animal);

		} catch (Exception e) {
			return MODEL_AND_VIEW;
		}
		redirectAttributes.addFlashAttribute("success", animal.getName() + " cadastrado com sucesso");
		return MODEL_AND_VIEW;
	}

	@RequestMapping(value = "/remove/{id}")
	public ModelAndView removeAnimal(final @PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
		Animal animal = service.findOne(id);
		if (animal != null)
			service.delete(id);

		redirectAttributes.addFlashAttribute("success", animal.getName() + " removido com sucesso");
		return MODEL_AND_VIEW;
	}
}
