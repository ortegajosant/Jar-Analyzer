package com.jaranalyzer.dependencias;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.jaranalyzer.grafo.Grafo;
import com.jaranalyzer.grafo.NodoGrafo;

/**
 * Permite la creación de las dependencias internas del JAR
 * 
 * @author jorte
 *
 */
public class DependenciaInterna {
	private JarFile jarInterno;
	private Grafo dependenciasInternas;
	private String dependenciaNombre;

	public DependenciaInterna(JarFile jarFile, NodoGrafo dependenciaInterna) throws IOException {

		JarExtractor extractor = new JarExtractor(jarFile.getName(), "jarInterno", dependenciaInterna.getId());
		extractor.parseJarFile();
		dependenciaNombre = dependenciaInterna.getId();

		jarInterno = new JarFile("jarInterno/" + "jarInterno.jar");
		dependenciasInternas = new Grafo();
		extraerPOM(new File(jarInterno.getName()));
		generarGrafoInterno();

		File file2 = new File("jarInterno/pom.xml");
		if (file2.exists()) {
			file2.delete();
		}

	}

	private File extractJarFileFromJar(final JarFile parentJar, final ZipEntry extractee) throws IOException {
		BufferedInputStream is = new BufferedInputStream(parentJar.getInputStream(extractee));

		File f = new File("jarInterno" + File.separator + "pom.xml");
		String parentName = f.getParent();
		if (parentName != null) {
			File dir = new File(parentName);
			dir.mkdirs();
		}
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(f));

		int c;
		while ((c = is.read()) != -1) {
			os.write((byte) c);
		}
		is.close();
		os.close();

		return f;
	}

	private void extraerPOM(final File file) {
		if (file == null) {
			throw new RuntimeException("file is null.");
		}

		JarFile jarFile = null;
		try {
			System.out.println(file);
			jarFile = new JarFile(file);
			Enumeration entries = jarFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (entry.isDirectory()) {
					continue;
				}

				String entryName = entry.toString();
				if (entryName == null) {
					continue;
				} else if (entryName.endsWith("pom.xml")) {
					// Found a child jar file inside the parent.
					File f = extractJarFileFromJar(jarInterno, entry);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void generarGrafoInterno() {
		SAXBuilder builder = new SAXBuilder();
		File file = new File("jarInterno/pom.xml");
		Document xml = null;
		if (file.exists()) {
			try {
				xml = (Document) builder.build(file);
			} catch (JDOMException | IOException e) {
				e.printStackTrace();
			}

			Element element = xml.getRootElement();
			Element dependencias = null;

			for (int i = 0; i < element.getChildren().size(); i++) {
				if (element.getChildren().get(i).getName().equals("dependencies")) {
					dependencias = element.getChildren().get(i);
					break;
				}
			}

			if (dependencias != null) {
				dependenciasInternas.agregarVertice(dependenciaNombre, jarInterno.getName());
				for (int i = 0; i < dependencias.getChildren().size(); i++) {
					for (int j = 0; j < dependencias.getChildren().get(i).getChildren().size(); j++) {
						if (dependencias.getChildren().get(i).getChildren().get(j).getName().equals("artifactId")) {
							dependenciasInternas.agregarVertice(
									dependencias.getChildren().get(i).getChildren().get(j).getText(), "");
							dependenciasInternas.agregarArista(dependenciaNombre,
									dependencias.getChildren().get(i).getChildren().get(j).getText());
						}
					}
				}
				dependenciasInternas.imprimirGrafo();
			}
		}
	}

	public Grafo getDependenciasInternas() {
		return this.dependenciasInternas;
	}
}
