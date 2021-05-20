package objects;

public class Field {
	public boolean boom; //mine --> true, ground --> false 
	public int neighbours; //number of mines around it. If this is a mine its -1.
	public boolean clicked;//false = have not been stepped on it. true have been stepped on it.
	
	private int x;
	private int y;
	private boolean mark;
	
	public Field(boolean boom, int posX, int posY)
	{
		x = posX;
		y = posY;
		this.boom = boom;
		neighbours = 0;
		clicked = false;
		mark = false;
		if(boom) {
			neighbours = 9;
		}
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/*
	public int getType() {
		return type;
	}
	*/
	public int getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(int neighbours) {
		this.neighbours = neighbours;
	}
	public void setBoom(boolean boom) {
		this.boom = boom;
	}
	public boolean isBoom() {
		return boom;
	}
	public boolean isClicked() {
		return clicked;
	}
	/*
	public void setType(int type) {
		this.type = type;
	}
	*/
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public void toggleMine() {
		mark = !mark;
	}

	public boolean isMarked() {
		return mark;
	}
}
