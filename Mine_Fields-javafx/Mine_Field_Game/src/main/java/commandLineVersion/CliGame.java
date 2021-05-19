package commandLineVersion;

import java.util.Scanner;

import make.MakeMineField;
import objects.Field;
import objects.Ground;

public class CliGame {

	public static void main(String[] args) {
		int dificulty = 1;
		boolean read = false;
		Scanner con = new Scanner(System.in);
		System.out.print("Hello.\n"
						 + "This is an implementation of the classic mine field game in CLI.\n"
						 + "The rules are easy to learn.\n"
						 + "First of all zou have to chose a difficulty:\n"
						 + "(0)-exit   -----> you don't want to play.\n"
						 + "(1)-easy   -----> 8x8    6 lives min 16    max 24   mine.\n"
						 + "(2)-medium -----> 16x16  4 lives min 64    max 128  mine.\n"
						 + "(3)-hard   -----> 32x32  2 lives min 256   max 512  mine.\n"
						 + "(4)-random -----> we chose one of the options above.\n"
						 + "your choice is:");
		do {
			try {
				dificulty = Integer.parseInt(con.nextLine());
				if(dificulty < 1 || dificulty > 4) {
					read = true;
					System.out.print("Plese use just the listed options:\n"
							 + "(0)-exit   -----> you don't want to play.\n"
							 + "(1)-easy   -----> 8x8    6 lives min 16    max 24   mine.\n"
							 + "(2)-medium -----> 16x16  4 lives min 64    max 128  mine.\n"
							 + "(3)-hard   -----> 32x32  2 lives min 256   max 512  mine.\n"
							 + "(4)-random -----> we chose one of the options above.\n"
							 + "your choice is:");
				}
				else if(dificulty >= 1 && dificulty < 4) {
					read = false;
					break;
				}
				else if(dificulty == 4) {
					dificulty = (int) (Math.round(Math.random() * 10) % (2 + 1)) + 1;
					read = false;
				}
			}catch (NumberFormatException e) {
				read = true;
				System.out.print("The impust can only be Numbers:\n"
						+ "(0)-exit   -----> you don't want to play.\n"
						+ "(1)-easy   -----> 8x8    6 lives min 16    max 24   mine.\n"
						+ "(2)-medium -----> 16x16  4 lives min 64    max 128  mine.\n"
						+ "(3)-hard   -----> 32x32  2 lives min 256   max 512  mine.\n"
						+ "(4)-random -----> we chose one of the options above.\n"
						+ "your choice is:");
			}
		}while(read);
		MakeMineField m = new MakeMineField(dificulty);
		Ground game = m.getMineField(); 
		int lives = game.getLives();
		int mines = game.getMines();
		int points = 0;
		int tiles = game.getTiles();
		int x = 0,y = 0;
		int width = (game.getPlace().length - 1);
		CliGame.print(game ,true, 2);
		System.out.println("");
		CliGame.print(m.mineField, true, 1);
		String coordinates = "";
		System.out.println("");
		
		read = true;
		System.out.println("Number of lives left:" + lives);
		System.out.println("Number of mines left:" + mines);
		System.out.println("Number of tiles left:" + tiles);
		System.out.println("Add the coordinates of tile what you want to step on?\n"
				+ "Fist is the x and then the y separated by a ',' (comma).\n"
				+ "The range of them from 0 to " + width + ".\n"
				+ "They become higher from left to right and from top to bottom.\n"
				+ "If you want to exit you have to write in exit.\n"
				+ "You have chousen:");
		while (lives > 0 && tiles > 0) {
			do {
				try {
					coordinates = con.nextLine();
					String[] st = coordinates.split(",");
					if(coordinates.equals("exit")) {
						tiles = 0;
						read = false;
						System.out.println("Your points: " + points + "\n");
						return;
					}
					if(st.length == 1) {
						read = true;
						System.out.print("Plese use ',' (comma) as a separator between the x and y coordinates\n"
								+ "Fist is the x and then the y separated by a ',' (comma).\n"
								+ "The range of them from 0 to " + width + ".\n"
								+ "They become higher from left to right and from top to bottom.\n"
								+ "If you want to exit you have to write in exit.\n"
								+ "You have chousen:");
						continue;
					}
					x = Integer.parseInt(st[0]);
					y = Integer.parseInt(st[1]);
					if(x >= 0 && x <= width && y >= 0 && y <= width) {
						read = false;
						break;
					}
					else {
						System.out.print("Plese use numbers from the range of them from 0 to " + width + "\n"
								+ "Fist is the x and then the y separated by a ',' (comma).\n"
								+ "They become higher from left to right and from top to bottom.\n"
								+ "If you want to exit you have to write in exit.\n"
								+ "You have chousen:");
						read = true;
						continue;
					}
				}catch (NumberFormatException e) {
					read = true;
					System.out.print("The impust can only be Numbers:\n"
							+ "Fist is the x and then the y separated by a ',' (comma).\n"
							+ "The range of them from 0 to " + width + ".\n"
							+ "They become higher from left to right and from top to bottom.\n"
							+ "If you want to exit you have to write in 'exit'.\n"
							+ "You have chousen:");
					continue;
				}
			}while(read);
			int tmp = game.step(x, y);
			switch(tmp) {
				case  0: System.out.println("This tile has been stepped\n"
						 + "Try another one.");
						 break;
						 
				case -1: points++; lives = game.getLives(); 
						 System.out.println("This tile was a mine.");
						 break;
				default: points += (5 * tmp);
						 System.out.println("This tile was not a mine.");
						 tiles = game.getTiles();
						 break;
			}
			System.out.println("");
			System.out.println("Number of lives left:" + lives);
			System.out.println("Number of mines left:" + mines);
			System.out.println("Number of tiles left:" + tiles);
			System.out.print("You have chousen:");
			CliGame.print(game ,true, 2);
		}
			
		//CliGame.print(m.mineField, true, 1);
		System.out.println("Your points: " + points + "\n");
		
		
		con.close();
	}
	public static void print(Ground mineField, boolean p, int mode) {
		Field[][] tmp = mineField.getPlace();
		System.out.println("The number of mines:" + mineField.getMines());
		int width = tmp.length;
		int height = tmp[0].length;
		if (p && mode == 0) {
			System.out.print("  ");
			for (int i = 0; i < width; i++) {
				System.out.print(i + " ");
			}
			System.out.println("");
			for (int i = 0; i < width; i++) {
				System.out.print(i + " ");
				for (int j = 0; j < height; j++) {
					System.out.print((tmp[i][j].isBoom())?"X ":"O ");
				}
				System.out.println("");
			}
		}
		else if (p && mode == 1) {
			System.out.print("  ");
			for (int i = 0; i < width; i++) {
				System.out.print(i + " ");
			}
			System.out.println("");
			for (int i = 0; i < width; i++) {
				System.out.print(i + " ");
				for (int j = 0; j < height; j++) {
					System.out.print(tmp[i][j].getNeighbours() + " ");
				}
				System.out.println("");
			}
		}
		else if (p && mode == 2) {
			System.out.print("  ");
			for (int i = 0; i < width; i++) {
				System.out.print(i + " ");
			}
			System.out.println("");
			for (int i = 0; i < width; i++) {
				System.out.print(i + " ");
				for (int j = 0; j < height; j++) {
					System.out.print(((tmp[i][j].isClicked())?
							((tmp[i][j].isBoom())?"X ":
							tmp[i][j].getNeighbours() + (" ")):
							((tmp[i][j].isBoom())?"X ":
							"# ")));
				}
				System.out.println("");
			}
		}
	}

}
