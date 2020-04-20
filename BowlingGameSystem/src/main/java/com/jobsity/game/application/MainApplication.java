package com.jobsity.game.application;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jobsity.game.context.configuration.ApplicationConfiguration;
import com.jobsity.game.service.DataLoaderService;
import com.jobsity.game.service.GameCalculatorService;
import com.jobsity.game.service.GameService;
import com.jobsity.game.service.PrinterService;

public class MainApplication {

	
	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		 
		DataLoaderService dataLoaderService = (DataLoaderService) context.getBean("dataLoadService");
		GameService gameService = (GameService) context.getBean("gameService");
		GameCalculatorService gameCalculatorService = (GameCalculatorService) context.getBean("gameCalculatorService");
		PrinterService printerService = (PrinterService) context.getBean("printerService");
		
		String pathFile = args[0];
		
		try {
			HashMap<String, List<String>> map = dataLoaderService.loadDataFromSource(pathFile);
						
			map.forEach((k,v)->{
				gameService.registerPlayer(k);
				gameService.processResult(v.iterator());
		        gameCalculatorService.CalculateResults(gameService.getBowler());
				printerService.PrintTotalAllFrames(gameService.getBowler().getFrameList(), gameService.getBowler().getName());

			});

		}catch(NoSuchFileException e) {
			System.out.print("The File was not founde");
		}catch(IOException e) {
			System.out.print("There is an error while procesing the file");
		}catch(Exception e) {
			System.out.print("Unexpected Error ");
		}
            
	}

}
