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

	public Ground() {
	}
	
	public int step(int x, int y) {
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
		if(place[y][x].getNeighbours() == 0) {
			this.exermineNeighbours(y, x, "#");
		}
		return 1;
	}
	//TODO :CHANGE IT BACK TO PRIVATE
	public void exermineNeighbours(int y, int x, String s) {
		try {
			int nx = x, ny = y;
			//int db = 1000;
			for(int i = 0; i < 8 && db-- > 0; i++){
				
				try {
					switch (i) {
					case 0:
						nx = x - 1;
						ny = y - 1;
						break;
					case 1:
						nx = x;
						ny = y - 1;
						break; 
					case 2:
						nx = x + 1;
						ny = y - 1;
						break;
					case 3:
						nx = x - 1;
						ny = y ;
						break;
					case 4:
						nx = x + 1;
						ny = y ;
						break;
					case 5:
						nx = x - 1;
						ny = y + 1;
						break;
					case 6:
						nx = x;
						ny = y + 1;
						break;
					case 7:
						nx = x + 1;
						ny = y + 1;
						break;
	
					}
					Field tmp = place[ny][nx];
					if(!tmp.isBoom() && !tmp.isClicked())
						fw.write("Ground # " + i + " x: " + nx + " y " + ny + " Clicked?: " + tmp.isClicked() + " neighbours: "+ tmp.getNeighbours() + " " + s + System.lineSeparator());
						if(tmp.getNeighbours() > 0 && tmp.getNeighbours() < 9) {
							tiles--;
							place[ny][nx].setClicked(true);
							continue;
						}
						if(tmp.getNeighbours() == 0 && !tmp.isClicked())  {
							tiles--;
							
							place[ny][nx].setClicked(true);
							this.exermineNeighbours(ny, nx, s + "#");
							continue;
						}
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
				
			}
			Ground.fw.close();
		} catch (IOException e) {
			
		}
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
