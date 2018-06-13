package com.jaranalyzer.pruebas;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class PruebasJar {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Manifest mf = null;
		try {
			mf = new JarFile(
					"C:\\Users\\jorte\\Desktop\\Jackson 2.9.5\\jackson-core-2.9.5.jar")
							.getManifest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String classPath = mf.getMainAttributes().getValue("Export-Package");
		if (classPath != null) {
			System.out.println("Hola mundo");
			for (String dependency : classPath.split(";")) {
				System.out.println(dependency);
			}
		}

	}
}
