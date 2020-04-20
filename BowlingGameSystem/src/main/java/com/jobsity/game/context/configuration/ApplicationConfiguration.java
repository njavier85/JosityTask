package com.jobsity.game.context.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jobsity.game.model.Bowler;
import com.jobsity.game.service.DataLoaderService;
import com.jobsity.game.service.GameCalculatorService;
import com.jobsity.game.service.GameService;
import com.jobsity.game.service.PrinterService;
import com.jobsity.game.service.impl.DataLoaderServiceImpl;
import com.jobsity.game.service.impl.GameCalculatorServiceImpl;
import com.jobsity.game.service.impl.GameServiceImpl;
import com.jobsity.game.service.impl.PrinterServiceImpl;

@Configuration
public class ApplicationConfiguration{
	
	@Bean(name="bowlerRef")
    public Bowler getBowler(){
        return new Bowler();
    }
	
	@Bean(name="printerService")
    public PrinterService getPrinterRef(){
        return new PrinterServiceImpl();
    }
	
	@Bean(name="dataLoadService")
    public DataLoaderService getDataLoadServicef(){
        return new DataLoaderServiceImpl();
    }
	

	@Bean(name="gameCalculatorService")
    public GameCalculatorService getGameCalculatorService(){
        return new GameCalculatorServiceImpl();
    }
	
	@Bean(name="gameService")
    public GameService getGameService(){		
		return new GameServiceImpl(getBowler(),getGameCalculatorService());
    }
}
