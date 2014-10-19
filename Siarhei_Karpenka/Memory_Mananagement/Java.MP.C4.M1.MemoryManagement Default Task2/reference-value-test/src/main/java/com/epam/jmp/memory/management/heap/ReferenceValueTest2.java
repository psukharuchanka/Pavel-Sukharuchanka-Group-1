package com.epam.jmp.memory.management.heap;

import org.apache.log4j.Logger;

/**
 * User: Siarhei_Karpenka
 * Date: 19/10/14
 */
public class ReferenceValueTest2 {
	
    private static final Logger logger = Logger.getLogger(ReferenceValueTest2.class);
    
    public static void main(String[] args) {
        int a = 0;
        int[] b = {20};
        a = f(a, g(a, b));
        logger.info(a + " " + b[0]);
    }

    /**
     * Return scalar type.
     */
    private static int f(int a, int[] b) {
        a += 30;
        b[0] = 40;
        return 42;
    }

    /**
     * Return object type.
     */
    private static int[] g(int a, int[] b) {
        a = 50;
        b = new int[]{60};
        return b;
    }
}