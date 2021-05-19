package objects;

import java.io.FileWriter;
import java.io.IOException;

public class Ground {
	private int dificulty;// 1 -> easy, 2 -> normal, 3 -> hard
	/*
	 * It difers in the number of lives and a the number of fields
	 * 1 -> 8x8 6 lives min 16 max 24 mine
	 * 2 -> 16x16 4 lives min 64 max 128 mine
	 * 3 -> 32x32 2 lives min 256 max 512 mine
	 */
	public Field[][] place;// the matrix of fields. There are mines and safe ground;
	public int mines;// number of mines
	public int lives;
	public int tiles;
	static FileWriter fw;
	public int db = 10000;

	public Ground() {}
	
	public int step(int x, int y) {
		
		if (x<0 || x>=place.length || y<0 || y>=place[0].length) return 0;
		
		try {
			Ground.fw = new FileWriter("a.txt");
		} catch (Exception e) {
			
		}
		
		if(place[y][x].isClicked())
		{
			return 0;
		}
		if(place[y][x].isBoom())
		{
			place[y][x].setClicked(true);
			mines--;
			lives--;
			return -1;
		}
		place[y][x].setClicked(true);
		tiles--;
		
		if (place[y][x].getNeighbours() == 0)
		{
			step(x-1, y-1);
			step(x-1, y);
			step(x-1, y+1);
			step(x, y-1);
			step(x, y+1);
			step(x+1, y-1);
			step(x+1, y);
			step(x+1, y+1);
		}
		
		return 1;
	}
	
	public void setTiles(int tiles) {
		this.tiles = tiles;
	}
	
	public void setPlace(Field[][] place) {
		this.place = place;
	}

	public void setDificulty(int dificulty) {
		this.dificulty = dificulty;
	}

	public void setMines(int mines) {
		this.mines = mines;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getTiles() {
		return tiles;
	}
	
	public int getDificulty() {
		return dificulty;
	}

	public Field[][] getPlace() {
		return place;
	}

	public int getMines() {
		return mines;
	}
	
	public int getLives() {
		return lives;
	}
}
