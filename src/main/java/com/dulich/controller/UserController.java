package com.dulich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dulich.entity.UserEntity;
import com.dulich.service.impl.UserService;

@CrossOrigin
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/checkLogin")
	public ResponseEntity<?> checkLogin(@RequestParam String username, @RequestParam String password) {
	    UserEntity user = userService.findUserByUsername(username);
	    if (user != null) {
	        String matkhau = user.getPassword();
	        if (userService.checkPassword(password, matkhau)) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	    }
	}
}
