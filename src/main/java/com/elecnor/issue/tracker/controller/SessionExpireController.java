package com.elecnor.issue.tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionExpireController {
		@RequestMapping("/sessionExpire")
		public String getProject() {
			return "sessionExpire";
		}
}
