package com.jobsity.game.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jobsity.game.context.configuration.ApplicationConfiguration;
import com.jobsity.game.service.DataLoaderService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class}) 
public class DataLoaderServiceImplTest {

	@Autowired
	private DataLoaderService dataLoaderService;
	
	private static String directoryPath = "src/test/resources/inputData/";
	
	@Test
	public void test_case_ok_resultado() throws IOException {

		String pathFile = directoryPath.concat("resultado.txt");
		HashMap<String, List<String>> map = dataLoaderService.loadDataFromSource(pathFile);
		
		assertNotNull(map);
		assertTrue(map.containsKey("Jeff"));
		assertTrue(map.containsKey("John"));
		
		List<String> list = map.get("Jeff");
		List<String> list2 = map.get("John");
		assertEquals(17,list.size());
		assertEquals(18,list2.size());
	}
	
	@Test
	public void test_case_ok_resultado2() throws IOException {

		String pathFile = directoryPath.concat("resultado2");
		HashMap<String, List<String>> map = dataLoaderService.loadDataFromSource(pathFile);
		
		assertNotNull(map);
		assertTrue(map.containsKey("Carl"));
		List<String> list = map.get("Carl");
		assertEquals(12,list.size());
	}
	
	@Test
	public void test_case_ok_resultado3() throws IOException {

		String pathFile = directoryPath.concat("resultado3");
		HashMap<String, List<String>> map = dataLoaderService.loadDataFromSource(pathFile);
		
		assertNotNull(map);
		assertTrue(map.containsKey("javier"));
		List<String> list = map.get("javier");
		assertEquals(17,list.size());
	}
}
