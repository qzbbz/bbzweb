package com.wisdom.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({ "userId" })
public class UserValidateController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserValidateController.class);
	
	
	
}
