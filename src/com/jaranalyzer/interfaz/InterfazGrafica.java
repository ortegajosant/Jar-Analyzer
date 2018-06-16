package com.jaranalyzer.interfaz;
import java.io.File;

import javax.swing.JFrame;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InterfazGrafica extends Application {
    
    	Pane layout = new Pane();
    	Button agregarJAR = new Button("Añadir JAR");
        ListView lstRankingDep = new ListView();
        ListView lstRankingRef = new ListView();
        Label lblRankDep = new Label("Ranking de Dependencias");
        Label lblRankRef = new Label("Ranking de Referencias");
        Label jarActual = new Label();
    
	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
                primaryStage.setTitle("JarAnalyzer");
	
                agregarJAR.setLayoutX(990);
                agregarJAR.setLayoutY(570);
                              
                //Listas
                                
                lblRankDep.setLayoutX(850);
                lblRankDep.setLayoutY(7);
                lblRankDep.setFont(Font.font("Centurie Gothic", 15));
                lblRankDep.setTextFill(Color.GREEN);
                lstRankingDep.getItems().add("Hello World");
                lstRankingDep.setLayoutX(810);
                lstRankingDep.setLayoutY(30);
                lstRankingDep.setMaxWidth(300);
                lstRankingDep.setMaxHeight(250);
                
                lblRankRef.setLayoutX(850);
                lblRankRef.setLayoutY(290);
                lblRankRef.setFont(Font.font("Centurie Gothic", 15));
                lblRankRef.setTextFill(Color.GREEN);
                lstRankingRef.getItems().add("Hello World");
                lstRankingRef.setLayoutX(810);
                lstRankingRef.setLayoutY(310);
                lstRankingRef.setMaxWidth(300);
                lstRankingRef.setMaxHeight(250);
                
                jarActual.setLayoutX(290);
                jarActual.setLayoutY(560);
                jarActual.setFont(Font.font("Centurie Gothic", 15));
                jarActual.setTextFill(Color.RED);
                
                
                //Función del botón de agregar jar
                agregarJAR.setOnAction(new EventHandler<ActionEvent>(){  
                    @Override
                    public void handle(ActionEvent event){
                        Stage stage = new Stage();
                        FileChooser fileChooser = new FileChooser();
                        FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("Add Files(*.jar)", "*.jar");
                        fileChooser.getExtensionFilters().add(extension);
                        File file = fileChooser.showOpenDialog(stage);
                        if(file != null) {
                        	jarActual.setText(file.toString());
                        }
                    }
                });
                //Fin de la función
        
		Graph<String, Integer> g = new DirectedSparseMultigraph();
		
		g.addVertex("Casa");
		g.addVertex("Escuela");
		g.addVertex("Super");
		g.addVertex("Farmacia");
		
		g.addEdge(1, "Casa", "Escuela");
		g.addEdge(2, "Casa", "Super");
		g.addEdge(3, "Casa", "Farmacia");
		g.addEdge(4, "Farmacia", "Super");
		
		JFrame frame = new JFrame();
		DibujaGrafo.DibujarGrafo(g, frame);
                

        layout.getChildren().add(lstRankingRef);
		layout.getChildren().add(lstRankingDep);
        layout.getChildren().add(lblRankDep);
        layout.getChildren().add(lblRankRef);
        layout.getChildren().add(jarActual);
		Scene scene = new Scene(layout, 1080, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
        
}
