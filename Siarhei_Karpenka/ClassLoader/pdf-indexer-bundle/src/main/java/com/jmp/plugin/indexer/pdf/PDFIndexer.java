package com.jmp.plugin.indexer.pdf;

import org.apache.log4j.Logger;

import com.jmp.indexer.api.Indexer;

public class PDFIndexer implements Indexer {
	
	private static final Logger logger = Logger.getLogger(PDFIndexer.class);

    @Override
    public void indexDocument(String documentUrl) {
    	logger.info("EXI: Indexing PDF document");
    }

    @Override
    public void indexPackage(String packageUrl) {
    	logger.info("EXI: Indexing package with PDF documents");
    }

}
