package com.jobsity.game.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.jobsity.game.service.DataLoaderService;

public class DataLoaderServiceImpl implements DataLoaderService {
	
	
	public HashMap<String, List<String>> loadDataFromSource(String filePath) throws IOException {

        HashMap<String, List<String>> map = new HashMap<String,List<String>>();
        
        Consumer<String> processLinesConsumer= new Consumer<String>() {
    	    public void accept(String currentString) {
    	    	String [] current = currentString.split(" ");
	            String name = current[0];
	            String knockDown = current[1];

	            if(map.containsKey(name)){
	            	map.get(name).add(knockDown);
	            }else{
	                List<String> numberList = new ArrayList<>();
	                numberList.add(knockDown);
	                map.put(name,numberList);
	            } 
    	    }
    	};
    	
    	
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach(processLinesConsumer);
		} 
		return map;
	}
}
