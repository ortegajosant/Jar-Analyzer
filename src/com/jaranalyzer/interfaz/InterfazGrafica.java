package com.jaranalyzer.interfaz;

import java.io.File;

import javax.swing.JFrame;

import com.jaranalyzer.adapter.Adapter;
import com.jaranalyzer.dependencias.Dependencia;

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
import sun.tools.jar.resources.jar;

public class InterfazGrafica extends Application {
	
    	private Dependencia jarGrafo;
    	private Pane layout = new Pane();
    	private Button btnagregarJAR = new Button("A�adir JAR");
    	private Button btnGrafo = new Button("Grafo");
    	private ListView lstRankingDep = new ListView();
    	private ListView lstRankingRef = new ListView();
    	private Label lblRankDep = new Label("Ranking de Dependencias");
    	private Label lblRankRef = new Label("Ranking de Referencias");
    	private Label jarActual = new Label();
    	private Label lblJar = new Label();

    
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
        primaryStage.setTitle("JarAnalyzer");

        btnagregarJAR.setLayoutX(350);
        btnagregarJAR.setLayoutY(270);
        
        btnGrafo.setLayoutX(300);
        btnGrafo.setLayoutY(270);
                      
        //Listas
                        
        lblRankDep.setLayoutX(27);
        lblRankDep.setLayoutY(7);
        lblRankDep.setFont(Font.font("Centurie Gothic", 14));
        lblRankDep.setTextFill(Color.CHOCOLATE);
        lstRankingDep.getItems().add("Hello World");
        lstRankingDep.setLayoutX(10);
        lstRankingDep.setLayoutY(25);
        lstRankingDep.setMaxWidth(200);
        lstRankingDep.setMaxHeight(200);
        
        lblRankRef.setLayoutX(240);
        lblRankRef.setLayoutY(7);
        lblRankRef.setFont(Font.font("Centurie Gothic", 14));
        lblRankRef.setTextFill(Color.CHOCOLATE);
        lstRankingRef.getItems().add("Hello World");
        lstRankingRef.setLayoutX(220);
        lstRankingRef.setLayoutY(25);
        lstRankingRef.setMaxWidth(200);
        lstRankingRef.setMaxHeight(200);
        
        jarActual.setLayoutX(100);
        jarActual.setLayoutY(240);
    	jarActual.setText("(Ninguno)");
        jarActual.setFont(Font.font("Centurie Gothic", 12));
        jarActual.setTextFill(Color.BLACK);
        
        lblJar.setLayoutX(5);
        lblJar.setLayoutY(240);
        lblJar.setText("Jar Seleccionado:");
        lblJar.setFont(Font.font("Centurie Gothic", 12));
        lblJar.setTextFill(Color.BLACK);
        
        //Funci�n del bot�n de agregar jar
        btnagregarJAR.setOnAction(new EventHandler<ActionEvent>(){  
            @Override
            public void handle(ActionEvent event){
                Stage stage = new Stage();
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("Add Files(*.jar)", "*.jar");
                fileChooser.getExtensionFilters().add(extension);
                File file = fileChooser.showOpenDialog(stage);
                if(file != null) {
	                	jarActual.setText(file.toString());
	                	jarGrafo = new Dependencia(file.toString());
	                	jarGrafo.generarGrafoJars();
              }
            }
        });
        //Fin de la funci�n
        
        
        //Funcion del boton para mostrar el grafo
        btnGrafo.setOnAction(new EventHandler<ActionEvent>(){  
            @Override
            public void handle(ActionEvent event){
            	if(jarGrafo != null) {
            		DibujaGrafo dibujar = new DibujaGrafo();
            		dibujar.DibujarGrafo(jarGrafo);
            	}                 	

            }});
       //Fin de la funci�n      

                
        layout.getChildren().add(lstRankingRef);
		layout.getChildren().add(lstRankingDep);
        layout.getChildren().add(lblRankDep);
        layout.getChildren().add(lblRankRef);
        layout.getChildren().addAll(jarActual, lblJar);
        layout.getChildren().addAll(btnagregarJAR, btnGrafo);
		Scene scene = new Scene(layout, 430, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
        
}
