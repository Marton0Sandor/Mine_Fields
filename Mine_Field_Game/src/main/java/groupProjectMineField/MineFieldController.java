package groupProjectMineField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import make.MakeMineField;
import objects.Field;

public class MineFieldController {
	@FXML
	private GridPane grid;
	
	@FXML
	private Label lives;
	
	@FXML
	private Label mines;
	
	private MakeMineField mineField;

	public void MineFieldInit(int difficulty) {
		mineField = new MakeMineField(difficulty);
		mineField.print(true, 0);
	}

	public void PopulateField(Stage stage) {

		int width = mineField.getWidth();
		int height = mineField.getHeight();
		
		Field[][] tmp = mineField.getMineField().getPlace();
		
		double buttonSize = 25.0;
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				Button b = new Button();
				b.setPrefSize(buttonSize, buttonSize);
				b.setOnAction(new EventHandler<ActionEvent>() {
				    @Override public void handle(ActionEvent e) { handleStep(e); }
					});

				b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				    @Override public void handle(MouseEvent e) { handleClick(e); }
					});

				b.setUserData(tmp[y][x]);
				
				GridPane.setConstraints(b, x, y);
				grid.getChildren().add(b);
			}
		}
		
		lives.setText(String.valueOf(mineField.getMineField().getLives()));
		mines.setText(String.valueOf(mineField.getMineField().getMines()));
	}

    @FXML
    private void handleStep(ActionEvent event) {
    	Button control = (Button)event.getSource();
    	Field  field = (Field)control.getUserData();
    	
    	if (field.isMarked()) return;
    	
    	int ret = mineField.getMineField().step(field);
    	if (ret == 0) return;

		lives.setText(String.valueOf(mineField.getMineField().getLives()));
		mines.setText(String.valueOf(mineField.getMineField().getMines()));

		updateGround();
	}

    @FXML
    private void handleClick(MouseEvent event) {
    	if (MouseButton.SECONDARY != event.getButton()) {
    		return;
    	}
    	
    	Button control = (Button)event.getSource();
    	Field  field = (Field)control.getUserData();
    	
    	//mineField.getMineField().markMine(field.x, field.y);
    	field.toggleMine();

		updateGround();
    }
    
    private void updateGround() {
		for (int i = 0; i < grid.getChildren().size(); i++) {
			Button b = (Button)grid.getChildren().get(i);
			if (b.isDisabled()) continue;
	    	Field  f = (Field)b.getUserData();
			if (((Field)b.getUserData()).isClicked())
			{
		    	if (f.isBoom()) {
		    		b.setText("*");
		    	} else {
		    		int count = f.getNeighbours();
		    		if (count > 0) b.setText(String.valueOf(count));
		    	}
			
				b.setDisable(true);
				continue;
			}

			if (f.isMarked()) {
			// put flag
				Image img = new Image("flag.png");
      			ImageView view = new ImageView(img);
      			b.setGraphic(view);			
			} else {
			// remove flag
				b.setGraphic(null);
			}			
		}
		mineField.print(true, 0);
    }
}
