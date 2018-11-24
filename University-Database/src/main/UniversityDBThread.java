package main;

/* --------------------------
 * CS 430 - Database Project
 * By: Zachary Vanscoit
 * -------------------------- */

import main.window.Frames;

public class UniversityDBThread extends Thread {
	
	private Frames frames;
	
	public UniversityDBThread(Frames frames) {
		this.frames = frames;
	}
	
	private int incre = 0;
	private final int maxIncre = 10;
	
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
		    try {
		    	frames.updateTimeLabel();
		    	if (frames.errorText.isVisible()) {
		    		incre ++;
		    		if (incre >= maxIncre) {
		    			frames.errorText.setVisible(false);
		    		}
		    	} else {
		    		incre = 0;
		    	}
		        Thread.sleep(1000);
		    } catch (InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
		}
    }
}
