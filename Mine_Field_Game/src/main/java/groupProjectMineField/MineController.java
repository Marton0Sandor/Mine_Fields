package groupProjectMineField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import make.MakeMineField;
import javafx.scene.control.Button;

public class MineController {
	
	private int difficulty = 0;

	@FXML
    private Label description;

	@FXML
    private Button button;
	
	@FXML
	private TextField name;
	
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
		MakeMineField m = new MakeMineField(difficulty);
		m.print(true, 0);
       
    }
    
    @FXML
    private void handleEasy(ActionEvent event) {
        description.setText("Difficulty: Easy\nLives: 6\nField size: 8x8\nMine count: 8-16");
        difficulty = 1;
    }
    
    @FXML
    private void handleMedium(ActionEvent event) {
        description.setText("Difficulty: Medium\nLives: 4\nField size: 16x16\nMine count: 64");
        difficulty = 2;
    }
    
    @FXML
    private void handleHard(ActionEvent event) {
        description.setText("Difficulty: Hard\nLives: 2\nField size: 32x32\nMine count: 256");
        difficulty = 3;
    }

}
