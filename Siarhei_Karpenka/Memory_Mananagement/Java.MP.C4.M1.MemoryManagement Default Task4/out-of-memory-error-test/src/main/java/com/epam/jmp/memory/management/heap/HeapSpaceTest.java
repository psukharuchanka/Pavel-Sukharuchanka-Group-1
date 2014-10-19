package com.epam.jmp.memory.management.heap;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Siarhei_Karpenka
 * Date: 19/10/14
 */
public class HeapSpaceTest {
	
    static final Logger logger = Logger.getLogger(HeapSpaceTest.class);

    public static void main(String[] args) {
        List<byte[]> bytesList = new ArrayList<byte[]>();
        Runtime runtime = Runtime.getRuntime();
        while (true) {
            byte bytes[] = new byte[1048576];
            bytesList.add(bytes);
            logger.info("free memory: " + runtime.freeMemory());
        }
    }
}
