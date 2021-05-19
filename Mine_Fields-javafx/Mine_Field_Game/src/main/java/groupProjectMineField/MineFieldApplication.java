package groupProjectMineField;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class MineFieldApplication {
	
	public static void main(String[] args) {
		// SpringApplication.run(MineFieldApplication.class, args);
		Application.launch(JavaFxApplication.class, args);
	}	
}
