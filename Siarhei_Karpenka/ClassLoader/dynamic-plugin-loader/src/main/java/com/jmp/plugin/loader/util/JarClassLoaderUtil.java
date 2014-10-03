package com.jmp.plugin.loader.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;

public class JarClassLoaderUtil {

	private static final Logger logger = Logger.getLogger(JarClassLoaderUtil.class);

	public static final File[] getPlugins(String pathToPlugins) {
		FileFilter fileFilter = new WildcardFileFilter("*plugin*.jar");
		File[] plugins = new File(pathToPlugins).listFiles(fileFilter);
		if (plugins.length == 0) {
			logger.warn("No plugins found");
		} else {
			logger.info("Found " + plugins.length + " plugin(s)");
		}
		return plugins;
	}

	public static final List<String> getClassNamesFromJar(String jarName) {
		ArrayList<String> classes = new ArrayList<String>();
		JarInputStream jarFile = null;
		try {
			jarFile = new JarInputStream(new FileInputStream(jarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null) {
					break;
				}
				if (jarEntry.getName().endsWith(".class")) {
					classes.add(jarEntry.getName().replaceAll("/", "\\."));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if(jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
		return classes;
	}
}