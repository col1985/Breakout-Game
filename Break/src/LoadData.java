/**
 * Author: Colum Bennett
 * Student No: 20044622
 * Purpose: Break Out Game Load Data Class to read text data
 */
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import processing.core.*;

public class LoadData extends GameClient {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8391764734895922313L;
	
	private int rows = 36; // Array index
	private int rowsC = 36; // Array index
	private int i = 0; // Loop Control
	
	// Global Vertices Arrays
	public int[] x = new int[rows];
	public int[] y = new int[rows];
	public int[] r = new int[rowsC];
	public int[] g = new int[rowsC];
	public int[] b = new int[rowsC];
	PApplet parent;
	
	// Constructor
	public LoadData() {}

	// Method to read .txt file and store vertices
	public void loadVertices() {
		
		try {
			InputStream vertices = getClass().getResourceAsStream("vert.txt");
			Scanner vert = new Scanner(new InputStreamReader(vertices));
			System.out.println("Path to vert.txt " + new File("vert.txt").getAbsolutePath());
			
			while (vert.hasNext()) {

				x[i] = vert.nextInt();
				y[i] = vert.nextInt();

				System.out.println("LoadData; X Coords: " + x[i]
						+ ", Y Coords:" + y[i]);
				i++;
			}

		} catch (Exception eVert) {
			System.err.println("Unable to find vert.txt, exiting...");
			eVert.printStackTrace();
		}
	}

	// Method to read .txt file and store rgb values
	public void loadColours() {
		try {
			InputStream colours = getClass().getResourceAsStream("rgb.txt");
			Scanner rgb = new Scanner(new InputStreamReader(colours));
			System.out.println("Path to rgb.txt " + new File("rgb.txt").getAbsolutePath());
			
			while (rgb.hasNext()) {

				r[i] = rgb.nextInt();
				g[i] = rgb.nextInt();
				b[i] = rgb.nextInt();

				System.out.println("LoadData; RGB Values:  " + r[i] + ", "
						+ g[i] + ", " + b[i]);
				i++;
			}
		} catch (Exception eColours) {
			System.err.println("Unable to find rgb.txt, exiting...");
			eColours.printStackTrace();
		}
	}

	public void startMenu() {

	}

} // end of LoadData()

