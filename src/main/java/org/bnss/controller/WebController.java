package org.bnss.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.bnss.domain.Data;
import org.bnss.domain.User;
import org.bnss.model.DataDTO;
import org.bnss.security.CustomAuthenticationProvider;
import org.bnss.service.DataService;
import org.bnss.service.UserService;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class WebController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DataService dataService;
	
	@Autowired
	CustomAuthenticationProvider auth;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<List<User>> populateDb() {
		auth.create();
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/files/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Data>> getFiles(@PathVariable String name) {
		List<Data> files = new ArrayList<>();
		for(Data d : dataService.getAllFiles()) {
			if(d.getRecipient().equals(name)) {
				files.add(d);
			}
		}
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/file/add", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<String> addFile(@RequestBody DataDTO fileToAdd) {
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
	
	
	
}