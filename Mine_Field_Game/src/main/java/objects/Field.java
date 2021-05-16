package objects;

public class Field {
	public boolean boom; //mine --> true, ground --> false 
	public int neighbours; //number of mines eround it. If this is a mine its -1.
	public boolean clicked;//false = have not been stepped on it. true have been stepped on it.
	public Field(boolean boom)
	{
		this.boom = boom;
		neighbours = 0;
		clicked = false;
		if(boom) {
			neighbours = 9;
		}
	}
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
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
}
