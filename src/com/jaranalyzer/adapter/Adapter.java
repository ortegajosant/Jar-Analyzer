package com.jaranalyzer.adapter;

import com.jaranalyzer.dependencias.Dependencia;
import com.jaranalyzer.grafo.NodoGrafo;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;

public class Adapter {

	public static Graph grafoJung(Dependencia grafoJar) {
		
		int numNodos = grafoJar.getGrafo().getNumeroNodos();
		Graph nuevoGrafo = new DirectedSparseMultigraph<>();
		
		for(int i = 0; i<numNodos; i++) {
			nuevoGrafo.addVertex(grafoJar.getGrafo().getVertices().find(i).getId());
		}
		
		int cont = 0;
		for (int i = 0; i<numNodos; i++) {
			NodoGrafo nodo = grafoJar.getGrafo().getVertices().find(i);
			for (int j = 0; j<nodo.getAristas().getLength(); j++ ) {
				nuevoGrafo.addEdge(cont, nodo.getId(), nodo.getAristas().find(j));
				cont ++;
			}
		}
		
		return nuevoGrafo;
	}

}
