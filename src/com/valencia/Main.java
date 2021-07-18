package com.valencia;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.geometry.Pos;


/**
 * The main class of the program.
 *
 */
public class Main extends Application {
	
	static TextAnalyzer textAnalyzer;
	
	/**
	 * The main method of the program. Contains high level methods.
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String args[]) throws Exception {
		
		textAnalyzer = new TextAnalyzer();
		
		launch(args);
		
		Database.getConnection().close();
		System.out.println("\nClosing program...");
		System.exit(0);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		
		
		primaryStage.setTitle("Word Occurences Program");
		Button analyzeButton = new Button("Analyze File");
		Label mainLabel1 = new Label("File:");
		Label mainLabel2 = new Label("File:");
		Label book1 = new Label("The Project Gutenberg eBook of The Raven, by Edgar Allan Poe.htm");
		Label book2 = new Label("The Project Gutenberg eBook of The Raven, by Edgar Allan Poe.htm");
		Label instructions1 = new Label("Click the Analyze File button to find the most frequent words in the file");
		Label instructions2 = new Label("The 20 most frequent words in the file are displayed above");
		
		book1.setStyle("-fx-font-weight: bold;");
		book2.setStyle("-fx-font-weight: bold;");
		
		analyzeButton.setMaxSize(200, 100);
		analyzeButton.setStyle("-fx-font-size: 30");
		
		VBox vbox1 = new VBox(5);
		vbox1.getChildren().addAll(mainLabel1,book1);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setStyle("-fx-background-color: #819ECC;");
		
		VBox vbox2 = new VBox(5);
		vbox2.getChildren().addAll(mainLabel2,book2);
		vbox2.setAlignment(Pos.CENTER);
		vbox2.setStyle("-fx-background-color: #819ECC;");
		
		HBox hbox1 = new HBox();
		hbox1.getChildren().addAll(instructions1);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setStyle("-fx-background-color: #8A8888;");
		
		HBox hbox2 = new HBox();
		hbox2.getChildren().addAll(instructions2);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setStyle("-fx-background-color: #8A8888;");
		
		
		
		TextArea occurences = new TextArea();
		occurences.setEditable(false);
		
		BorderPane borderpane1 = new BorderPane();
		borderpane1.setTop(vbox1);
		borderpane1.setCenter(analyzeButton);
		borderpane1.setBottom(hbox1);
		BorderPane.setAlignment(mainLabel1, Pos.CENTER);
		
		BorderPane borderpane2 = new BorderPane();
		borderpane2.setTop(vbox2);
		borderpane2.setCenter(occurences);
		borderpane2.setBottom(hbox2);
		BorderPane.setAlignment(mainLabel2, Pos.CENTER);

		Scene scene = new Scene(borderpane1, 500, 400);
		Scene scene2 = new Scene(borderpane2,500,400);
		
		//analyzeButton.setOnAction(e -> primaryStage.setScene(scene2));
		
		analyzeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					
					textAnalyzer.run();
				} catch (Exception e) {
					
					System.out.println(e.getMessage());
				}
				
				System.out.println("Getting words and frequency from the database...");
				for (int i = 0; i < 20; i++) {
					
					try {
						
						occurences.appendText(textAnalyzer.getLines() + "\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				primaryStage.setScene(scene2);
				System.out.println("Success!");
			}
			
		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
