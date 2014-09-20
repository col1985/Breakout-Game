/**
 * Author: Colum Bennett
 * Student No: 20044622
 * Purpose: Break Out Ball Class
 */
import processing.core.*;

public class Ball extends GameClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7384682399076481423L;
	
	float x, y, r;
	float xSpeed, ySpeed; 			// Set ball speed
	private boolean moveX = true; 	// Move ball X axis control
	private boolean moveY = true;	// Move ball Y axis control
	PApplet parent;

	// Overloaded constructor with hardwired values
	Ball(float tempR, PApplet p) {
		x = 125;
		y = 340;
		r = tempR;
		xSpeed = 3;
		ySpeed = -3;
		parent = p;
	}

	void display() {
		parent.fill(255, 0, 34);
		parent.noStroke();
		parent.ellipse(x, y, r, r);
	}

	void move() {

		// Assign Random positioning.
		this.x += xSpeed * 2;
		this.y += ySpeed * 2;

		// Boundary xAxis
		if (moveX == true) {
			if (this.x > 478 || this.x < 0) {
				xSpeed *= -1;
			}
		}

		// Boundary yAxis
		if (moveY == true) {

			// Game over if ball hits bottom
			if (this.y > 700) {
				ySpeed *= -1;
				gameEnd = true;
			}

			if (this.y < 4) {
				ySpeed *= -1;
			}
		}
	}
} // end of Ball()
