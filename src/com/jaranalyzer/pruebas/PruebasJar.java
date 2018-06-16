package com.jaranalyzer.pruebas;

import com.jaranalyzer.dependencias.Dependencia;
import com.jaranalyzer.dependencias.DependenciaInterna;

public class PruebasJar {

	@SuppressWarnings("resource")
    public static void main(String[] args) {

		Dependencia dependencia = new Dependencia("C:\\Users\\jorte\\Desktop\\InvadersGame.jar");
		dependencia.generarGrafoJars();
		
		dependencia.getGrafo().imprimirGrafo();
		
		DependenciaInterna interna = new DependenciaInterna(dependencia.getJar(), dependencia.obtenerDependenciaInterna("gdx-box2d-1.9.8.jar"));
    	
    }
}
