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

import io.redspark.domain.Veterinary;
import io.redspark.service.VeterinaryService;

@RequestMapping(ControllerConstants.VETERINARY)
@Controller
public class VeterinaryController {

	private final ModelAndView GET_VETERINARY = new ModelAndView("redirect:/" + ControllerConstants.VETERINARY);
	
	@Autowired
	private VeterinaryService service;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getVaccines(final Veterinary veterinary){
		List<Veterinary> veterinaries = service.findAll();

		ModelAndView modelAndView = new ModelAndView("veterinarios");
		modelAndView.addObject("veterinaries", veterinaries);
		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView saveVeterinary(final @Valid Veterinary veterinary, final BindingResult bindingResult, final RedirectAttributes redirectAttributes){
		if(bindingResult.hasErrors()){
			redirectAttributes.addFlashAttribute("error", "Erro ao salvar o veterinario");
			return GET_VETERINARY;
		}
		boolean isSaved = service.save(veterinary);

		if(!isSaved)
			redirectAttributes.addFlashAttribute("error", "Um veterinário com o mesmo nome já existe");
		else
			redirectAttributes.addFlashAttribute("success", veterinary.getName() + " cadastrado com sucesso");
		
		return GET_VETERINARY;
	}

	@RequestMapping(value="/remove/{id}")
	public ModelAndView removeVeterinary(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		Veterinary veterinary = service.findOne(id);
		if(veterinary!=null)
			service.delete(id);

		redirectAttributes.addFlashAttribute("success", veterinary.getName() + " removido com sucesso");
		return GET_VETERINARY;
	}
}
