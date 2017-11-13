package kursJFX;

import kursJFX.Singleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main  extends Application {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		/* Singleton DB = Singleton.getInstance();
		   DB.selectAll();
		   DB.deleteSurname("Iks");
		   DB.selectAll();	   
		   DB.closeConnection();
		   DB.reopenConnection();
		   DB.selectAll();
	*/	
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		System.out.println(this.getClass().getResource("/FXML/StackPaneWindow.fxml"));
		loader.setLocation(this.getClass().getResource("/FXML/StackPaneWindow.fxml"));
		StackPane panel = loader.load();
		Scene scena = new Scene(panel);
		primaryStage.setScene(scena);
		primaryStage.show();
		
	}
	

}
