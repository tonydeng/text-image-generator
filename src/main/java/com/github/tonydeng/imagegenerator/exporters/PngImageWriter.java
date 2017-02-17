package com.github.tonydeng.imagegenerator.exporters;

import com.github.tonydeng.imagegenerator.TextImage;
import com.github.tonydeng.imagegenerator.impl.TextImageImpl;
import com.github.tonydeng.imagegenerator.validate.Validate;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.github.tonydeng.imagegenerator.exporters.ImageType.PNG;

/**
 * Implementation of the ImageWriter which handlers png exports.
 * Created by tonydeng on 2017/2/9.
 */
public class PngImageWriter implements ImageWriter {

    @Override
    public void writeImageToOutputStream(TextImage image, OutputStream output) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(output, "The outputStream may not be null.");

        ImageIO.write(((TextImageImpl) image).getBufferedImage(), PNG.getValue(), output);
    }

    @Override
    public void writeImageToFile(TextImage image, File file) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(file, "The file may not be null.");

        OutputStream os = new FileOutputStream(file);
        try {
            ImageIO.write(((TextImageImpl) image).getBufferedImage(), PNG.getValue(), os);
        } finally {
            os.close();
        }
    }
}
