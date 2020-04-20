package com.jobsity.game.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jobsity.game.context.configuration.ApplicationConfiguration;
import com.jobsity.game.model.Frame;
import com.jobsity.game.service.DataLoaderService;
import com.jobsity.game.service.GameCalculatorService;
import com.jobsity.game.service.GameService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class GameServiceImplIntegrationTest {

	@Autowired
	private DataLoaderService dataLoaderService;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
    private GameCalculatorService gameCalculatorService;

	private static String directoryPath = "src/test/resources/inputData/";
	
	
	@Before
    public void init() {

    }
	
	
	
	@Test
	public void test_case_calculate_final_two_players() throws IOException {

		String pathFile = directoryPath.concat("resultado.txt");
		HashMap<String, List<String>> map = dataLoaderService.loadDataFromSource(pathFile);
        
		/*******Process player 1*************/
		gameService.registerPlayer("Jeff");
		gameService.processResult(map.get("Jeff").iterator());
        gameCalculatorService.CalculateResults(gameService.getBowler());

		
		Frame lastFrame = gameService.getBowler().getFrameList().get(9); 
		
		int totalScore = lastFrame.getTotalScore();
		
		assertEquals(167,totalScore);
		
		
		/*********Process player 2***************************/
		gameService.registerPlayer("John");
		gameService.processResult(map.get("John").iterator());
        gameCalculatorService.CalculateResults(gameService.getBowler());

		
		Frame lastFrame2 = gameService.getBowler().getFrameList().get(9); 
		
		int totalScore2 = lastFrame2.getTotalScore();
		
		assertEquals(151,totalScore2);
	}
	
	
	@Test
	public void test_case_calculate_final_perfect_score() throws IOException {

		String pathFile = directoryPath.concat("resultado2");

		manageGame("Carl", pathFile);
		
		Frame lastFrame = gameService.getBowler().getFrameList().get(9); 
		
		int totalScore = lastFrame.getTotalScore();
		
		assertEquals(300,totalScore);
	}
	
	@Test
	public void test_case_calculate_final_zero_score() throws IOException {

		String pathFile = directoryPath.concat("resultado5");

		manageGame("Jose", pathFile);
		
		Frame lastFrame = gameService.getBowler().getFrameList().get(9); 
		
		int totalScore = lastFrame.getTotalScore();
		
		assertEquals(0,totalScore);
	}
	
	
	private void manageGame(String name, String pathFile) throws IOException {
		HashMap<String, List<String>> map = dataLoaderService.loadDataFromSource(pathFile);

		gameService.registerPlayer(name);
		gameService.processResult(map.get(name).iterator());
        gameCalculatorService.CalculateResults(gameService.getBowler());
	}
}
