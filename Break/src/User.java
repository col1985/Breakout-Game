/**
 * Author: Colum Bennett
 * Student No: 20044622
 * Purpose: Break Out Game User Class
 */
// Class to take in Username and score.
public class User {

	private String userName;
	private int lives = 6;
	private int score;

	// Default Constructor
	public User() {

		userName = "Player 1";
	}

	// OverLoaded Constructor
	public User(String startUser, int startScore, int startLives) {

		this.userName = startUser;
		this.setScore(startScore);
		this.setLives(startLives);
	}

	// Acessor Methods
	public String getUsername() {
		return userName;
	}

	public int getScore() {
		return score;
	}

	public int getLives() {
		return lives;
	}

	// Mutator Methods
	public void setUsername(String newUser) {
		userName = newUser;
	}

	public void setScore(int newScore) {
		score = newScore;
	}

	public void setLives(int newLives) {
		lives = newLives;
	}

} // end of User()

