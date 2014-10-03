package com.jmp.plugin.indexer.poi;

import org.apache.log4j.Logger;

import com.jmp.indexer.api.Indexer;

public class POIIndexer implements Indexer {
	
	private static final Logger logger = Logger.getLogger(POIIndexer.class);

    @Override
    public void indexDocument(String documentUrl) {
    	logger.info("EXI: Indexing POI document");
    }

    @Override
    public void indexPackage(String packageUrl) {
    	logger.info("EXI: Indexing package with POI documents");
    }
}
