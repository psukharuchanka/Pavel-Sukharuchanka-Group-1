package com.jmp.plugin.loader.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.jmp.plugin.loader.PluginLoader;
import com.jmp.plugin.loader.classloader.JarPluginUrlClassLoader;
import com.jmp.plugin.loader.util.JarClassLoaderUtil;

public class PluginLoaderImpl implements PluginLoader {

	private static final Logger logger = Logger.getLogger(PluginLoaderImpl.class);
	
	private static Map<String, List<Class<?>>> pluginCache = new HashMap<String, List<Class<?>>>();
	
	public int update(String pathToPlugins) throws ClassNotFoundException, MalformedURLException  {
		File[] plugins = JarClassLoaderUtil.getPlugins(pathToPlugins);
		return loadClasses(plugins);
	}
	
	private int loadClasses(File[] plugins) throws ClassNotFoundException, MalformedURLException {
		JarPluginUrlClassLoader classLoader = null;
		for (int i = 0; i < plugins.length; i++) {
		    try {
		    	List<String> classesToLoad = JarClassLoaderUtil.getClassNamesFromJar(plugins[i].getAbsolutePath());
		    	List<Class<?>> loadedClasses = new ArrayList<Class<?>>();
		        URL jarUrl = new URL("file:" + plugins[i].getAbsolutePath());
		        classLoader = new JarPluginUrlClassLoader(new URL[] {jarUrl}, getClass().getClassLoader());
		        for (String classToLoad : classesToLoad) {
		        	loadedClasses.add(classLoader.loadClass(FilenameUtils.getBaseName(classToLoad)));
		        }
		        getPluginCache().put(FilenameUtils.getName(plugins[i].getAbsolutePath()), loadedClasses);
		        logger.info("Plugin " + FilenameUtils.getName(plugins[i].getAbsolutePath()) + " is successfully uploaded");
		    } finally {
		        if(classLoader != null)
		        	classLoader.close();
		    }
		}
		return getPluginCache().size();
	}

	public void uninstall() {
		pluginCache.clear();
		logger.info("All plugins were successfully uninstalled");
	}

	public Map<String, List<Class<?>>> getPluginCache() {
		return pluginCache;
	}
}