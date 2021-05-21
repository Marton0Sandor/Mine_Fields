package com.minefield.java.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.minefield.java.jpa.config.AppConfig;
import com.minefield.java.jpa.init.DataBaseInitializer;
import com.minefield.java.jpa.repository.MineRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    
	public static ApplicationContext context;
	
    @Override
    public void start(Stage stage) throws Exception {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        DataBaseInitializer db = context.getBean(DataBaseInitializer.class);
        db.init();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main_scene.fxml"));
        loader.setControllerFactory(context::getBean);
        showStage(loader.load(), stage);
    }
    
    
    private void showStage(Parent root, Stage stage) {
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Mine Fields");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
