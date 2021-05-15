package objects;

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

	public Ground() {
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

	public int getDificulty() {
		return dificulty;
	}

	public Field[][] getPlace() {
		return place;
	}

	public int getMines() {
		return mines;
	}

}
