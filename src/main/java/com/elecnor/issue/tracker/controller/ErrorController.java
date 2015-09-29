package com.elecnor.issue.tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/errorPage")
	public String getErrorPage() {
		return "error";
	}
	
}
