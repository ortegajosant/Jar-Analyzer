package com.jaranalyzer.dependencias;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Obtiene un jar interno del Jar principal
 * 
 * @author jorte
 */
public class JarExtractor {

	private String parentPath = null;
	private String extractPath = ".";
	private String jarInterno;

	/**
	 * Constructor
	 * 
	 * @param parentPath
	 *            Path donde se encuentra el Jar Principal
	 * @param extractPath
	 *            Path donde se guardará el Jar Interno
	 */
	JarExtractor(String parentPath, String extractPath, String jarInterno) {
		this.parentPath = parentPath;
		this.extractPath = extractPath;
		this.jarInterno = jarInterno;
	}

	private File extractJarFileFromJar(final JarFile parentJar, final ZipEntry extractee) throws IOException {
		BufferedInputStream is = new BufferedInputStream(parentJar.getInputStream(extractee));

		File f = new File(extractPath + File.separator + "jarInterno.jar");
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

	/**
	 * Genera un nuevo JarInterno
	 */
	public void parseJarFile() {
		parseJarFile(new File(parentPath));
	}

	private void parseJarFile(final File file) {
		if (file == null) {
			throw new RuntimeException("El archivo es nulo");
		}

		JarFile jarFile = null;
		try {
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
				} else if (entryName.endsWith(jarInterno)) {
					File f = extractJarFileFromJar(jarFile, entry);
					if (f != null) {
						parseJarFile(f);
					}
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
}