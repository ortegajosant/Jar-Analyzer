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
