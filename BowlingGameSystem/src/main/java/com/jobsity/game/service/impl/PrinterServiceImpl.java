package com.jobsity.game.service.impl;

import java.util.List;

import com.jobsity.game.model.Frame;
import com.jobsity.game.model.Try;
import com.jobsity.game.service.PrinterService;

public class PrinterServiceImpl implements PrinterService{


    public void PrintTotalAllFrames(List<Frame> frames, String name) {
        int frameAmount = frames.size();
        System.out.print("Frame            ");
        for (int i =1;i<=frameAmount;i++) {
            System.out.print(" "+i+"        ");
        }
        System.out.println();
        System.out.println(name);
    
        
        System.out.print("Pinfalls         ");
        for (Frame frame : frames) {
            if(frame.isStrike()){
                System.out.print("   X  ");
            }else if(frame.isSpare()){
                System.out.print(frame.getTries().get(0).getKnockedDownPins() + "  /");
            }
            else{
                for (Try currentTry : frame.getTries()) {
                		if(currentTry.isFault()) {
                            System.out.print(" F ");
                		}else {
                        System.out.print(currentTry.getKnockedDownPins() + " ");
                		}
                }
            }
            System.out.print("     ");
        }

        System.out.println();

        System.out.print("Score          ");
        for (Frame frame : frames) {
            System.out.print("  " + frame.getTotalScore() + "      ");
        }

        System.out.println();
        System.out.println();
    }
}
