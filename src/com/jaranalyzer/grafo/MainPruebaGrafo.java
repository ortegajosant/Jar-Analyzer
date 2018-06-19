package com.jaranalyzer.grafo;

public class MainPruebaGrafo {
	public static void main(String [] args) {
		Grafo grafo = new Grafo();
		
		grafo.agregarVertice("a", "1");
		grafo.agregarVertice("b", "1");
		grafo.agregarVertice("c", "1");
		grafo.agregarVertice("d", "1");
		grafo.agregarVertice("e", "1");
		grafo.agregarVertice("f", "1");
		
		grafo.agregarArista("a", "b");
		grafo.agregarArista("b", "c");
		grafo.agregarArista("c", "d");
		grafo.agregarArista("d", "e");
		grafo.agregarArista("e", "f");
		grafo.agregarArista("f", "e");
		
		grafo.imprimirGrafo();
		System.out.println(grafo.esConexo());
	}
}
