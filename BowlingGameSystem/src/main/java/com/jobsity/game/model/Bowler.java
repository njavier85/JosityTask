package com.jobsity.game.model;


import java.util.ArrayList;
import java.util.List;

public class Bowler {

    private String name;
    private List<Frame> frameList = new ArrayList<Frame>();

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public List<Frame> getFrameList() {
        return frameList;
    }

    public void setFrames(ArrayList<Frame> frameList) {
        this.frameList = frameList;
    }
}
