package com.github.tonydeng.imagegenerator.exporters;

import com.github.tonydeng.imagegenerator.TextImage;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by tonydeng on 2017/2/9.
 */
public interface ImageWriter {
    /**
     * Writes the given image to the given outpotStream
     * Does not close the outputStream
     *
     * @param image The image to write to the outputStream
     * @param output The outputStream to write the image to.
     */
    void writeImageToOutputStream(TextImage image, OutputStream output)throws IOException;

    /**
     * Write the given image to the given file.
     *
     * @param image The image to write to the file.
     * @param file The File to write the image to.
     */
    void writeImageToFile(TextImage image, File file) throws IOException;
}
