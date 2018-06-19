package com.jaranalyzer.dependencias;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import com.jaranalyzer.grafo.Grafo;
import com.jaranalyzer.grafo.NodoGrafo;

/**
 * Genera las dependencias del Jar Principal
 * 
 * @author jorte
 *
 */
public class Dependencia {

	private JarFile jar;
	private Grafo grafoJars;

	public Dependencia() {

	}

	/**
	 * Constructor
	 * 
	 * @param urlJar
	 *            Path donde se encuentra el archivo
	 */
	public Dependencia(String urlJar) {
		try {
			this.jar = new JarFile(urlJar);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Se genera el grafo logico
	 */
	public void generarGrafoJars() {
		grafoJars = new Grafo();
		Manifest manifest = null;
		try {
			manifest = jar.getManifest();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (manifest != null) {
			String dependencias = manifest.getMainAttributes().getValue("Rsrc-Class-Path");
			if (dependencias != null) {
				int largo = jar.getName().split("\\\\").length;
				String nombre = jar.getName().split("\\\\")[largo - 1];
				grafoJars.agregarVertice(nombre, jar.getName());
				for (String dependencia : dependencias.split(" ")) {
					if (!dependencia.equals("./")) {
						grafoJars.agregarVertice(dependencia, "\\" + dependencia);
						grafoJars.agregarArista(nombre, dependencia);
					}
				}
				generarGrafosInternos();
			}
		}
	}

	private void generarGrafosInternos() {
		int largo = grafoJars.getNumeroNodos();
		DependenciaInterna temp = null;
		for (int i = 1; i < largo; i++) {
			try {
				temp = new DependenciaInterna(jar, grafoJars.getVertices().find(i));
				if (temp.getDependenciasInternas() != null) {
					for (int j = 1; j < temp.getDependenciasInternas().getNumeroNodos(); j++) {
						grafoJars.agregarVertice(temp.getDependenciasInternas().getVertices().find(j).getId(),
								temp.getDependenciasInternas().getVertices().find(j).getUrl());
						grafoJars.agregarArista(grafoJars.getVertices().find(i).getId(),
								temp.getDependenciasInternas().getVertices().find(j).getId());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene el grafo
	 * 
	 * @return Grafo
	 */
	public Grafo getGrafo() {
		return this.grafoJars;
	}

	/**
	 * Obtiene el vertice del grafo
	 * 
	 * @param vertice
	 *            String con el nombre del vertice
	 * @return
	 */
	public NodoGrafo obtenerDependenciaInterna(String vertice) {
		return grafoJars.buscarVertice(vertice);
	}

	/**
	 * Obtiene el jar
	 * 
	 * @return JarFile
	 */
	public JarFile getJar() {
		return jar;
	}

	/**
	 * Modificar el Jar de la dependecia
	 * 
	 * @param jar
	 */
	public void setJar(JarFile jar) {
		this.jar = jar;
	}

	/**
	 * Obtiene el valor del grafo del jar
	 * 
	 * @return Grafo
	 */
	public Grafo getGrafoJars() {
		return grafoJars;
	}

	/**
	 * Configura el valor de los jars
	 * 
	 * @param grafoJars
	 *            Grafo
	 */
	public void setGrafoJars(Grafo grafoJars) {
		this.grafoJars = grafoJars;
	}

}
