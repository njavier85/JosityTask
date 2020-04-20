package com.jobsity.game.service.impl;

import com.jobsity.game.model.Bowler;
import com.jobsity.game.model.Frame;
import com.jobsity.game.service.GameCalculatorService;

public class GameCalculatorServiceImpl implements GameCalculatorService {


	  public void CalculateResults(Bowler bowler) {
	        for (int i = 0; i <= 9; i++) {
	            Frame currentFrame = bowler.getFrameList().get(i);
	            if (i == 9) {
	                currentFrame.setSpare(false);
	                currentFrame.setStrike(false);
	            }
	            if (CheckFrameCalculability(bowler,i, currentFrame)) {
	                if (i == 0) {
	                    CalculateFirstFrame(bowler,i, currentFrame);
	                } else {
	                    if (!currentFrame.isStrike() && !currentFrame.isSpare()) {
	                        currentFrame.setTotalScore(currentFrame.getScore() + bowler.getFrameList().get(i - 1).getTotalScore());
	                    }
	                    if (currentFrame.isStrike() && i != 8) {
	                        if (bowler.getFrameList().get(i + 1).isStrike()) {
	                            currentFrame.setTotalScore(bowler.getFrameList().get(i - 1).getTotalScore() + currentFrame.getScore() + bowler.getFrameList().get(i + 1).getScore() + bowler.getFrameList().get(i + 2).getTries().get(0).getKnockedDownPins());
	                        } else {
	                             currentFrame.setTotalScore(bowler.getFrameList().get(i - 1).getTotalScore() + currentFrame.getScore() + bowler.getFrameList().get(i + 1).getScore());
	                        }
	                    }
	                    if (currentFrame.isStrike() && i == 8) {
	                        currentFrame.setTotalScore(bowler.getFrameList().get(i - 1).getTotalScore() + currentFrame.getScore() + bowler.getFrameList().get(i + 1).getTries().get(0).getKnockedDownPins() + bowler.getFrameList().get(i + 1).getTries().get(1).getKnockedDownPins());
	                    }
	                    if (currentFrame.isSpare()) {
	                        currentFrame.setTotalScore(bowler.getFrameList().get(i - 1).getTotalScore() + currentFrame.getScore() + bowler.getFrameList().get(i + 1).getTries().get(0).getKnockedDownPins());
	                    }
	                }
	            }
	        }
	    }
	  
	  
	  private void CalculateFirstFrame(Bowler bowler,int i, Frame currentFrame) {
	        if (!currentFrame.isStrike() && !currentFrame.isSpare()) {
	            currentFrame.setTotalScore(currentFrame.getScore());
	        }
	        if (currentFrame.isStrike()) {
	            if (bowler.getFrameList().get(i + 1).isStrike()) {
	                currentFrame.setTotalScore(currentFrame.getScore() + bowler.getFrameList().get(i + 1).getScore() + bowler.getFrameList().get(i + 2).getTries().get(0).getKnockedDownPins());
	            } else {
	                currentFrame.setTotalScore(currentFrame.getScore() + bowler.getFrameList().get(i + 1).getScore());
	            }
	        }
	        if (currentFrame.isSpare()) {
	            currentFrame.setTotalScore(currentFrame.getScore() + bowler.getFrameList().get(i + 1).getTries().get(0).getKnockedDownPins());
	        }
	    }

	  private boolean CheckFrameCalculability(Bowler bowler,int i, Frame currentFrame) {
	        boolean calculable = false;
	        if (currentFrame.getDone() && i != 8) {
	            if (!currentFrame.isStrike() && !currentFrame.isSpare()) {
	                calculable = true;
	            }
	            if (currentFrame.isStrike()) {
	                if (bowler.getFrameList().get(i + 1).isStrike()) {
	                    if (bowler.getFrameList().get(i + 1).getDone() && bowler.getFrameList().get(i + 2).getDone()) {
	                        calculable = true;
	                    }
	                } else {
	                    if (bowler.getFrameList().get(i + 1).getDone()) {
	                        calculable = true;
	                    } else {
	                        calculable = false;
	                    }
	                }
	            }
	            if (currentFrame.isSpare()) {
	                if (bowler.getFrameList().get(i + 1).getDone()) {
	                    calculable = true;
	                }
	            }
	        }
	        if (currentFrame.getDone() && i == 8) {
	            if (!currentFrame.isStrike() && !currentFrame.isSpare()) {
	                calculable = true;
	            }
	            if (currentFrame.isStrike() || currentFrame.isSpare()) {
	                if (bowler.getFrameList().get(i + 1).getDone()) {
	                    calculable = true;
	                }
	            }
	        }
	        return calculable;
	    }

}
