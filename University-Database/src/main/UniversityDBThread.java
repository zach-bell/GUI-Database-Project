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
	
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
		    try {
		    	frames.updateTimeLabel();
		    	
		        Thread.sleep(1000);
		    } catch (InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
		}
    }
}
