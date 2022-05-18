package vn.trinhtung.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.entity.Order;
import vn.trinhtung.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderRepository orderRepository;

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "order-form";
	}

	@PostMapping
	public Order order(@RequestBody Order order) {
		return orderRepository.save(order);
	}

	@GetMapping("/recent")
	public List<Order> recent() {
		return orderRepository.findAll();
	}
}
