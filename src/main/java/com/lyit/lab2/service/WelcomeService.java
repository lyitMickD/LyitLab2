package com.lyit.lab2.service;

import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
	
	public String getWelcomeMessage(String name) {
		return String.format("Welcome %s!", name);
	}

}
