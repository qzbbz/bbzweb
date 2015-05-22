package com.wisdom.web.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		return new ModelAndView("global_exception");
		/*System.out.println("Exception : " + ex.toString());
		if (ex instanceof org.springframework.web.multipart.MaxUploadSizeExceededException) {
			return "redirect:/views/webviews/error/upload_exceed_max_error.html";
		} else {
			return "redirect:/views/webviews/error/global_exception.html";
		}*/
	}
}