/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolas Penagos Montoya
 * nicolas.penagosm98@gmail.com
 * 
 * @author Valentina Zapata Zapata
 * valzapataz@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.client_aliens_vs_humans.model;


import com.example.client_aliens_vs_humans.tcpmodel.Direction;

/*
 * This class represents any of the two players.
 */
public class Player {

	// -------------------------------------
	// Constants
	// -------------------------------------
	public final static int PLAYER1 = 0;
	public final static int PLAYER2 = 1;

	// -------------------------------------
	// Attributes
	// -------------------------------------
	private int playerNumber;
	private int minI;
	private int maxI;
	private int minJ;
	private int maxJ;

	private Coordinate currentPos;
	private Coordinate drawPos;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public Player(int playerNumber) {

		this.playerNumber = playerNumber;
		
		this.minI = 0;
		this.maxI = 4;
		
		if (playerNumber == PLAYER1) {

			this.minJ = 0;
			this.maxJ = 3;
			this.currentPos = new Coordinate(0, 0);
			this.drawPos = new Coordinate(207,157);

		} else if (playerNumber == PLAYER2) {

			this.minJ = 5;
			this.maxJ = 8;
			this.currentPos = new Coordinate(8, 4);
			this.drawPos = new Coordinate(871, 513);

		}

	}

	public void updatePos(char direction) {

		int x = currentPos.getX();
		int y = currentPos.getY();

		if (playerNumber == PLAYER1) {

			switch (direction) {

			case Direction.UP:

				if (y - 1 >= minI)
					y--;

				break;

			case Direction.DOWN:

				if (y + 1 <= maxI)
					y++;

				break;
				
			case Direction.RIGHT:

				if (x + 1 <= maxJ)
					x++;

				break;
				
			case Direction.LEFT:

				if (x - 1 >= minJ)
					x--;

				break;

			}

		}else if(playerNumber == PLAYER2) {
			
			switch (direction) {

			case Direction.UP:

				if (y - 1 >= minI)
					y--;

				break;

			case Direction.DOWN:

				if (y + 1 <= maxI)
					y++;

				break;
				
			case Direction.RIGHT:

				if (x + 1 <= maxJ)
					x++;

				break;
				
			case Direction.LEFT:

				if (x - 1 >= minJ)
					x--;

				break;

			}
			
		}
		
		currentPos.setX(x);
		currentPos.setY(y);
		
	}

	// -------------------------------------
	// Getters and setters
	// -------------------------------------
	public Coordinate getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(Coordinate currentPos) {
		this.currentPos = currentPos;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public int getMinI() {
		return minI;
	}

	public void setMinI(int minI) {
		this.minI = minI;
	}

	public int getMaxI() {
		return maxI;
	}

	public void setMaxI(int maxI) {
		this.maxI = maxI;
	}

	public int getMinJ() {
		return minJ;
	}

	public void setMinJ(int minJ) {
		this.minJ = minJ;
	}

	public int getMaxJ() {
		return maxJ;
	}

	public void setMaxJ(int maxJ) {
		this.maxJ = maxJ;
	}

	public Coordinate getDrawPos() {
		return drawPos;
	}

	public void setDrawPos(Coordinate drawPos) {
		this.drawPos = drawPos;
	}

}
