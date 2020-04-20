package com.jobsity.game.model;

public class Try {

    private int knockedDownPins;
    private boolean done = false;
    private int tryNumber =0;
    private boolean fault=false;

    public int getTryNumber() {return tryNumber;}

    public void setTryNumber(int tryNumber) { this.tryNumber = tryNumber; }

    public int getKnockedDownPins() {
        return knockedDownPins;
    }

    public void setKnockedDownPins(int knockedDownPins) {
        this.knockedDownPins = knockedDownPins;
    }

    public boolean getDone(){ return done; }

    public void setDone(boolean done){ this.done=done; }

	public boolean isFault() {
		return fault;
	}

	public void setFault(boolean fault) {
		this.fault = fault;
	}

}
