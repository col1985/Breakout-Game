/**
 * Author: Colum Bennett
 * Student No: 20044622
 * Purpose: Break Out Game Paddle Class
 */
import processing.core.*;

public class Paddle {

	float x, y, w, h, xSpeed;
	boolean moveX = true;
	PApplet parent;

	Paddle(float startX, float startY, PApplet p) {
		x = startX;
		y = startY;
		w = 90;
		h = 20;
		//xSpeed = 2;
		parent = p;
	}

	void display() {
		parent.fill(177, 57, 213);
		parent.rect(x, y, w, h);

	}

	void moveLeft() {
	
		if (this.x < 0) {
			moveX = false;
		} else {
			x -= 55;
		}

	}

	void moveRight() {
	
		if (this.x > 413) {
			moveX = false;
		} else {
			x += 55;
		}
	}

	boolean intersect(Ball b) {

		// Creating top boundry 
		float p1 = x;
		float p2 = x + 90;

		if ((b.y + b.r) >= y) {

			if ((b.x + b.r >= p1) && (b.x + b.r <= p2)) {

				return true;

			} else {

				return false;

			}

		} else {

			return false;

		}

	}

} // end of Paddle()
