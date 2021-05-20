package groupProjectMineField;

import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameLength extends TimerTask {

	private int seconds = 0;

	@FXML
	private Label duration = null;
	
	public void run() {
        seconds++;
        
        if (duration != null) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                	duration.setText(String.valueOf(seconds));
                }
            });
        }
     }
	
	public void setDurationLabel(Label label) {
		duration = label;
	}

	public int getDuration() {
		return seconds;
	}
}
