package com.github.tonydeng.imagegenerator.exporters;

/**
 * Facgtory which creates an Imagewriter based on the ImageType
 * Created by tonydeng on 2017/2/9.
 */
public final class ImageWriterFactory {

    private static final ImageWriter JPEG_IMAGE_WRITER = new JpegImageWriter();

    private static final ImageWriter PNG_IMAGE_WRITER = new PngImageWriter();

    /**
     *
     * @param type the export type to create an ImageWriter for
     * @return An implementation of ImageWriter which is capable of writing image indicated by the image type.
     */
    public static ImageWriter getImageWriter(final ImageType type) {
        switch (type) {
//            case JPEG:
//                return JPEG_IMAGE_WRITER;
            case PNG:
                return PNG_IMAGE_WRITER;
            default:
                throw new IllegalStateException("No ImageWriter defined for ImageType[" + type + "].");
        }
    }
}
