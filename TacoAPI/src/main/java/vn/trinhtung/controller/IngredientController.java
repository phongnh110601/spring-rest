package vn.trinhtung.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.entity.Ingredient;
import vn.trinhtung.repository.IngredientRepository;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IngredientController {
	private final IngredientRepository ingredientRepository;

	@GetMapping
	public Iterable<Ingredient> getAllIngredients() {
		return ingredientRepository.findAll();
	}

	@GetMapping("/{id}")
	public Ingredient ingredientById(@PathVariable("id") String id) {
		Optional<Ingredient> optIngredient = ingredientRepository.findById(id);
		if (optIngredient.isPresent()) {
			return optIngredient.get();
		}
		return null;
	}
}
