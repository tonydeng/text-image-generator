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
public class TestTextWrap extends TestTextWrapper {

    @Test
    public void test() throws IOException {
        TextImage image = new TextImageImpl(1000, 400);
        image.withFont(new Font("宋体", Font.PLAIN, 20));
        image.wrap(true);
        image.setTextAligment(Alignment.LEFT);
        for (String s : text.split(System.getProperty("line.separator"))) {
            if (null != s && s.length() > 0) {
                image.write(s);
            }
        }

        ImageWriter writer = ImageWriterFactory.getImageWriter(ImageType.PNG);
        writer.writeImageToFile(image, new File("textwrap.png"));
    }
}
