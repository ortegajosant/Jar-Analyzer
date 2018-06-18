package com.jaranalyzer.pruebas;

import com.jaranalyzer.dependencias.DependenciasClases;
import com.jaranalyzer.grafo.Grafo;

public class PruebasDepClases {

	public PruebasDepClases() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		DependenciasClases test = new DependenciasClases("JarsdePrueba/InvadersGame.jar");
		
		//test.generarGrafoClases();
		Grafo newGrafo = test.getGrafoClases();
		newGrafo.imprimirGrafo();
	}

}
