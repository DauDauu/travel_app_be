package com.dulich.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dulich.dto.UsernameExistsException;
import com.dulich.entity.UserEntity;
import com.dulich.service.IUserService;


@CrossOrigin
@RestController
//@RequestMapping("/user")
public class UserAPI {
	@Autowired
	private IUserService userService;
	
	@PostMapping(value = "/user")
	public ResponseEntity<String> createUser(@RequestBody UserEntity userEntity) {
		try {
			userService.save(userEntity);
			return ResponseEntity.ok("User registered successfully");
		}catch (UsernameExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/getUserByUsername")
	public UserEntity findOneUser(@RequestParam String username){
		return userService.findUserByUsername(username);
	}
}
