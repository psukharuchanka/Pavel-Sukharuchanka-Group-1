package com.jmp.indexer.api;

public interface Indexer {

    void indexDocument(String documentUrl);

    void indexPackage(String packageUrl);

}
