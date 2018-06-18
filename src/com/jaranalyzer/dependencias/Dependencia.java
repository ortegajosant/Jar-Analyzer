package com.jaranalyzer.dependencias;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import com.jaranalyzer.grafo.Grafo;
import com.jaranalyzer.grafo.NodoGrafo;

public class Dependencia {

	private JarFile jar;
	private Grafo grafoJars;

	public Dependencia() {

	}

	public Dependencia(String urlJar) {
		try {
			this.jar = new JarFile(urlJar);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
						grafoJars.agregarArista(grafoJars.getVertices().find(i).getId(), temp.getDependenciasInternas().getVertices().find(j).getId());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Grafo getGrafo() {
		return this.grafoJars;
	}

	public NodoGrafo obtenerDependenciaInterna(String vertice) {
		return grafoJars.buscarVertice(vertice);
	}

	public JarFile getJar() {
		return jar;
	}

	public void setJar(JarFile jar) {
		this.jar = jar;
	}

	public Grafo getGrafoJars() {
		return grafoJars;
	}

	public void setGrafoJars(Grafo grafoJars) {
		this.grafoJars = grafoJars;
	}

}
