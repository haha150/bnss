package org.bnss.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;

@Controller
public class Web2Controller {

	@GetMapping("/")
	public String user(Model model, Principal principal) {
		model.addAttribute("username", principal.getName());
		return "user";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
