package vn.trinhtung.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.entity.Ingredient;
import vn.trinhtung.entity.Ingredient.Type;
import vn.trinhtung.entity.Taco;

@Controller
@RequestMapping("/design")
@RequiredArgsConstructor
public class DesignTacoController {
	private final RestTemplate template;

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays
				.asList(template.getForObject("http://localhost:8080/ingredients", Ingredient[].class));
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@GetMapping
	public String showDesignForm(Model model) {
		model.addAttribute("taco", new Taco());
		return "design";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		List<Ingredient> ingrList = new ArrayList<Ingredient>();
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType().equals(type))
				ingrList.add(ingredient);
		}
		return ingrList;
	}

	@PostMapping
	public String processDesign(@RequestParam("ingredients") String ingredientIds, @RequestParam("name") String name,
			HttpSession session) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		System.out.println(ingredientIds);
		for (String ingredientId : ingredientIds.split(",")) {
			Ingredient ingredient = template.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class,
					ingredientId);

			ingredients.add(ingredient);
		}
		Taco taco = new Taco();
		taco.setName(name);
		taco.setIngredients(ingredients);
		System.out.println("Taco " + taco);
		Taco result = template.postForObject("http://localhost:8080/design", taco, Taco.class);
		session.setAttribute("taco", result);
		return "redirect:/orders/current";
	}
}