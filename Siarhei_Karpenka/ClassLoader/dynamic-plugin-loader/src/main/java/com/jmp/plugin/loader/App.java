package com.jmp.plugin.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import com.jmp.indexer.api.Indexer;
import com.jmp.plugin.loader.impl.PluginLoaderImpl;

public class App {
	
	private static final Logger logger = Logger.getLogger(App.class);
	
	private static final PluginLoader pluginLoader = new PluginLoaderImpl();
	
	private static String pathToPlugins; 

    public static void main(String[] args) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException  {
    	logger.info("Initializing dynamic plugin loader");
        Options options = new Options();
        CmdLineParser parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);
            if (options.getInputPath() == null || options.getInputPath().isEmpty()) {
                throw new CmdLineException(parser, "-in required");
            }
	    	pathToPlugins = options.getInputPath();
	    	logger.info("DYNAMIC CLASS LOADER");
	    	logger.info("test - test plugins");
	    	logger.info("update - update plugins");
	    	logger.info("uninstall - uninstall plugins");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        for (;;) {
	            String line = reader.readLine();
	            if (line != null && !line.isEmpty()) {
	                processCommand(line);
	            }
	        }
        } catch(Exception e) {
        	logger.error(e);
        }
    }

    private static void processCommand(String command) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException  {
        if (command.equals("update")) {
        	logger.info("Updating plugins...");
        	pluginLoader.update(pathToPlugins);
        } else if (command.equals("uninstall")) {
            logger.info("Uninstalling plugins...");
            pluginLoader.uninstall();
        } else if (command.equals("test")) {
        	if(pluginLoader.getPluginCache().isEmpty()) {
        		logger.info("There are no loaded plugins");
        		return;
        	}
    		for (Map.Entry<String, List<Class<?>>> plugin : pluginLoader.getPluginCache().entrySet()) {
    			for (Class<?> clazz : plugin.getValue()) {
    				logger.info("Testing " + clazz.getName());
     				Indexer obj = (Indexer) clazz.newInstance();
    				obj.indexPackage("Package");
    			}
    		}
        }
    }
}
