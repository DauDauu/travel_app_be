package com.dulich.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dulich.dto.DuLichDTO;

@RestController
public class DuLichAPI {
	@GetMapping("/test")
	public String testAPI() {
		return "success";
	}

	@PostMapping("/new")

	public DuLichDTO createNew(@RequestBody DuLichDTO model) {

		return model;

	}
}
