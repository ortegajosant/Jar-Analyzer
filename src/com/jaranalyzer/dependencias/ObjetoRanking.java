package com.jaranalyzer.dependencias;

/**
 * Objeto que contiene los datos especificos de cada vertice
 * 
 * @author jorte
 *
 */
public class ObjetoRanking {

	private int gradoSaliente;
	private int gradoEntrante;
	private String id;

	/**
	 * Contructor del objeto
	 * 
	 * @param id
	 *            String con el nombre del vértice
	 * @param gradoSaliente
	 *            numero de dependencias iniciales
	 * @param gradoEntrante
	 *            numero de referencias iniciales
	 */
	public ObjetoRanking(String id, int gradoSaliente, int gradoEntrante) {
		this.id = id;
		this.gradoSaliente = gradoSaliente;
		this.gradoEntrante = gradoEntrante;
	}

	/**
	 * Modifica el valor del grado saliente del vertice
	 * 
	 * @param valor
	 *            entero
	 */
	public void modificarGradoSaliente(int valor) {
		this.gradoSaliente += valor;
	}

	/**
	 * Modifica el valor del grado entrante del vertice
	 * 
	 * @param valor
	 *            entero
	 */
	public void modificarGradoEntrante(int valor) {
		this.gradoEntrante += valor;
	}

	public int getGradoSaliente() {
		return this.gradoSaliente;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return this.id;
	}

	public int getGradoEntrante() {
		return this.gradoEntrante;
	}

}
