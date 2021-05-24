package mts.teta.resizer;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

@CommandLine.Command(
        synopsisHeading = "",
        helpCommand = true,
        customSynopsis = {
                "Version: resizer 0.0.1 https://github.com/Alexandr2901/super-resizer",
                "Available formats: jpeg png",
                "Usage: convert input-file [options ...] output-file",
                "Options Settings:",
                "--resize width height       resize the image",
                "--quality value             JPEG/PNG compression level",
                "--crop width height x y     @|fg(red) cut|@ out one rectangular area of the image",
                "--blur {radius}             reduce image noise detail level",
                "--format @|fg(green) \"outputFormat\"|@     the image @|fg(red) format type|@"
        }
)
public class ConsoleAttributes {

    protected File inputFile;
    protected File outputFile;
    protected int resizeHeight = 0;
    protected int resizeWidth = 0;
    protected int quality = 0;
    @Option(names = {"-h", "--help"}, hidden = true, usageHelp = true, echo = true)
    protected boolean helpRequested = false;
    private String format;
    private int[] cropParams;
    private int blurRadius = 0;

    @Option(names = {"--resize"}, hidden = true,
            split = " ",
            arity = "2")
    public void setResizeAttribute(int[] attribute) {

        this.resizeWidth = attribute[0];
        this.resizeHeight = attribute[1];

    }

    public int getQuality() {
        return quality;
    }

    @Option(names = {"--quality"}, hidden = true,
            paramLabel = "value", defaultValue = "100")
    public void setQuality(int quality) {
        this.quality = quality;

    }

    public int getResizeHeight() {
        return resizeHeight;
    }

    public void setResizeHeight(int resizeHeight) {
        this.resizeHeight = resizeHeight;
    }

    public int getResizeWidth() {
        return resizeWidth;
    }

    public void setResizeWidth(int resizeWidth) {
        this.resizeWidth = resizeWidth;
    }

    public File getInputFile() {
        return inputFile;
    }

    @Parameters(
            index = "0",
            hidden = true)
    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    @Parameters(
            index = "1",
            hidden = true)
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public String getFormat() {
        return format;
    }

    @Option(names = {"--format"}, hidden = true)
    public void setFormat(String format) {
        this.format = format;
    }

    public int[] getCropParams() {
        return cropParams;
    }

    @Option(names = {"--crop"}, hidden = true,
            split = " ",
            arity = "4")
    public void setCropParams(int[] cropParams) {
        this.cropParams = cropParams;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    @Option(names = {"--blur"}, hidden = true)
    public void setBlurRadius(int blurRadius) {
        this.blurRadius = blurRadius;
    }
}


