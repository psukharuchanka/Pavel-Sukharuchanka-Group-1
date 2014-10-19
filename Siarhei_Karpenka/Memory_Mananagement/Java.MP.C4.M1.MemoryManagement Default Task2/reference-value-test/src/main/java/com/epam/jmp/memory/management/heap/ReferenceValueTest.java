package com.epam.jmp.memory.management.heap;

import org.apache.log4j.Logger;

/**
 * User: Siarhei_Karpenka
 * Date: 19/10/14
 */
public class ReferenceValueTest {
	
    private static final Logger logger = Logger.getLogger(ReferenceValueTest.class);

    public static void main(String[] args) {
        int a = 0;
        int[] b = {20};
        f(a, b);
        logger.info(a + " " + b[0]);
        g(a, b);
        logger.info(a + " " + b[0]);
    }
    
    /**
     * Passing by reference.
     * Created local copies if arguments. 
     * We work with the copies inside.
     */
    private static void f(int a, int[] b) {
        a += 30;
        b[0] = 40;
    }

    /**
     * Passing by reference.
     * Created local copies if arguments. 
     * We work with the copies inside.
     */
    private static void g(int a, int[] b) {
        a = 50;
        b = new int[]{60};
    }
}