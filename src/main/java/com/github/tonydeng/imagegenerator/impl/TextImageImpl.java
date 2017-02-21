package com.github.tonydeng.imagegenerator.impl;

import com.github.tonydeng.imagegenerator.*;
import com.github.tonydeng.imagegenerator.textalign.Center;
import com.github.tonydeng.imagegenerator.textalign.GreedyTextWrapper;
import com.github.tonydeng.imagegenerator.textalign.Justify;
import com.github.tonydeng.imagegenerator.textalign.Left;
import com.github.tonydeng.imagegenerator.validate.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class TextImageImpl implements TextImage {
    private final static Logger log = LoggerFactory.getLogger(TextImageImpl.class);
    private final int width;

    private final int height;

    private int xPos = 0;

    private int yPos = 0;

    private Style style = Style.PLAIN;

    private Alignment alignment = Alignment.LEFT;

    private Margin margin = new Margin(0, 0, 0, 0);

    private boolean wrap = false;

    private TextWrapper wrapper = new GreedyTextWrapper();

    private final BufferedImage image;

    private final Graphics2D graphics;

    private static Font previouslyUsedFont = new Font("SansSerif", Font.PLAIN, 12);

    private Color previouslyUsedColor = Color.BLACK;

    private final Map<Alignment, Align> alignments;

    {
        alignments = new ConcurrentHashMap<>();
        alignments.put(Alignment.LEFT, new Left());
        alignments.put(Alignment.CENTER, new Center());
        alignments.put(Alignment.RIGHT, new Center());
        alignments.put(Alignment.JUSTIFY, new Justify());
    }

    public TextImageImpl(int width, int height) {
        this.width = width;
        this.height = height;

        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.graphics = image.createGraphics();

        graphics.setBackground(new Color(255, 255, 255));
        graphics.setColor(new Color(0, 0, 0));

        graphics.clearRect(0, 0, width, height);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 反锯齿
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/fonts/SimSun.ttf");
            this.withFont(Font.createFont(Font.TRUETYPE_FONT, fis).deriveFont(Font.PLAIN, 20));
        } catch (Exception e) {
            log.error("init simsun ttf error:", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public TextImageImpl(int width, int height, Margin margin) {
        this(width, height);
        this.margin = Validate.notNull(margin, "The margin may not be null.");

        this.yPos = this.margin.getTop();
        this.xPos = this.margin.getLeft();
    }

    @Override
    public TextImage useTextWrapper(TextWrapper wrapper) {
        this.wrapper = Validate.notNull(wrapper, "The wrapper may not be null.");
        return this;
    }

    @Override
    public TextImage performAction(TextImageCallback callback) {
        Validate.notNull(callback, "The callback may not be null.");
        callback.doWithGraphics(this.graphics);
        return this;
    }

    @Override
    public TextImage addHSpace(int space) {
        this.xPos = space;
        return this;
    }

    @Override
    public TextImage addVSpace(int space) {
        this.yPos = space;
        return this;
    }

    @Override
    public TextImage wrap(boolean enable) {
        this.wrap = enable;
        return this;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public TextImage write(String text) {
        Validate.notNull(text, "The text may not be null.");
        FontMetrics fm = getFontMetrics();
        if (wrap) {
            int lineWidth = width - margin.getLeft() - margin.getRight();
            List<String> lines = wrapper.doWrap(text, lineWidth, fm);
            lines.forEach(
                    l -> {
                        writeText(fm, l);
                        applyStyle(fm, l);
                        newLine();
                    }
            );
        }
        return this;
    }

    private void applyStyle(FontMetrics fm, String line) {
        if (style.equals(Style.UNDERLINED)) {
            int y = yPos + fm.getAscent();
            graphics.drawLine(xPos - fm.stringWidth(line), y, xPos, y);
        }
    }

    /**
     * Writes the given text on the Graphics object.
     *
     * @param fm   FontMetrics used to calculate the width of the text based on the font.
     * @param text
     */
    private void writeText(FontMetrics fm, String text) {
        int lineWidth = width - margin.getLeft() - margin.getRight();
        List<DrawableText> words = alignments.get(alignment).align(text, fm, lineWidth);
        words.forEach(
                w -> {
                    graphics.drawString(w.getText(), xPos + w.getxPos(), yPos + getFontMetrics().getAscent() - getFontMetrics().getDescent());
                }
        );
        xPos = xPos + fm.stringWidth(text);
    }

    @Override
    public TextImage write(String text, int yOffset) {
        Validate.notNull(text, "The text may not be null.");

        int oldY = this.yPos;
        this.yPos += yOffset;
        this.write(text);

        // The specified yOffset is only temporary
        this.yPos = oldY;
        return this;
    }

    @Override
    public TextImage writeLine(String text) {
        graphics.drawString(text, this.xPos, this.yPos + this.getFontMetrics().getAscent() - this.getFontMetrics().getDescent());
        this.newLine();

        return this;
    }

    @Override
    public TextImage write(Image image) {
        Validate.notNull(image, "The image may not be null.");

        int iWidth = ((BufferedImage) image).getWidth();
        int iHeight = ((BufferedImage) image).getHeight();

        int y = this.yPos + getFontMetrics().getAscent() - iHeight;

        this.graphics.drawImage(image, this.xPos, y, null);
        this.xPos = this.xPos + iWidth;

        return this;
    }

    @Override
    public TextImage write(Image image, int yOffset) {
        Validate.notNull(image, "The image may not be null.");

        int oldY = this.yPos;
        this.yPos += yOffset;
        this.write(image);

        // The specified yOffset is only temporary
        this.yPos = oldY;

        return this;
    }

    @Override
    public TextImage writeLine(Image image) {
        Validate.notNull(image, "The image may not be null.");

        int iHeight = ((BufferedImage) image).getHeight();

        int y = this.yPos + getFontMetrics().getAscent() - iHeight;

        this.graphics.drawImage(image, this.xPos, y, null);
        this.newLine();
        return this;
    }

    @Override
    public TextImage write(Image image, int x, int y) {
        Validate.notNull(image, "The image may not be null.");

        this.graphics.drawImage(image, x, y, null);
        return this;
    }

    @Override
    public TextImage newLine() {
        yPos = yPos + getFontMetrics().getHeight();
        xPos = margin.getLeft();
        return this;
    }

    @Override
    public TextImage newLine(int times) {
        yPos = yPos + (times * getFontMetrics().getHeight());
        xPos = margin.getLeft();
        return this;
    }

    @Override
    public TextImage withFont(Font font) {
        previouslyUsedFont = Validate.notNull(font, "The font may not be null.");
        graphics.setFont(font);
        return this;
    }

    @Override
    public TextImage withFontStyle(Style style) {
        this.style = Validate.notNull(style, "The style may not be null.");
        return this;
    }

    @Override
    public TextImage withColor(Color color) {
        previouslyUsedColor = Validate.notNull(color, "The color may not be null.");
        graphics.setColor(color);
        return this;
    }

    @Override
    public Font getCurrentFont() {
        return previouslyUsedFont;
    }

    @Override
    public Color getCurrentColor() {
        return previouslyUsedColor;
    }

    @Override
    public TextImage setTextAligment(Alignment alignment) {
        this.alignment = Validate.notNull(alignment, "The alignment may not be null.");
        return this;
    }

    @Override
    public TextImage setFontSize(float size) {
        Validate.notNull(size, "The font size may not be null.");
        this.previouslyUsedFont = previouslyUsedFont.deriveFont(size);
        return this;
    }

    private FontMetrics getFontMetrics() {
        return this.graphics.getFontMetrics(this.previouslyUsedFont);
    }

    public BufferedImage getBufferedImage() {
        return image;
    }
}
