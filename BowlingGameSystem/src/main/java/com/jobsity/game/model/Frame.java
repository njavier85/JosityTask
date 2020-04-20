package com.jobsity.game.model;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private int score = 0;
    private List<Try> tries = new ArrayList<Try>();
    private boolean strike = false;
    private boolean spare = false;
    private int totalScore = 0;
    private boolean done = false;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStrike() {
        return strike;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public boolean isSpare() {
        return spare;
    }

    public void setSpare(boolean spare) {
        this.spare = spare;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<Try> getTries() {
        return tries;
    }

    public void setTries(ArrayList<Try> tries) {
        this.tries = tries;
    }


    public boolean getDone(){
        return done;
    }

    public void setDone(boolean done){
        this.done=done;
    }
}
