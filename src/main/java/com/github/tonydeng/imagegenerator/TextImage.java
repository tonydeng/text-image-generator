package com.github.tonydeng.imagegenerator;

import javax.xml.soap.Text;
import java.awt.*;

/**
 * Created by tonydeng on 2017/2/9.
 */
public interface TextImage {

    /**
     * Defines the textwrapper to use.
     *
     * @param wrapper
     * @return
     */
    TextImage useTextWrapper(TextWrapper wrapper);

    /**
     * Method which accepts a TextImageCallback which makees the graphics object available for custom processing.
     *
     * @param callback
     * @return
     */
    TextImage performAction(TextImageCallback callback);

    /**
     * Add the specified horizontal space in pixels. Can be used for the
     * alligment of elements when you need precise control over the positioning of elements.
     *
     * @param space
     * @return
     */
    TextImage addHSpace(int space);

    /**
     * Add the specified vertical space in pixels. Can be used for the
     * alligment of elements when you need precise control over the positioning of elements.
     *
     * @param space
     * @return
     */
    TextImage addVSpace(int space);

    /**
     * Enables/Disables the wrapping of text at the right end margin when the methods are use with no explicit newline.
     * like {@link #write(String)}.
     *
     * @param enable
     * @return
     */
    TextImage wrap(boolean enable);

    /**
     * @return the height of the image in pixels.
     */
    int getHeight();

    /**
     * @return The width of the image in pixels.
     */
    int getWidth();

    /**
     * Add Text, in the image current font and color to the image.
     *
     * @param text
     * @return
     */
    TextImage write(String text);

    /**
     * Add Text, in the image current font and color to the image.
     * The sppecified yAligment aligns the text.
     *
     * @param text
     * @param yOffset
     * @return
     */
    TextImage write(String text, int yOffset);

    /**
     * Add text, in the image current font and color to the image.
     * Inserts a new line.
     *
     * @param text
     * @return
     */
    TextImage writeLine(String text);

    /**
     * Add the specified image, aligned with the text, to the image.
     * The bottom of the iamge is aligned with the beginning of the ascent of the font.
     *
     * @param image
     * @return
     */
    TextImage write(Image image);

    /**
     * Add the specified image, aligned with the text, to the image.
     * The bottom of the iamge is aligned with the beginning of the ascent of the font.
     * Aligns the image at the specified yOffset.
     *
     * @param image
     * @return
     */
    TextImage write(Image image, int yOffset);

    /**
     * Adds the specified image, aligned with the text, to the image. The bottom
     * of the the image is aligned with the beginning of the ascent of the font.
     * Adds a newline.
     *
     * @param image
     * @return this
     */
    TextImage writeLine(Image image);

    /**
     * Adds an image at the specified x and y positions (absolute).
     *
     * @param image The image to be inserted.
     * @param x     left position of teh image.
     * @param y     top position of the image.
     * @return this
     */
    TextImage write(Image image, int x, int y);

    /**
     * Inserts a newline based on the previously used dont. The previously used
     * font is determined by the last call to the addText* methods with the
     * given font. If none of these methods are used, no font is available yet,
     * you can set one with the {@link #withFont(Font)} and
     *
     * @return this
     */
    TextImage newLine();

    /**
     * Convenience method which executes the newline the specified number of times.
     *
     * @param times The number of times the newline is executed.
     * @return this
     */
    TextImage newLine(int times);

    /**
     * Use the specified Font in all subsequent calls.
     *
     * @param font
     * @return this
     */
    TextImage withFont(Font font);

    /**
     * Set the fontstyle to be used.
     *
     * @param style
     * @return this
     */
    TextImage withFontStyle(Style style);

    /**
     * Use the specified color in all subsequent calls.
     *
     * @param color
     * @return this
     */
    TextImage withColor(Color color);

    /**
     * Returns the currently used Font.
     *
     * @return The currently used font.
     */
    Font getCurrentFont();

    /**
     * Returs the currently used Color.
     *
     * @return The currently used color.
     */
    Color getCurrentColor();

    /**
     * Sets the text aligment. @see Alignment.
     *
     * @param alignment
     * @return this
     */
    TextImage setTextAligment(Alignment alignment);
}
