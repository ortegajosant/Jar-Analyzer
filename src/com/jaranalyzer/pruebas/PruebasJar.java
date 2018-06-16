package com.jaranalyzer.pruebas;

import com.jaranalyzer.dependencias.Dependencia;

public class PruebasJar {

	@SuppressWarnings("resource")
    public static void main(String[] args) {

		Dependencia dependencia = new Dependencia("C:\\Users\\jorte\\Desktop\\InvadersGame.jar");
		dependencia.generarGrafoJars();
		
		dependencia.getGrafo().imprimirGrafo();
    	
    }
}
