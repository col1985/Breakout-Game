/**
 * Author: Colum Bennett
 * Student No: 20044622
 * Purpose: Break Out Game Block Class
 */

import processing.core.*;

public class Block extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4473740620970639997L;
	
	int x, y, w, h;
	PApplet parent;

	// Default Constructor
	public Block() {
	}

	// OverLoaded Constructor
	public Block(int startX, int startY, PApplet p) {
		this.x = startX;
		this.y = startY;
		this.w = 80;
		this.h = 30;
		this.parent = p;

	}

	// Acessor Methods
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	// Mutator Methods
	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public void display() {
		parent.noStroke();
		parent.rect(x, y, w, h);
	}

	boolean intersect(Ball b) {

		float p1 = x;
		float p2 = x + 80;
		float p3 = y + 30;
		float p4 = y + 30;

		if (((b.y + b.r) >= p3) && ((b.y + b.r) <= p4 + 30)) {

			if ((b.x >= p1) && (b.x <= p2)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
		
	}
	
} // end of class

