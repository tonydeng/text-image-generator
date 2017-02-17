package com.github.tonydeng.imagegenerator.exporters;

import com.github.tonydeng.imagegenerator.TextImage;
import com.github.tonydeng.imagegenerator.impl.TextImageImpl;
import com.github.tonydeng.imagegenerator.validate.Validate;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.io.*;
import java.util.Iterator;
import java.util.Locale;

import static com.github.tonydeng.imagegenerator.exporters.ImageType.JPEG;
import static com.github.tonydeng.imagegenerator.exporters.ImageType.PNG;

/**
 * Implementation of the ImageWriter that handlers Jpeg export.
 * Uses the highest quality JPEG.
 * <p>
 * Created by tonydeng on 2017/2/9.
 */
public class JpegImageWriter implements ImageWriter {
    private static final int MAX_COMPRESSION_QUALITY = 1;

    /**
     * {@inheritDoc}
     *
     * @param image        The image to write to the outputstream.
     * @param outputStream The outputStream to write the image to.
     * @throws IOException
     */
    public void writeImageToOutputStream(final TextImage image, final OutputStream outputStream) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(outputStream, "The outputStream may not be null.");
        ImageIO.write(((TextImageImpl) image).getBufferedImage(), JPEG.getValue(), outputStream);
//        compressJpegStream(outputStream, image);
    }

    /**
     * {@inheritDoc}
     *
     * @param image The image to write to the file.
     * @param file  The outputStream to write the image to.
     * @throws IOException
     */
    public void writeImageToFile(final TextImage image, final File file) throws IOException {
        Validate.notNull(image, "The image may not be null.");
        Validate.notNull(file, "The file may not be null.");

        OutputStream os = new FileOutputStream(file);
        try {
            ImageIO.write(((TextImageImpl) image).getBufferedImage(), JPEG.getValue(), os);
        } finally {
            os.close();
        }
//        compressJpegStream(outputStream, image);
    }

    private void compressJpegStream(
            final OutputStream outputStream,
            final TextImage image) throws IOException {
        javax.imageio.ImageWriter jpegWriter = getJpegWriter();
        ImageWriteParam imageWriteParam = getHighestCompressionQualityParams();
        writeImageToOutputStream(outputStream, image, jpegWriter, imageWriteParam);

    }

    private javax.imageio.ImageWriter getJpegWriter() {
        javax.imageio.ImageWriter writer = null;
        Iterator<javax.imageio.ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
        if (iter.hasNext()) {
            writer = iter.next();
        }
        return writer;
    }

    private ImageWriteParam getHighestCompressionQualityParams() {
        // Set the compression quality
        ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault()) {
            public void setCompressionQuality(float quality) {
                this.compressionQuality = quality;
            }
        };
        iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        iwparam.setCompressionQuality(MAX_COMPRESSION_QUALITY);
        return iwparam;
    }

    private void writeImageToOutputStream(
            final OutputStream outputStream,
            final TextImage image,
            final javax.imageio.ImageWriter jpegWriter,
            final ImageWriteParam imageWriteParam) throws IOException {
        ImageOutputStream ios = ImageIO.createImageOutputStream(outputStream);
        try {
            jpegWriter.setOutput(ios);
            jpegWriter.write(null, new IIOImage(((TextImageImpl) image).getBufferedImage(), null, null), imageWriteParam);
        } finally {
            cleanUp(jpegWriter, ios);
        }
    }

    private void cleanUp(final javax.imageio.ImageWriter jpegWriter, final ImageOutputStream ios) throws IOException {
        ios.flush();
        jpegWriter.dispose();
        ios.close();
    }
}
