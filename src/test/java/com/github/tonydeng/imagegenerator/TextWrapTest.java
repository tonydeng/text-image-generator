package com.github.tonydeng.imagegenerator;

import com.github.tonydeng.imagegenerator.callback.BackgroundColorCallback;
import com.github.tonydeng.imagegenerator.exporters.ImageType;
import com.github.tonydeng.imagegenerator.exporters.ImageWriter;
import com.github.tonydeng.imagegenerator.exporters.ImageWriterFactory;
import com.github.tonydeng.imagegenerator.impl.TextImageImpl;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

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
            image.withFont(new Font("SimSum", Font.PLAIN, 20));
            image.setTextAligment(Alignment.LEFT);
            image.performAction(new BackgroundColorCallback(Color.WHITE, Color.black, image));

            image.write(texts.get(i).length() > 500 ? texts.get(i).substring(0, 500) : texts.get(i));
            ImageType type = ImageType.PNG;
            ImageWriter writer = ImageWriterFactory.getImageWriter(type);

            writer.writeImageToFile(image, new File(i + "." + type.getValue()));
        }
    }

    @Test
    public void testImageWriterByFont() throws IOException, FontFormatException {
        String text = texts.get(0);
        log.info("{}", System.getProperty("user.dir"));
        String fontpath = System.getProperty("user.dir") + "/src/main/resources/fonts/SimSun.ttf";


        TextImage textImage = new TextImageImpl(1000, 300);

        InputStream is = new FileInputStream(fontpath);
        Font usedFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 20);

        textImage.setTextAligment(Alignment.LEFT);
//        textImage.withFont(usedFont);
//        textImage.withFont(new Font("SimSun", Font.PLAIN, 20));
        textImage
                .wrap(true)
                .write(text);


//        Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontpath));
//        font.deriveFont(Font.PLAIN, 50);


        ImageWriterFactory.getImageWriter(ImageType.PNG).writeImageToFile(textImage, new File("t.png"));

    }

    @Test
    public void testFullText() throws IOException {
        String t = "测";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            sb.append(t);
        }

        TextImage textImage = new TextImageImpl(1000, 300);
        textImage.setTextAligment(Alignment.LEFT).wrap(true);
        textImage.write(sb.toString());
        log.info("1 {}", textImage.getCurrentFont());
        textImage.setFontSize(12);
        log.info("2 {}", textImage.getCurrentFont());


        ImageWriterFactory.getImageWriter(ImageType.PNG).writeImageToFile(textImage, new File("f.png"));

        FileOutputStream os = new FileOutputStream("os.png");
        ImageWriterFactory.getImageWriter(ImageType.PNG).writeImageToOutputStream(textImage,os);
        os.close();

    }
}