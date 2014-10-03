package com.jmp.plugin.loader;

import org.kohsuke.args4j.Option;

/**
 * User: Siarhei Karpenka
 * Date: 03.10.14
 * Time: 13:55
 */
public class Options {
    @Option(name = "-in")
    private String inputPath;

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }
}