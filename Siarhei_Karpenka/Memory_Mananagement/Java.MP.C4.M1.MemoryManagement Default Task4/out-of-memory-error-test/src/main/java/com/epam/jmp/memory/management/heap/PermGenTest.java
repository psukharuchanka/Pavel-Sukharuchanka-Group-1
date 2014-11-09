package com.epam.jmp.memory.management.heap;

import java.util.Set;

import javassist.ClassPool;

import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

/**
 * User: Siarhei_Karpenka Date: 19/10/14
 */
public class PermGenTest {
	
	static final Logger logger = Logger.getLogger(PermGenTest.class);
	
	public static void main(String[] args) {
		Reflections reflections = new Reflections("org", new SubTypesScanner(false));
		Set<String> allClasses = reflections.getStore().getSubTypesOf(Object.class.getName());
		ClassPool pool = ClassPool.getDefault();
		for (int i = 0; i < Long.MAX_VALUE; i++) {
			pool.makeClass("ClassName" + i);
		}
		/*for (String className : allClasses) {
			try {
				reflections.getClass().getClassLoader().loadClass(className);
			} catch (NoClassDefFoundError e) {
				logger.error(className, e);
			} catch (ClassNotFoundException e) {
				logger.error(className, e);
			}
		}*/
	}
}
