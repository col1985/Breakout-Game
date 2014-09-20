/**
 * Author: Colum Bennett
 * Student No: 20044622
 * Purpose: Break Out Game SimpleThread Class
 */

import java.io.*;

public class SimpleThread extends Thread {

	boolean running; // IS thread running
	boolean available; // Detect whether thread is available
	int wait; // time in milliseconds between executions
	int count; // Thread Name
	String id; // counter

	PrintWriter output;

	// Constructor, thread not running by default
	SimpleThread(int w, String s) {
		wait = w;
		running = false;
		id = s;
		count = 0;
	}

	int getCount() {
		return count;
	}

	boolean running() {
		return running;
	}

	boolean available() {
		return available;
	}

	@Override
	public void start() {

		// Start Thread
		running = true;

		// Print msgs
		System.out.println("Starting Thread will execute every " + wait
				+ " milliseconds.)");

		try {
			new WriteData().writeToFile("output.txt"); // Create
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}

		// Do whatever in Thread, next line most important!!
		super.start();

	}

	@Override
	public void run() {

		while (running) {

			System.out.println(id + ": " + count);
			count++;

			available = true;

			// Setup thread waiting
			try {
				sleep((wait));
			}

			catch (Exception te) {
				te.printStackTrace();
			}

		}
		// Print msg when end of thread
		System.out.println(id + " thread is done!!!");
	}

	public void quit() {

		System.out.println("Quiting thread!!!");

		running = false; // Ends loop in run()

		interrupt();
	}

} // End of Thread Class
