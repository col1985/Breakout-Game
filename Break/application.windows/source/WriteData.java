/**
 * Author: Colum Bennett
 * Student No: 20044622
 * Purpose: Break Out Game WriteData Class
 */

import java.io.*;

public class WriteData extends GameClient {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4323117772808654410L;

	public void writeToFile(String filename) { // Method to create output.txt file

		BufferedWriter bufferedWriter = null;

		try {

			// Construct the BufferedWriter object
			bufferedWriter = new BufferedWriter(new FileWriter(filename));

			// Start writing to the output stream
			bufferedWriter.write("This is a TEST ");
			//bufferedWriter.write("Player: " + player.getUsername());
			//bufferedWriter.write("Score: " + player.getScore());
			//bufferedWriter.write("Lives: " + player.getLives());
			bufferedWriter.newLine();
			bufferedWriter.write("To prove that i can write data to text file!!");

		}
		// Error Handling
		catch (FileNotFoundException ex) {
			System.err.println("Cannot create output.txt file. exiting...");
			ex.printStackTrace();
		}

		catch (IOException ex) {
			System.err.println("Cannot find output.txt file. exiting...");
			ex.printStackTrace();
		}

		finally {
			// Close the BufferedWriter
			try {
				if (bufferedWriter != null) {
					bufferedWriter.flush(); // writes data to output file
					bufferedWriter.close(); // closes link to output file
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		// Retrieves path to file
		System.out.println("Path to output.txt "+ new File("output.txt").getAbsolutePath()); 
	}
}
