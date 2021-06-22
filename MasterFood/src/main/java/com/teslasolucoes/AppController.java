package com.teslasolucoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/index")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	@GetMapping("/pedidoMesa")
	public String showpedidoMesa() {
		return "pedidoMesa";
	}
	
	@GetMapping("/pedidoDelivery")
	public String showpedidoDelivery() {
		return "pedidoDelivery";
	}
	
	@GetMapping("/pedidoCozinha")
	public String showpedidoCozinha() {
		return "pedidoCozinha";
	}
	
	@GetMapping("/list_users")
	public String showUsuarios(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		repo.save(user);
		return "register_sucess";
	}
}