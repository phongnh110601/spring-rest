package vn.trinhtung.controller;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.entity.Order;
import vn.trinhtung.entity.Taco;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	private final RestTemplate restTemplate;

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "order-form";
	}

	@PostMapping
	public String order(Order order, HttpSession session) {
		Taco taco = (Taco) session.getAttribute("taco");
		order.setTacos(Collections.singletonList(taco));
		System.out.println(order);
		restTemplate.postForObject("http://localhost:8080/orders", order, Order.class);
		return "redirect:/";
	}

	@GetMapping("/recent")
	public String recent(Model model) {
		Order[] result = restTemplate.getForObject("http://localhost:8080/orders/recent", Order[].class);
		model.addAttribute("orders", result);
		System.out.println(result.toString());
		return "order-success";
	}
}
