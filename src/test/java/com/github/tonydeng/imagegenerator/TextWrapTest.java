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
            image.withFont(new Font("SimSum", Font.PLAIN, 20));
            image.setTextAligment(Alignment.LEFT);
            image.performAction(new BackgroundColorCallback(Color.WHITE, Color.black, image));

            image.write(texts.get(i).length() > 500 ? texts.get(i).substring(0, 500) : texts.get(i));
            ImageType type = ImageType.PNG;
            ImageWriter writer = ImageWriterFactory.getImageWriter(type);

            writer.writeImageToFile(image, new File(i + "." + type.getValue()));
        }
    }

//    @Test
    public void testImageWriterByFont() throws IOException, FontFormatException {
//        String fontpath = "/Users/tonydeng/Downloads/qingchongti.ttf";
//        Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontpath));
//        font.deriveFont(Font.PLAIN, 50);
//
//        BufferedImage image = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D graphics = image.createGraphics();
//        graphics.setBackground(new Color(255, 255, 255));
//        graphics.setColor(new Color(0, 0, 0));
//        graphics.setFont(font);
//        graphics.drawString("测试", 100, 100);
//        graphics.dispose();
//        graphics.drawImage(image,100,100,1000,800,null);
//
//        ImageIO.write(image, ImageType.PNG.getValue(), new File("t.png"));


        // 获取font的样式应用在str上的整个矩形
        String str = "测试";
        Font font = new Font("SimSum", Font.PLAIN, 20);
        Rectangle2D r = font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));
        int unitHeight = (int) Math.floor(r.getHeight()) + 1; // 获取单个字符的高度
        // 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int width = (int) Math.round(r.getWidth()) + 1;
        int height = unitHeight + 3; // 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
        // 创建图片
        BufferedImage imageb = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = imageb.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 反锯齿
        g.setFont(font); // 设置画笔字体
        g.drawString(str, 0, font.getSize()); // 画出字符串
        g.dispose();

        ImageIO.write(imageb, "png", new File("t.png")); // 输出png图片

    }
}