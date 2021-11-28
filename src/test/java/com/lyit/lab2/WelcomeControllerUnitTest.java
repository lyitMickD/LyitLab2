package com.lyit.lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.lyit.lab2.controller.UserController;
import com.lyit.lab2.service.WelcomeService;

class WelcomeControllerUnitTest {

	@Test
	void shouldWelcome() {
		WelcomeService welcomeService = Mockito.mock(WelcomeService.class);
		when(welcomeService.getWelcomeMessage("John")).thenReturn("Welcome John!");
		UserController welcomeController = new UserController(welcomeService);
		assertEquals("Welcome John!", welcomeController.welcome("John"));
	}

}
