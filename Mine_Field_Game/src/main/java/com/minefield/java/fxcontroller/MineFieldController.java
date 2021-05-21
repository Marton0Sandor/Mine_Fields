package com.minefield.java.fxcontroller;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minefield.java.jpa.domain.TopScore;
import com.minefield.java.jpa.repository.MineRepository;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

@Component
public class MineFieldController {

	@Autowired
	private MineRepository mineRepository;
	
	@FXML
	private Window window;
	
	@FXML
	private GridPane grid;
	
	@FXML
	private Label lives;
	
	@FXML
	private Label mines;
	
	@FXML
	private Label duration;
	
	private MakeMineField mineField;
	private boolean isOver;
    private boolean isLoosing;
    private boolean isFirstStep;
    private Timer timer;
    private GameLength stopper;
    private String name;
    
    MineFieldController(MineRepository repo) {
    	mineRepository = repo;
    }
    
	public void MineFieldInit(int difficulty) {
		mineField = new MakeMineField(difficulty);
		isOver = false;
		isLoosing = true;
		isFirstStep = true;
		timer = new Timer();
	
		mineField.print(true, 0);
		
		if (mineRepository == null) {
			System.out.println("ERROR : userRepository == null");
		}
	}

	public void setPlayer(String str) {
		name = str;
	}
	
	public void PopulateField(Stage stage) {

		window = stage.getScene().getWindow();
		
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
				b.setMaxSize(buttonSize, buttonSize);
				b.setMinSize(buttonSize, buttonSize);
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
    	if (isOver) return;
    	
    	Button control = (Button)event.getSource();
    	Field  field = (Field)control.getUserData();
    	
    	if (field.isMarked()) return;
    	
    	if (isFirstStep) {
    		isFirstStep = false;
    		stopper = new GameLength();
    		stopper.setDurationLabel(duration);
    		timer.schedule(stopper, 0, 1000);    		
    	}
    	
    	int ret = mineField.getMineField().step(field);
    	if (ret == 0) return;

		lives.setText(String.valueOf(mineField.getMineField().getLives()));
		mines.setText(String.valueOf(mineField.getMineField().getMines()));

		updateGround();
	}

    @FXML
    private void handleClick(MouseEvent event) {
    	if (isOver) return;
    	if (MouseButton.SECONDARY != event.getButton()) {
    		return;
    	}
    	
    	Button control = (Button)event.getSource();
    	Field  field = (Field)control.getUserData();
    	
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

		isOver = isGameOver();
		if (isOver) openResult();
		
		mineField.print(true, 0);
    }
    
    private boolean isGameOver() {
    	if (mineField.getMineField().getLives() == 0) return true;

		for (int i = 0; i < grid.getChildren().size(); i++) {
			Button b = (Button)grid.getChildren().get(i);
			if (b.isDisabled()) continue;
         	Field  f = (Field)b.getUserData();
         	if (!f.isBoom()) return false;
		}
		
		isLoosing = false;
    	return true;
    }
    
    private void openResult() {
    	
    	timer.cancel();

    	if (!isLoosing) {
    		// stopper.getDuration();
    		// mineField.getMineField().getDificulty();
    		// name
    		System.out.println(name + " - " + mineField.getMineField().getDificulty() + " - " + stopper.getDuration() + " sec");

    		TopScore result = new TopScore();
    		result.setName(name);
    		result.setDiff(mineField.getMineField().getDificulty());
    		result.setDuration(stopper.getDuration());

    		mineRepository.save(result);
    	}
    	
        try {
        	FXMLLoader fxmlLoader = new FXMLLoader();
        	fxmlLoader.setLocation(getClass().getResource("/GameOver.fxml"));

        	GameOverController ctrl = new GameOverController();
        	fxmlLoader.setController(ctrl);
        	
            Stage stage = new Stage();
            stage.setTitle("Mine Fields");
            stage.setScene(new Scene(fxmlLoader.load()));
            
        	ctrl.setParent(window);

            if (isLoosing) {
            	ctrl.resultText("Game Over!");
            } else {
            	ctrl.resultText("You survived!");
            }

            stage.show();
 //         ((Node)(event.getSource())).getScene().getWindow().hide();
        }
	    catch (Exception e) {
	        e.printStackTrace();
	    }      
    }
}
