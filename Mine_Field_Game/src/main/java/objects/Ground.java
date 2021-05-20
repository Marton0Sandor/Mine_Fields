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
		return step(place[y][x]);
	}
	
	public int step(Field field) {
		int x = field.getX();
		int y = field.getY();
		
		if (x<0 || x>=place.length || y<0 || y>=place[0].length) return 0;
		
		if (field.isClicked() || field.isMarked())
		{
			return 0;
		}
		
		if (field.isBoom())
		{
			field.setClicked(true);
			mines--;
			lives--;
			return -1;
		}
		
		field.setClicked(true);
		tiles--;
		
		if (isSafeField(field))
		{
			for (int i=0; i<8; i++) {
				Field neighbour = getNeighbour(field, i);
				if (neighbour != null && !neighbour.isMarked()) step(neighbour);
			}
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

	public Field getNeighbour(Field field, int n) {
		int x = field.getX();
		int y = field.getY();

		switch (n)
		{
		case 0: y--; x--; break;
		case 1:      x--; break;
		case 2: y++; x--; break;
		
		case 3: y--; break;
		case 4: y++; break;
		
		case 5: y--; x++; break;
		case 6:      x++; break;
		case 7: y++; x++; break;
		}

		if (x<0 || x>=place.length || y<0 || y>=place[0].length) return null;
		
		return place[y][x];
	}
	
	public boolean isSafeField(Field field) {
		int flags = 0;

		for (int i=0; i<8; i++) {
			Field neighbour = getNeighbour(field, i);
			if (neighbour != null && neighbour.isMarked()) flags++;
		}

		return (flags == field.getNeighbours());
	}
		
}
