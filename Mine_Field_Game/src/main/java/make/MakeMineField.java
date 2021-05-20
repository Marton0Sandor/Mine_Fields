package make;

import objects.Field;
import objects.Ground;

public class MakeMineField {
	public static int EASY_SIZE = 8, NORMAL_SIZE = 16, HARD_SIZE = 32;
	public static int EASY_LIVES = 6, NORMAL_LIVES = 4, HARD_LIVES = 2;
	public static int EASY_MINES_MIN = 16, NORMAL_MINES_MIN = 64, HARD_MINES_MIN = 256;
	public static int EASY_MINES_MAX = 8, NORMAL_MINES_MAX = 64, HARD_MINES_MAX = 256;
	public static int EASY = 1, NORMAL = 2, HARD = 3;

	public Ground mineField;

	public MakeMineField(int dificulty) {
		mineField = setUpMineField(dificulty);
	}

	public Ground getMineField() {
		return mineField;
	}

	public int getWidth() {
		return mineField.getPlace().length;
	}

	public int getHeight() {
		return mineField.getPlace()[0].length;
	}
	
	public void setMineField(Ground mineField) {
		this.mineField = mineField;
	}
	
	public Ground setUpMineField(int dificulty) {
		Ground field = new Ground();
		field.setDificulty(dificulty);
		;
		switch (field.getDificulty()) {
		case 1:
			field.setMines(generateRandomInt(EASY_MINES_MIN, EASY_MINES_MAX));
			field.setLives(EASY_LIVES);
			field.setTiles((EASY_SIZE * EASY_SIZE) - field.getMines());
			break;
		case 2:
			field.setMines(generateRandomInt(NORMAL_MINES_MIN, NORMAL_MINES_MAX));
			field.setLives(NORMAL_LIVES);
			field.setTiles((NORMAL_SIZE * NORMAL_SIZE) - field.getMines());
			break;
		case 3:
			field.setMines(generateRandomInt(HARD_MINES_MIN, HARD_MINES_MAX));
			field.setLives(HARD_LIVES);
			field.setTiles((HARD_SIZE * HARD_SIZE) - field.getMines());
			break;
		}
		this.layMines(field);
		return field;
	}

	void layMines(Ground field) {

		Field[][] mineField;
		switch (field.getDificulty()) {
		case 2:
			mineField = (new Field[NORMAL_SIZE][NORMAL_SIZE]);
			break;
		case 3:
			mineField = (new Field[HARD_SIZE][HARD_SIZE]);
			break;
		default:
			mineField = (new Field[EASY_SIZE][EASY_SIZE]);
			break;
		}
		int mines = field.getMines();
		int width = mineField.length;
		int height = mineField[0].length;
		int tmp;
		int neighbours;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tmp = (int) Math.round(Math.random() * 10) % 4;
				if (tmp == 0 && mines != 0) {
					mines--;
					mineField[i][j] = new Field(true, j, i);
				} else {
					mineField[i][j] = new Field(false, j, i);
				}
			}
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				neighbours = 0;
				if(!mineField[i][j].isBoom()) {
					for (int k = 0; k < 8; k++) {
						try {
							switch (k) {
							case 0:
								if (mineField[i - 1][j - 1].isBoom())
									neighbours++;
								break;
							case 1:
								if (mineField[i - 1][j].isBoom())
									neighbours++;
								break;
							case 2:
								if (mineField[i - 1][j + 1].isBoom())
									neighbours++;
								break;
							case 3:
								if (mineField[i][j - 1].isBoom())
									neighbours++;
								break;
							case 4:
								if (mineField[i][j + 1].isBoom())
									neighbours++;
								break;
							case 5:
								if (mineField[i + 1][j - 1].isBoom())
									neighbours++;
								break;
							case 6:
								if (mineField[i + 1][j].isBoom())
									neighbours++;
								break;
							case 7:
								if (mineField[i + 1][j + 1].isBoom())
									neighbours++;
								break;
							}
						} catch (IndexOutOfBoundsException e) {
							continue;
						}
					}
					mineField[i][j].setNeighbours(neighbours);
				}
			}
		}
		field.setPlace(mineField);
	}
	
	public int generateRandomInt(int min, int max) {
		return (int) (Math.round(Math.random() * 10) % (max + 1)) + min;
	}

	public void print(boolean p, int mode) {
		Field[][] tmp = mineField.getPlace();
		System.out.println("mines:" + mineField.getMines());
		int width = tmp.length;
		int height = tmp[0].length;
		if (p && mode == 0) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					System.out.print((tmp[i][j].isBoom())?"X ":(tmp[i][j].isMarked())?"! ":"O ");
				}
				System.out.println("");
			}
		}
		if (p && mode == 1) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					System.out.print(tmp[i][j].getNeighbours() + " ");
				}
				System.out.println("");
			}
		}
	}
}
