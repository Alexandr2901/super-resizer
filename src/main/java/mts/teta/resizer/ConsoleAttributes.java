package mts.teta.resizer;


import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;


public class ConsoleAttributes {
    @Parameters(index = "0", description = "The file whose checksum to calculate.", defaultValue = "1.png")
    protected File inputFile;
    @Parameters(index = "1", description = "The file whose checksum to calculate.", defaultValue = "2.png")
    protected File outputFile;
    @Option(names = {"--resize"}, description = "1", defaultValue = "1")
    protected int resizeWidth;
    @Option(names = {"-b", "--blgorithm"}, description = "1", defaultValue = "1")
    protected int resizeHeight;
    @Option(names = {"-c", "--clgorithm"}, description = "1", defaultValue = "1")
    protected int quality;

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public void setResizeWidth(int resizeWidth) {
        this.resizeWidth = resizeWidth;
    }

    public void setResizeHeight(int resizeHeight) {
        this.resizeHeight = resizeHeight;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}


