package com.chinamobile.cmss.web.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleErrorController implements ErrorController {
	
	private static final String PATH = "/pages/error/404";
	
	@Override
	@RequestMapping(value=PATH)
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/pages/error/404";
	}

}
