/**
 * Author: Colum Bennett
 * Student No: 20044622
 * Purpose: Break Out Game Client
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;
import processing.core.*;

public class GameClient extends PApplet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1282148641950854625L;
	
	int[] x, y; // Store Vertices
	int[] r, g, b; // Store RGB values
	private int i; // Loop Control
	private int score; // player score
	private String input = ""; // user input
	private boolean moveX = true; // Paddle move control
	private boolean gameStart = false; // gameStart control
	private boolean nextLevel; // Next Level control
	public boolean gameEnd = false; // gameEnd control
	PApplet parent; // PApplet
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public ArrayList<User> topScores = new ArrayList<User>();

	PImage img; // Load image for background
	PFont statFont, titleFont, nameFont; // load fonts for text

	// Instantiate objects.
	User player;
	Paddle paddle;
	Ball ball;
	LoadData loadVert, loadColours, loadMenu;
	SimpleThread output;

	public void setup() {

		frameRate(60); // set frameRate per second
		size(508, 700);

		// Request Username
		input = JOptionPane.showInputDialog(null, "Please enter your name.");
		System.out.println("Game has started for " + input);

		if (input == null) {
			// gameStart = true;
			input = player.getUsername();
			System.out.println("Game has started for " + input);
		}

		
		keyPressed(); // load keyboard functionality
		
		// Load bg & fonts
		img = loadImage("game-bg.png");
		statFont = createFont("OCR", 12, true);
		//statFont = loadFont("OCRAStd-12.vlw");
		nameFont = createFont("ASO", 60, true);
		//nameFont = loadFont("ASO-60.vlw");
		titleFont = createFont("DesigersBold", 36, true);
		//titleFont = loadFont("DESIB___-20.vlw");

		// Instantiate objects
		player = new User();
		loadVert = new LoadData();
		loadColours = new LoadData();
		loadMenu = new LoadData();
		ball = new Ball(30, this);
		paddle = new Paddle(125, 680, this);
		output = new SimpleThread(1000, "Create output.txt file.");

		loadVert.loadVertices(); // load vertices
		x = loadVert.x;
		y = loadVert.y;
		
		loadColours.loadColours(); // load rgb values
		r = loadColours.r;
		g = loadColours.g;
		b = loadColours.b;
		
		// Instantiate Blocks and add to Array List
		for(i = 0; i <= 35; i++) {
			Block brick = new Block(x[i], y[i], this);
			blocks.add(brick);
			System.out.println("Block added to blocks list b" + i);
		}

		output.start(); // start "Create output.txt thread".

		player.setUsername(input); // set username
		topScores.add(player); // add user to topScores List
	}

	public void draw() {

		background(img);

		// load welcome screen
		if (gameStart == false) {
			welcome();
			
			if (mousePressed && (mouseButton == LEFT)) {
				// loop();
				gameStart = true;
			}
		}

		// load Game
		if (gameStart == true) {
			gameData(); // load game stats

			// load blocks from Arraylist
			for (i = 0; i < blocks.size(); i++) {
				Block block = (Block) blocks.get(i);
				fill(r[i], g[i], b[i]);
				block.display();

				// detect ball collision with brick
				if (block.intersect(ball)) {
					ball.ySpeed *= -1;
					blocks.remove(block);
					score = score + 5;
					player.setScore(score);
				}
			}

			paddle.display();
			ball.move();
			ball.display();
			
			// life calculation
			if (ball.y == 700) {
				int gameKiller = player.getLives() - 1;
				player.setLives(gameKiller);

				if (player.getLives() == 0) {
					gameEnd = true;
				}
			}

			if (paddle.intersect(ball)) { // handle intersection of paddle & ball
				ball.xSpeed *= -1;
				ball.ySpeed *= -1;
			}

			// load gameover screen
			if (gameEnd == true) {
				end();
			}
			
			// Next level
			if(blocks.size() == 0) {
				noLoop();
				nextLevel();
				nextLevel = false;
				
				if (mousePressed && (mouseButton == LEFT)) {
					nextLevel = true;
					
					if (nextLevel == true) {
						loop();
						level2();
					}
				}
			}	
		}

	} // end of draw()

	// keyboard functionality method
	public void keyPressed() {
		// Method to control paddle with keyboard
		if (key == CODED) {

			if (key == '1') {
				gameStart = true;
				// redraw();
			}
			// Move Paddle left using keyboard
			if (keyCode == LEFT) {
				if (moveX == true) {
					paddle.moveLeft();
				}

			}
			// Move Paddle right using keyboard
			if (keyCode == RIGHT) {
				if (moveX == true) {
					paddle.moveRight();
				}
			}
			// Escape Game
			if (keyCode == ESC) {
				exit();
			}
		}
	}

	// Welcome screen text method
	public void welcome() {
		textAlign(CENTER);
		textFont(nameFont);
		textSize(60);
		fill(140, 255, 63);
		text("BREAK OUT!", 254, 150);
		textFont(titleFont);
		fill(255);
		text("Hello " + player.getUsername(), 254, 340);
		textFont(titleFont);
		textSize(18);
		fill(255);
		text("Click mouse to start game...", 254, 370);
		text("Use the Keyboard to control Paddle...", 254, 400);
	}

	// Game over screen text method
	public void end() {
		textAlign(CENTER);
		textFont(titleFont);
		fill(255);
		textSize(30);
		text("GAME OVER!!!", 254, 300);
		text(player.getUsername(), 254, 335);
		textSize(24);
		text("Score: " + player.getScore(), 254, 430);
		text("Lives left: " + player.getLives(), 254, 460);
		output.quit(); // Stop Output write thread
		noLoop();

	}
	
	public void nextLevel() {
		textAlign(CENTER);
		textFont(titleFont);
		fill(255);
		textSize(30);
		text("Level Complete", 254, 300);
		text(player.getUsername(), 254, 340);
		text("Click mouse for next level", 254, 380);
	}
	
	public void level2() {
		loop();
		
		for(i = 0; i <= 35; i++) {
			Block brick = new Block(x[i], y[i], this);
			blocks.add(brick);
			System.out.println("Block added to blocks list b" + i);
		}
		
		for (i = 0; i < blocks.size(); i++) {
			Block block = (Block) blocks.get(i);
			fill(r[i], g[i], b[i]);
			block.display();

			// detect ball collision with brick
			if (block.intersect(ball)) {
				ball.ySpeed *= -1;
				blocks.remove(block);
				score = score + 5;
				player.setScore(score);
			}
		}
		
	}

	// game stats text method
	public void gameData() {
		fill(255);
		textFont(statFont);
		textSize(12);
		textAlign(CENTER);
		text(player.getUsername(), 30, 15);
		text("Score:  ", 210, 15);
		text(player.getScore(), 264, 15);
		text("Lives: ", 455, 15);
		text(player.getLives() / 2, 485, 15);
	}
	
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "GameClient" });
	}

}// end of Client()

