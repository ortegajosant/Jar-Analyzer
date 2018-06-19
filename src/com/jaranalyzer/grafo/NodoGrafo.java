package com.jaranalyzer.grafo;

import com.jaranalyzer.listas.SimpleList;
import com.jaranalyzer.listas.SimpleNode;

/**
 * Nodo del grafo
 * 
 * @author jorte
 *
 */
public class NodoGrafo {
	private String id;
	private String url;
	private boolean visitado;
	private SimpleList<String> aristas;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Nombre del vertice
	 * @param url
	 *            Ubiacion del vertice
	 */
	public NodoGrafo(String id, String url) {
		this.id = id;
		this.url = url;
		this.aristas = new SimpleList<>();
		this.visitado = false;
	}

	/**
	 * Constructor
	 */
	public NodoGrafo() {
		this.aristas = new SimpleList<>();
		this.visitado = false;
	}

	/**
	 * Obtiene el nombre del vertice
	 * 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Modifica el nombre del vertice
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtiene la ubicación del
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SimpleList<String> getAristas() {
		return this.aristas;
	}

	public boolean setSiguienteArista(String vertice) {
		for (int i = 0; i < aristas.getLength(); i++) {
			if (aristas.find(i).equals(vertice)) {
				return false;
			}
		}
		this.aristas.add(new SimpleNode<String>(vertice));
		return true;
	}

	public boolean getVisitado() {
		return this.visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

}
