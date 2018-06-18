package com.jaranalyzer.pruebas;

import java.io.IOException;

import com.jaranalyzer.dependencias.Dependencia;
import com.jaranalyzer.dependencias.DependenciaInterna;

public class PruebasJar {

	@SuppressWarnings("resource")
    public static void main(String[] args) {

		Dependencia dependencia = new Dependencia("C:\\Users\\jorte\\Desktop\\InvadersGame.jar");
		dependencia.generarGrafoJars();
		
		dependencia.getGrafo().imprimirGrafo();
		
//		try {
//			DependenciaInterna interna = new DependenciaInterna(dependencia.getJar(), dependencia.obtenerDependenciaInterna("gdx-1.9.8.jar"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
    }
}
