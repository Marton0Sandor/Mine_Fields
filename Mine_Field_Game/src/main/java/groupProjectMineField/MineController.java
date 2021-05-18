package groupProjectMineField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class MineController {

	@FXML
    private Label label;

	@FXML
    private Button button;
	
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked start game!");
        label.setText("Start game!");
    }

}
