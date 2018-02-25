package org.bnss.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> welcome() {
		System.out.println("post login");
		return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@RequestMapping(value = "/user")
    public String user(Model model, Principal principal) {
         
        UserDetails currentUser 
          = (UserDetails) ((Authentication) principal).getPrincipal();
        model.addAttribute("username", currentUser.getUsername());
        return "user";
    }
	
	@GetMapping("/test")
	public String home(Principal principal){
		return String.format("Hello %s!", principal.getName());
	}
	
	@RequestMapping(value = "/logged", method = RequestMethod.GET)
    public String helloController()
    {
        String loggedUser;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            loggedUser = ((UserDetails) principal).getUsername();
        } else {
            loggedUser = principal.toString();
        }
        return "X.509 authentication done! REST client has been identified as ["+loggedUser+"] ";
    }
}