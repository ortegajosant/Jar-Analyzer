package com.jaranalyzer.dependencias;

import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.jaranalyzer.grafo.NodoGrafo;

public class DependenciaInterna {
	private JarFile jarInterno;	
	
	public DependenciaInterna(JarFile jarFile, NodoGrafo dependeciaInterna) {
		
		System.out.println(jarFile.getEntry("\\"));
	}
	

}
