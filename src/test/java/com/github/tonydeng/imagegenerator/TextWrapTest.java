package com.github.tonydeng.imagegenerator;

import com.github.tonydeng.imagegenerator.exporters.ImageType;
import com.github.tonydeng.imagegenerator.exporters.ImageWriter;
import com.github.tonydeng.imagegenerator.exporters.ImageWriterFactory;
import com.github.tonydeng.imagegenerator.impl.TextImageImpl;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class TextWrapTest extends TextWrapperTest {

    //    @Test
    public void testImage() throws IOException {
        TextImage image = new TextImageImpl(1000, 400);
        image.withFont(new Font("宋体", Font.PLAIN, 20));
        image.wrap(true);
        image.setTextAligment(Alignment.LEFT);
        for (String s : text.split(System.getProperty("line.separator"))) {
            if (null != s && s.length() > 0) {
                log.info("'{}'", s);
//                image.write(s);
            }
        }

//        ImageWriter writer = ImageWriterFactory.getImageWriter(ImageType.PNG);
//        writer.writeImageToFile(image, new File("textwrap.png"));
    }

    @Test
    public void testImageWriter() throws IOException {

        for (int i = 0; i < texts.size(); i++) {
            log.info("{}", texts.get(i).length());
            TextImage image = new TextImageImpl(1000, 300);
            image.wrap(true);
            image.withFont(new Font("STSong-Light", Font.PLAIN, 20));
            image.setTextAligment(Alignment.LEFT);

            image.write(texts.get(i).length() > 500 ? texts.get(i).substring(0, 500) : texts.get(i));

            ImageWriter writer = ImageWriterFactory.getImageWriter(ImageType.PNG);

            writer.writeImageToFile(image, new File(i + ".png"));
        }
    }
}