package com.jobsity.game.service;

import java.io.IOException;
import java.util.Iterator;

import com.jobsity.game.model.Bowler;

public interface GameService {
	

    public void registerPlayer(String name) ;

    public void processResult(Iterator<String> it)  ;
    
    public Bowler getBowler() ;

}
