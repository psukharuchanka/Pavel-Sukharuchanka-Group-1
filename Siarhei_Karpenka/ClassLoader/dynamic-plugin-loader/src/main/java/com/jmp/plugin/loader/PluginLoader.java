package com.jmp.plugin.loader;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public interface PluginLoader {

    int update(String pathToBundles) throws ClassNotFoundException, MalformedURLException;

    void uninstall();
    
    Map<String, List<Class<?>>> getPluginCache();

}
