package org.bnss.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<List<User>> populateDb() {
		auth.create();
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/create2", method = RequestMethod.GET)
	public ResponseEntity<List<User>> populateDb2() throws IOException {
		List<User> users = userService.getAllUsers();
		for (User u : users) {
			if (u.getId() == 1) {
				u.setCert("/Users/Ali/Downloads/bnss-2/certs/employee3.der");
				userService.updateUser(u);
			} else if (u.getId() == 2) {
				u.setCert("/Users/Ali/Downloads/bnss-2/certs/employee1.der");
				userService.updateUser(u);
			} else if (u.getId() == 3) {
				u.setCert("/Users/Ali/Downloads/bnss-2/certs/employee2.der");
				userService.updateUser(u);
			} else if (u.getId() == 4) {
				u.setCert("/Users/Ali/Downloads/bnss-2/certs/employee4.der");
				userService.updateUser(u);
			}
		}
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/files/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Data>> getFiles(@PathVariable String name) {
		List<Data> files = new ArrayList<>();
		for (Data d : dataService.getAllFiles()) {
			if (d.getRecipient().equals(name)) {
				files.add(d);
			}
		}
		return new ResponseEntity<>(files, HttpStatus.OK);
	}

	@RequestMapping(value = "/file/add", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<String> addFile(@RequestBody DataDTO fileToAdd) {
		Data d = new Data();
		d.setFrom(fileToAdd.getFrom());
		d.setRecipient(fileToAdd.getRecipient());
		d.setFile(fileToAdd.getFile());
		d.setHash(fileToAdd.getHash());
		d.setKey(fileToAdd.getKey());
		d.setName(fileToAdd.getName());
		d = dataService.addFile(d);
		if (d != null && d.getId() != null) {
			return new ResponseEntity<>("OK", HttpStatus.OK);
		}
		return new ResponseEntity<>("NOT OK", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/certificate/{name}", method = RequestMethod.GET)
	public void getFile(@PathVariable("name") String name, HttpServletResponse response) {
		List<User> users = userService.getAllUsers();
		for (User u : users) {
			try {
				if(u.getUsername().equals(name)) {
					File f = new File(u.getCert());
					InputStream is = new FileInputStream(f);
					IOUtils.copy(is, response.getOutputStream());
					response.flushBuffer();
					break;
				}
			} catch (IOException ex) {
				throw new RuntimeException("IOError writing file to output stream");
			}
		}

	}

}