package com.jobsity.game.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface DataLoaderService {
	
	public  HashMap<String, List<String>> loadDataFromSource(String filePath) throws IOException;

}
