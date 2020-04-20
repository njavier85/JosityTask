package com.jobsity.game.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.jobsity.game.model.Bowler;
import com.jobsity.game.model.Frame;
import com.jobsity.game.model.Try;
import com.jobsity.game.service.GameCalculatorService;
import com.jobsity.game.service.GameService;

public final class GameServiceImpl implements GameService{

    private Bowler bowler;
    private GameCalculatorService gameCalculatorService;

    
    public GameServiceImpl(Bowler bowler, GameCalculatorService gameCalculatorService) {
    	this.bowler = bowler;
    	this.gameCalculatorService = gameCalculatorService;
    }

    public void registerPlayer(String name) {
        bowler.setName(name);
        bowler.setFrames(createNewScoresTable());

    }

    public void processResult(Iterator<String> it) {
        try {
            int tenthFrameTriesCounter = 0;
            for (int i = 0; i <= 9; i++) {
                Frame currentFrame = bowler.getFrameList().get(i);
                for (Try thisTry : currentFrame.getTries()) {
                    if (!thisTry.getDone()) {
                        if (i == 9) {
                            tenthFrameTriesCounter++;
                            thisTry = makeLastFrameTry(it,tenthFrameTriesCounter, thisTry, currentFrame);
                        } else {
                            thisTry.setKnockedDownPins(checkInsertedValue((String) it.next(), currentFrame, thisTry, false));
                        }
                        currentFrame.setScore( currentFrame.getScore() + thisTry.getKnockedDownPins());
                        currentFrame = checkStrikeOrSpare(i, currentFrame, thisTry);
                    }
                }
                currentFrame.setDone(true);
                gameCalculatorService.CalculateResults(bowler);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private ArrayList<Frame> createNewScoresTable() {
        int triesCounter = 0;
        int indexOfTry = 0;
        ArrayList<Frame> createFrames = new ArrayList<Frame>();
        while (createFrames.size() < 10) {
            if (createFrames.size() + 1 == 10) {
                triesCounter = 3;
            } else {
                triesCounter = 2;
            }
            Frame frame = new Frame();
            indexOfTry = 0;
            while (frame.getTries().size() < triesCounter) {
                Try newTry = new Try();
                newTry.setTryNumber(++indexOfTry);
                frame.getTries().add(newTry);
            }
            createFrames.add(frame);
        }
        return createFrames;
    }
   
    
    private Frame checkStrikeOrSpare(int i, Frame currentFrame, Try thisTry) {
        if (thisTry.getKnockedDownPins() == 10 && thisTry.getTryNumber() == 1) {
            currentFrame.setStrike(true);
            if (i != 9) {
                currentFrame.getTries().get(1).setDone(true);
            }
        }
        if (thisTry.getTryNumber() == 2 && currentFrame.getScore() >= 10) {
            currentFrame.setSpare(true);
        }
        return currentFrame;
    }

    private Try makeLastFrameTry(Iterator<String> it,int tenthFrameTriesCounter, Try thisTry, Frame currentFrame) throws IOException {
        if (tenthFrameTriesCounter != 3) {
            thisTry.setKnockedDownPins(checkInsertedValue((String) it.next(), currentFrame, thisTry, true));
        } else {
            if (currentFrame.isSpare() || currentFrame.isStrike()) {
                thisTry.setKnockedDownPins(checkInsertedValue((String) it.next(), currentFrame, thisTry, true));
            }
        }
        return thisTry;
    }

    private int checkInsertedValue(String value, Frame currentFrame, Try currentTry, boolean isLastFrame) {
    	
    	Integer intValue;
    	if("F".equals(value)) {
    		intValue= 0;
    		currentTry.setFault(true);
    	}else {
    		intValue= new Integer(value);
    	}
    	
        if (currentTry.getTryNumber() == 2 && !isLastFrame) {
            if (intValue > 10 - currentFrame.getTries().get(0).getKnockedDownPins()) {
            	intValue = 10 - currentFrame.getTries().get(0).getKnockedDownPins();
            }
        }
        if (intValue >= 10) {
            return 10;
        } else {
            return intValue;
        }
    }

    
    public Bowler getBowler() {
		return bowler;
	}

	public void setBowler(Bowler bowler) {
		this.bowler = bowler;
	}

	public GameCalculatorService getGameCalculatorService() {
		return gameCalculatorService;
	}

	public void setGameCalculatorService(GameCalculatorService gameCalculatorService) {
		this.gameCalculatorService = gameCalculatorService;
	}
}
