package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox vbox = new VBox();
			
		    Label name = new Label("Sisesta enda nimi: ");
		    vbox.getChildren().add(name);
		    final TextField tekst = new TextField("");
		    vbox.getChildren().add(tekst);
		 
		    Label name2 = new Label("Sisesta graafik: ");
		    vbox.getChildren().add(name2);
		    final TextArea tekst2 = new TextArea("");
		    vbox.getChildren().add(tekst2);
		    
		    tekst2.setOnKeyPressed(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.ENTER) {
						passEvent(tekst2.getText(), tekst.getText());
					}
				}
		    	
		    });
		    
		    Button nupp1 = new Button("Done");
		    vbox.getChildren().add(nupp1);
		    
		    nupp1.setOnMousePressed(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent event) {
		        	passEvent(tekst2.getText(), tekst.getText());
		        }
		    });
			
			
			
			Scene scene = new Scene(vbox);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("GG");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void passEvent(String input, String name){
//		System.out.println(input + " " + name);
		try {
			
			main.java.Inputparser.parseInput(input, name);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
