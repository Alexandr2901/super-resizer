package mts.teta.resizer;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import mts.teta.resizer.imageprocessor.BadAttributesException;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static marvinplugins.MarvinPluginCollection.crop;
import static marvinplugins.MarvinPluginCollection.gaussianBlur;

public class ImageProcessor {

    public void processImage(BufferedImage read, ResizerApp resizerApp) throws BadAttributesException {


        if (getExtension(resizerApp.getOutputFile().getName()).equals("")) {
            if (resizerApp.getFormat() == null) {
                resizerApp.setOutputFile(new File(resizerApp.getOutputFile().getAbsolutePath() +
                        "." +
                        getExtension(resizerApp.getInputFile().getName())
                ));
            } else {
                resizerApp.setOutputFile(new File(resizerApp.getOutputFile().getAbsolutePath() +
                        "." + resizerApp.getFormat()
                ));
            }
        }

        try {
            Thumbnails.Builder thumbnails = Thumbnails.of(read);
            if (resizerApp.getQuality() != 0) {

                if (resizerApp.getQuality() < 1 || resizerApp.getQuality() > 100) {
                    throw new BadAttributesException("Please check params!");
                } else {
                    thumbnails.outputQuality((double) resizerApp.getQuality() / 100);
                }

            }
            if (resizerApp.getResizeWidth() <= 0 && resizerApp.getResizeHeight() <= 0) {
                throw new BadAttributesException("Please check params!");
            } else {
                thumbnails.forceSize(resizerApp.getResizeWidth(), resizerApp.getResizeHeight());

            }
            thumbnails.toFile(resizerApp.getOutputFile());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            boolean hasChange = false;
            MarvinImage image = MarvinImageIO.loadImage(resizerApp.getOutputFile().getAbsolutePath());
            if (resizerApp.getCropParams() != null) {
                if (
                        resizerApp.getCropParams()[2] > 0 &&
                                resizerApp.getCropParams()[3] > 0 &&
                                resizerApp.getCropParams()[0] >= 0 &&
                                resizerApp.getCropParams()[1] >= 0
                ) {
                    crop(image.clone(), image,
                            resizerApp.getCropParams()[2],
                            resizerApp.getCropParams()[3],
                            resizerApp.getCropParams()[0],
                            resizerApp.getCropParams()[1]);
                    hasChange = true;
                } else {
                    throw new BadAttributesException("Please check params!");

                }

            }

            if (resizerApp.getBlurRadius() != 0) {

                if (resizerApp.getBlurRadius() < 0) {
                    throw new BadAttributesException("Please check params!");
                } else {
                    gaussianBlur(image.clone(), image, resizerApp.getBlurRadius());
                    hasChange = true;
                }


            }
            if (hasChange) {
                MarvinImageIO.saveImage(image, resizerApp.getOutputFile().getAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (resizerApp.getFormat() != null) {

            String format = resizerApp.getFormat().toLowerCase();
            if (format.equals("jpeg") || format.equals("png") || format.equals("jpg")) {
                try {
                    ImageIO.write(ImageIO.read(resizerApp.getOutputFile()), resizerApp.getFormat(), resizerApp.getOutputFile());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new BadAttributesException("Please check params!");
            }
        }

    }

    private String getExtension(String path) {
        String extension = "";
        int i = path.lastIndexOf('.');
        int p = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));
        if (i > p) {
            extension = path.substring(i + 1);
        }
        return extension;
    }
}
