package com.jaranalyzer.dependencias;

import java.io.IOException;

import org.pfsw.tools.cda.base.model.ClassInformation;
import org.pfsw.tools.cda.base.model.Workset;
import org.pfsw.tools.cda.base.model.workset.ClasspathPartDefinition;
import org.pfsw.tools.cda.core.init.WorksetInitializer;
import com.jaranalyzer.grafo.Grafo;

public class DependenciasClases {

	private ClassInformation[] infoClases;
	private Grafo grafoClases;
	
	public DependenciasClases() {

	}
	
	public ClassInformation[] getInfoClases() {
		return infoClases;
	}


	public void setInfoClases(ClassInformation[] infoClases) {
		this.infoClases = infoClases;
	}


	public Grafo getGrafoClases() {
		return grafoClases;
	}


	public void setGrafoClases(Grafo grafoClases) {
		this.grafoClases = grafoClases;
	}

	public DependenciasClases(String urlJar) {
		try {
			Workset ws = new Workset("default");
			ClasspathPartDefinition partDefinition = new ClasspathPartDefinition(urlJar);
			ws.addClasspathPartDefinition(partDefinition);
			while (!WorksetInitializer.initWorksetAndWait(ws)) {}

			infoClases = ws.getAllContainedClasses();
			grafoClases = new Grafo();

			for (ClassInformation info : infoClases)
			{
				System.out.println(info.getClassName());
				String [] nameTokens;
				for(ClassInformation dependencia : info.getReferredClassesArray()) {
					nameTokens = dependencia.getName().split("\\.");
					if(!nameTokens[0].equals("java") && !nameTokens[1].equals("lang")) {
						grafoClases.agregarVertice(dependencia.getClassName(), "");
						grafoClases.agregarArista(info.getClassName(), dependencia.getClassName());
					}
				}
				
				
//				grafoClases.agregarVertice(info.getClassName(), "");
//				dependencias = info.getReferredClassesArray();
//				for (ClassInformation dep : dependencias) {
//					System.out.println("\t> "+dep.getClassName());
//					grafoClases.agregarVertice(dep.getClassName(), "");
//					System.out.println(grafoClases.agregarArista(info.getClassName(), info.getClassName()));
//				}
			}
//
//			System.out.println("Cant. Paquetes: "+ws.getNumberOfContainedPackages());
//			System.out.println("Cant. Clases: "+ws.getNumberOfContainedClasses());

			generarGrafoClases();
			
			ws.release();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generarGrafoClases() {
		grafoClases = new Grafo();
		ClassInformation[] dependencias;
		for(ClassInformation clase : infoClases) {
			String [] nameTokens;	
			for(ClassInformation dependencia :  clase.getReferredClassesArray()) {
				nameTokens = dependencia.getName().split("\\.");
				if(!nameTokens[0].equals("java") && !nameTokens[1].equals("lang")) {
					grafoClases.agregarVertice(dependencia.getClassName(), "");
					grafoClases.agregarArista(clase.getClassName(), dependencia.getClassName());
				}
			}
		}
	}
}
