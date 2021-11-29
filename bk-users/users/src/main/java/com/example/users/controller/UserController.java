package com.example.users.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.model.User;
import com.example.users.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/")
  	public String home() {
    	return "Hello Docker World";
  	}
	
	@GetMapping("/users")
	public ResponseEntity<?> users() {
		System.out.println("inside GET mehtod***********************");
		List<User> userList = userRepo.findAll();
		if(userList.size() > 0) {
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No User found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/users")
	public ResponseEntity<?> createUsers(@RequestBody User user) {
		System.out.println("inside POST mehtod************************");
		try {
			userRepo.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);	
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> userById(@PathVariable("id") String id) {
		System.out.println("inside GET BY ID mehtod************************");
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No User found with id : " + id, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> removeUser(@PathVariable("id") String id) {
		System.out.println("inside DELETE mehtod************************");
		try {
			userRepo.deleteById(id);
			return new ResponseEntity<>("Successfully deleted with " + id, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);	
		}
	}
	
	@PutMapping("/users")
	public ResponseEntity<?> updateUsers(@RequestBody User user) {
		System.out.println("inside POST mehtod************************");
		try {
			userRepo.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);	
		}
	}

	
}
