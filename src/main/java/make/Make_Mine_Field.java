package make;

import Objects.Field;
import Objects.Ground;

public class Make_Mine_Field {
	int easy = 8, normal = 16, hard = 32;
	public int lives;
	public Ground mineField;

	public Make_Mine_Field(int dificulty) {
		mineField = setUpMineField(dificulty);
	}

	public Ground setUpMineField(int dificulty) {
		Ground field = new Ground();
		field.setDificulty(dificulty);
		;
		switch (field.getDificulty()) {
		case 1:
			field.setMines((int) (Math.round(Math.random() * 10) % 9) + 8);
			lives = 8;
			break;
		case 2:
			field.setMines((int) (Math.round(Math.random() * 10) % 17) + 16);
			lives = 4;
			break;
		case 3:
			field.setMines((int) (Math.round(Math.random() * 10) % 33) + 32);
			lives = 2;
			break;
		}
		this.layMines(field);
		return field;
	}

	void layMines(Ground field) {

		Field[][] mineField;
		switch (field.getDificulty()) {
		case 2:
			mineField = (new Field[normal][normal]);
			break;
		case 3:
			mineField = (new Field[hard][hard]);
			break;
		default:
			mineField = (new Field[easy][easy]);
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
					mineField[i][j] = new Field(true);
				} else {

					mineField[i][j] = new Field(false);
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
//TESTING ONLY!!!!TESTING ONLY!!!!TESTING ONLY!!!!TESTING ONLY!!!!TESTING ONLY!!!!TESTING ONLY!!!!TESTING ONLY!!!!TESTING ONLY!!!!TESTING ONLY!!!!TESTING ONLY!!!!TESTING ONLY!!!!
	public void print(boolean p, int mode) {
		Field[][] tmp = mineField.getPlace();
		System.out.println("mines:" + mineField.getMines());
		int width = tmp.length;
		int height = tmp[0].length;
		if (p && mode == 0) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					System.out.print((tmp[i][j].isBoom())?"X":"O");
				}
				System.out.println("");
			}
		}
		if (p && mode == 1) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					System.out.print(tmp[i][j].getNeighbours());
				}
				System.out.println("");
			}
		}
	}
}
