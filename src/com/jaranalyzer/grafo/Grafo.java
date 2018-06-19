package com.jaranalyzer.grafo;

import com.jaranalyzer.listas.SimpleList;
import com.jaranalyzer.listas.SimpleNode;

/**
 * Genera un grafo direccionado
 * 
 * @author jorte
 *
 */
public class Grafo {

	private SimpleList<NodoGrafo> vertices;
	private boolean esConexo;

	/**
	 * Constructor
	 */
	public Grafo() {
		this.vertices = new SimpleList<>();
	}

	/**
	 * Constructor
	 * 
	 * @param vertice
	 */
	public Grafo(NodoGrafo vertice) {
		this.vertices = new SimpleList<>();
		vertices.add(new SimpleNode<NodoGrafo>(vertice));
	}

	/**
	 * Agrega un nuevo vertice
	 * 
	 * @param id
	 *            Nombre del vertice
	 * @param url
	 *            ubicación del vertice
	 * @return Boolean
	 */
	public boolean agregarVertice(String id, String url) {
		for (int i = 0; i < vertices.getLength(); i++) {
			if (vertices.find(i).getId().equals(id)) {
				return false;
			}
		}
		vertices.add(new SimpleNode<NodoGrafo>(new NodoGrafo(id, url)));
		return true;
	}

	/**
	 * Agrega una nueva arista
	 * 
	 * @param idVertice
	 *            Nombre del vertice
	 * @param idVerticeDestino
	 *            NOmbre del destino
	 * @return Boolean
	 */
	public boolean agregarArista(String idVertice, String idVerticeDestino) {
		for (int i = 0; i < vertices.getLength(); i++) {
			if (vertices.find(i).getId().equals(idVertice)) {
				for (int j = 0; j < vertices.getLength(); j++) {
					if (vertices.find(j).getId().equals(idVerticeDestino)) {
						vertices.find(i).setSiguienteArista(idVerticeDestino);
						return true;
					}
				}
				return false;
			}
		}
		return false;
	}

	/**
	 * Obtiene el numero de nodos
	 * 
	 * @return Integer
	 */
	public int getNumeroNodos() {
		return this.vertices.getLength();
	}

	/**
	 * Imprime el grafo
	 */
	public void imprimirGrafo() {
		for (int i = 0; i < vertices.getLength(); i++) {
			NodoGrafo temp = vertices.find(i);
			System.out.println(temp.getId());
			SimpleList<String> tempRelaciones = temp.getAristas();
			for (int j = 0; j < tempRelaciones.getLength(); j++) {
				System.out.print("-> " + tempRelaciones.find(j));
			}
			System.out.println();
		}
	}

	/**
	 * Verifica que el grafo sea conexo
	 * 
	 * @param inicial
	 *            Nombre del Vertice inicial
	 * @return Boolean
	 */
	public boolean esConexo() {
		SimpleList<NodoGrafo> visitados = new SimpleList<>();
		esConexoAux(vertices.getFirst().getDato().getId(), visitados, false);
		System.out.println(vertices.getLength());
		for (int i = 0; i < vertices.getLength(); i++) {
			if (!vertices.find(i).getVisitado()) {
				return false;
			}
		}
		if (vertices.getLength() != visitados.getLength()) {
			return false;
		}
		return true;
	}

	private void esConexoAux(String inicial, SimpleList<NodoGrafo> visitados, boolean visitado) {
		NodoGrafo verticeActual = buscarVertice(inicial);
		if (!verticeActual.getVisitado()) {
			verticeActual.setVisitado(visitado);
			for (int j = 0; j < verticeActual.getAristas().getLength(); j++) {
				if (!buscarVertice(verticeActual.getAristas().find(j)).getVisitado()) {
					if (!contieneVertice(visitados, verticeActual.getAristas().find(j))) {
						visitados.add(new SimpleNode<NodoGrafo>(new NodoGrafo(verticeActual.getAristas().find(j), "")));
					}
					esConexoAux(verticeActual.getAristas().find(j), visitados, true);
				}
			}
		}
		if (!vertices.contains(verticeActual)) {
			vertices.add(new SimpleNode<NodoGrafo>(verticeActual));
		}
	}
	
	private boolean contieneVertice(SimpleList<NodoGrafo> visitados, String nombre) {
		for (int i = 0; i < visitados.getLength(); i++) {
			if (visitados.find(i).getId().equals(nombre)) {
				return true;
			}	
		}
		return false;
	}

	/**
	 * Obtiene un vertice del grafo
	 * 
	 * @param vertice
	 *            Nombre del vertice
	 * @return NodoGrafo
	 */
	public NodoGrafo buscarVertice(String vertice) {
		for (int i = 0; i < vertices.getLength(); i++) {
			if (vertices.find(i).getId().equals(vertice)) {
				return vertices.find(i);
			}
		}
		return null;
	}

	private NodoGrafo verticeNoVisitado() {
		for (int i = 0; i < vertices.getLength(); i++) {
			if (!vertices.find(i).getVisitado()) {
				return vertices.find(i);
			}
		}
		return null;
	}

	/**
	 * Obtiene la lista con los vertices
	 * 
	 * @return SimpleList
	 */
	public SimpleList<NodoGrafo> getVertices() {
		return vertices;
	}
}
