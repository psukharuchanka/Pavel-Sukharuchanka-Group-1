package com.epam.jmp.memory.management.heap;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * User: Siarhei_Karpenka
 * Date: 19/10/14
 */
public class MemoryEater {
	
    private static final Logger logger = Logger.getLogger(MemoryEater.class);
	
	/**
	 * Created variable for array size to get rid of hard coded values. 
	 */
	private static final int arraySize = 1048576;

    public static void main(String[] args) {
        /**
         * There is no need to create Runtime object in the cycle.
         * It pollutes heap with unnecessary objects.
         * 
         * Runtime object creation is located out of while cycle.
         */
        Runtime runtime = Runtime.getRuntime();
        
    	/**
    	 * References to generic type ArrayList<E> should be parameterized.
    	 * Added type arguments <byte[]> to List.
    	 * Added type arguments <byte[]> to ArrayList.
    	 * 
    	 * The names of variables should be descriptive.
    	 * Changed names of variables to reflect the main intent.
    	 * Creation of new ArrayList<byte[]> - initialization of bytesList reference 
    	 * is located in the while cycle to prevent java.lang.OutOfMemoryError.
    	 * It fixes java.lang.OutOfMemoryError because every iteration 
    	 * creates new ArrayList object end we have no reference to previous ArrayList object.
    	 * So it is available for GC.
    	 */
        List<byte[]> bytesList;

        /**
         * Reference to byte[] array is located out of while cycle.
         */
        byte bytes[];
        
        while (true) {
        	bytesList = new ArrayList<byte[]>();
            bytes = new byte[arraySize];
            bytesList.add(bytes);
            /**
             * User should be informed by more detailed messages.
             * Console info should be reflected in the log files.
             * 
             * Changed System.out.println() to logger.info
             */
            logger.info("free memory: " + runtime.freeMemory());
        }
    }
}