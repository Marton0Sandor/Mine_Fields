package groupProjectMineField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Window;

public class GameOverController {

	@FXML
    private Label result;

	@FXML
	private Window parent;
	
    @FXML
    private void handleClose(ActionEvent event) {
    	((Node)(event.getSource())).getScene().getWindow().hide();
    	parent.hide();
    }
    
    public void setParent(Window wnd) {
    	parent = wnd;
    }

    public void resultText(String str) {
    	result.setText(str);
    }
}
