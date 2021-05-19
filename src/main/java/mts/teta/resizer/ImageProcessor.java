package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.BadAttributesException;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessor {
    private BufferedImage inputFile;

    public void processImage(BufferedImage read, ResizerApp resizerApp) throws BadAttributesException {
        if (
                resizerApp.quality < 1
                        || resizerApp.quality > 100
        ) {
            throw new BadAttributesException("Please check params!");
        }

        try {
            this.inputFile = read;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
//            String path = resizerApp.outputFile.getAbsolutePath();
//            resizerApp.outputFile.delete();
//            System.out.println(resizerApp.resizeHeight);
//            System.out.println(resizerApp.resizeWidth);
            Thumbnails.of(this.inputFile)
                    .forceSize(
                            resizerApp.resizeWidth,
                            resizerApp.resizeHeight

                    )
                    .outputQuality(1)
                    .toFile(resizerApp.outputFile);
            resizerApp.setOutputFile(new File(resizerApp.outputFile.getAbsolutePath()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        try {
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

    }

    private void resize() {

    }

}
