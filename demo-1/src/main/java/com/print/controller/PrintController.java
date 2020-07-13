package com.print.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.print.service.Print;

@RestController
public class PrintController {
	@Autowired
	Print print;

	@GetMapping("/insert")
	public String insertQueue(@RequestParam("filePath") String filePath) {
		return print.insert(filePath);
	}
}
