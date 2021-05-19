package groupProjectMineField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        try {
        	FXMLLoader fxmlLoader = new FXMLLoader();
        	fxmlLoader.setLocation(getClass().getResource("/groupProjectMineField/MineField_scene.fxml"));

        	MineFieldController ctrl = new MineFieldController();
        	ctrl.MineFieldInit(difficulty);
        	fxmlLoader.setController(ctrl);
        	
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(fxmlLoader.load()));
            
            ctrl.PopulateField(stage);
            stage.show();
 //         ((Node)(event.getSource())).getScene().getWindow().hide();
        }
	    catch (Exception e) {
	        e.printStackTrace();
	    }      
    }
    
    @FXML
    private void handleEasy(ActionEvent event) {
        description.setText("Difficulty: Easy\nLives: 6\nField size: 8x8\nMine count: 16-24");
        difficulty = 1;
    }
    
    @FXML
    private void handleMedium(ActionEvent event) {
        description.setText("Difficulty: Medium\nLives: 4\nField size: 16x16\nMine count: 64-128");
        difficulty = 2;
    }
    
    @FXML
    private void handleHard(ActionEvent event) {
        description.setText("Difficulty: Hard\nLives: 2\nField size: 32x32\nMine count: 256-512");
        difficulty = 3;
    }

}
