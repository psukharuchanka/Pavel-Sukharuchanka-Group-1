package com.jmp.plugin.loader.classloader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.Logger;

public class JarPluginUrlClassLoader extends URLClassLoader {
	
	private static final Logger logger = Logger.getLogger(JarPluginUrlClassLoader.class);

	public JarPluginUrlClassLoader(URL[] urls, ClassLoader parent) {
	    super(urls, parent);
	}

    /**
     * Closes all open jar files
     */
    public void close() {
        try {
			super.close();
		} catch (IOException e) {
			logger.error("Unnable to close classloader", e);
		}
        return;
    }
}