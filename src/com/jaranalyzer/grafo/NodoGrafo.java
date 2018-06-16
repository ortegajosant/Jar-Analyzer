package com.jaranalyzer.grafo;

import com.jaranalyzer.listas.SimpleList;
import com.jaranalyzer.listas.SimpleNode;

public class NodoGrafo {
	private String id;
	private String url;
	private boolean visitado;
	private SimpleList<String> aristas;

	public NodoGrafo(String id, String url) {
		this.id = id;
		this.url = url;
		this.aristas = new SimpleList<>();
		this.visitado = false;
	}

	public NodoGrafo() {
		this.aristas = new SimpleList<>();
		this.visitado = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
