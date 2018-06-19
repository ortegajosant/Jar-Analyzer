package com.jaranalyzer.dependencias;

import com.jaranalyzer.grafo.Grafo;
import com.jaranalyzer.listas.SimpleList;
import com.jaranalyzer.listas.SimpleNode;

/**
 * Se generan todos los datos respectivos a las dependencias, referencias y
 * grados salientes y entrantes, de un grafo dirijido
 * 
 * @author jorte
 *
 */
public class Ranking {

	private SimpleList<ObjetoRanking> grados;

	/**
	 * Constructor del ranking grafo
	 * 
	 * @param grafo
	 *            grafo que contiene los vertices a evaluar
	 */
	public Ranking(Grafo grafo) {
		grados = new SimpleList<>();
		generarGradoSaliente(grafo);
		generarGradoEntrante(grafo);
	}

	private void ordenarRankingSaliente() {
		int i, pos;
		ObjetoRanking aux = null;
		for (i = 0; i < grados.getLength(); i++) {
			pos = i;
			aux = grados.find(i);
			while ((pos > 0) && (grados.find(pos - 1).getGradoSaliente() < aux.getGradoSaliente())) {
				grados.replace(pos, grados.find(pos - 1));
				pos--;
			}
			grados.replace(pos, aux);
		}
	}

	private void ordenarRankingEntrante() {
		int i, pos;
		ObjetoRanking aux = null;
		for (i = 0; i < grados.getLength(); i++) {
			pos = i;
			aux = grados.find(i);
			while ((pos > 0) && (grados.find(pos - 1).getGradoEntrante() < aux.getGradoEntrante())) {
				grados.replace(pos, grados.find(pos - 1));
				pos--;
			}
			grados.replace(pos, aux);
		}
	}

	private void generarGradoSaliente(Grafo grafo) {
		for (int i = 0; i < grafo.getNumeroNodos(); i++) {
			grados.add(new SimpleNode<ObjetoRanking>(new ObjetoRanking(grafo.getVertices().find(i).getId(),
					grafo.getVertices().find(i).getAristas().getLength(), 0)));
		}
	}

	private void generarGradoEntrante(Grafo grafo) {
		SimpleList<String> temp = null;
		int i, j;
		for (i = 0; i < grafo.getNumeroNodos(); i++) {
			temp = grafo.getVertices().find(i).getAristas();
			for (j = 0; j < temp.getLength(); j++) {
				obtenerVertice((temp.find(j))).modificarGradoEntrante(1);
			}
		}
	}

	/**
	 * Verifica que exista el vertice
	 * 
	 * @param id
	 * @return true / false
	 */
	public boolean contieneVertice(String id) {
		for (int i = 0; i < grados.getLength(); i++) {
			if (grados.find(i).getID().equals(id)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtiene un vertice en específico para analizar sus entradas y salidas
	 * 
	 * @param Nombre
	 *            del vertice a evaluar
	 * @return ObjetoRanking con los datos de entrada y salida
	 */
	public ObjetoRanking obtenerVertice(String objeto) {
		for (int i = 0; i < grados.getLength(); i++) {
			if (grados.find(i).getID().equals(objeto)) {
				return grados.find(i);
			}
		}
		return null;
	}

	/**
	 * Obtiene las lista de vertices mas dependientes
	 * 
	 * @return SimpleList de Grados
	 */
	public SimpleList<ObjetoRanking> getSaliente() {
		ordenarRankingSaliente();
		return this.grados;
	}

	/**
	 * Obtiene las lista de vertices mas referenciados
	 * 
	 * @return SimpleList de Grados
	 */
	public SimpleList<ObjetoRanking> getEntrante() {
		ordenarRankingEntrante();
		return this.grados;
	}

	/**
	 * Devuelve una lista ordenada con las referencias
	 */
	public SimpleList RankingEntrante() {
		ordenarRankingEntrante();
		SimpleList entrante = new SimpleList();
		for (int i = 0; i < grados.getLength(); i++) {
			SimpleNode<String> nodo = new SimpleNode<String>(
					grados.find(i).getID() + " -> " + grados.find(i).getGradoEntrante());
		}
		return entrante;
	}

	/**
	 * Devuelve una lista ordenada con las dependencias
	 */
	public SimpleList RankingSaliente() {
		ordenarRankingSaliente();
		SimpleList saliente = new SimpleList();
		for (int i = 0; i < grados.getLength(); i++) {
			SimpleNode<String> nodo = new SimpleNode<String>(
					grados.find(i).getID() + " -> " + grados.find(i).getGradoSaliente());
			saliente.add(nodo);
		}
		return saliente;
	}

}
