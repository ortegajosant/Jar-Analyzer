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
	 *            ubicaci�n del vertice
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
	public boolean esConexo(String inicial) {

		SimpleList<NodoGrafo> visitados = new SimpleList<>();

		esConexoAux(inicial, visitados);

		for (int i = 0; i < visitados.getLength(); i++) {
			System.out.print(visitados.find(i).getId() + "-");
		}

		return visitados.getLength() == vertices.getLength();

	}

	private void esConexoAux(String inicial, SimpleList<NodoGrafo> visitados) {

		NodoGrafo verticeActual = buscarVertice(inicial); // Busca el vértice inicial para el recorrido en la lista de
															// vértices
		while (verticeActual != null) // Ciclo para verificar que todos los vértices estén visitados
		{
			// Si el nodo inicial no está visitado
			if (!verticeActual.getVisitado()) {
				verticeActual.setVisitado(true); // Lo actualiza como visitado
			}

			// Revisa todos los nodos adyacentes del nodo recién visitado
			for (int j = 0; j < verticeActual.getAristas().getLength(); j++) {
				if (!buscarVertice(verticeActual.getAristas().find(j)).getVisitado()) // Si el nodo adyacente no está
																						// visitado
				{
					esConexoAux(verticeActual.getAristas().find(j), visitados); // Hace la llamada recursiva para
																				// realizar el
					// recorrido en el
				}
			}
			// Verifica si queda algún nodo sin visitar
			if (!visitados.contains(verticeActual)) {
				visitados.add(new SimpleNode<NodoGrafo>(verticeActual));
			}
			verticeActual = verticeNoVisitado();
		}
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
