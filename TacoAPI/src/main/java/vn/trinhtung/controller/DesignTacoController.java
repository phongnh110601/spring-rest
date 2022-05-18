package vn.trinhtung.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.entity.Taco;
import vn.trinhtung.repository.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DesignTacoController {
	private final TacoRepository tacoRepository;

	@GetMapping("/recent")
	public Iterable<Taco> recentTacos() {
		return tacoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Taco tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepository.findById(id);
		if (optTaco.isPresent()) {
			return optTaco.get();
		}
		return null;
	}

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		Taco tacoSaved = tacoRepository.save(taco);
		return tacoSaved;
	}

}