package com.jobsity.game.context.configuration;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jobsity.game.service.DataLoaderService;
import com.jobsity.game.service.GameCalculatorService;
import com.jobsity.game.service.GameService;
import com.jobsity.game.service.PrinterService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class}) 
public class ApplicationConfigurationTest {

	@Autowired
	private PrinterService printerService;
	
	@Autowired
	private DataLoaderService dataLoaderService;
	
	@Autowired
	private GameCalculatorService gameCalculatorService;
	
	@Autowired
	private GameService gameService;
	
	
	@Test
	public void entitiesContextTest() {

		assertNotNull(printerService);
		assertNotNull(dataLoaderService);
		assertNotNull(gameCalculatorService);
		assertNotNull(gameService);
	}
}
