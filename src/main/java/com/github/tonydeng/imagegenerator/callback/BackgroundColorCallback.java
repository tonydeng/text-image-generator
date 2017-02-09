package com.github.tonydeng.imagegenerator.callback;

import com.github.tonydeng.imagegenerator.TextImage;
import com.github.tonydeng.imagegenerator.TextImageCallback;
import com.github.tonydeng.imagegenerator.validate.Validate;

import java.awt.*;

/**
 * Created by tonydeng on 2017/2/9.
 * Implementation of the TextImageCallback interface to change the backgrountcolor
 */
public class BackgroundColorCallback implements TextImageCallback {
    private final Color backgroundColor;
    private final Color textColor;
    private final TextImage textImage;

    public BackgroundColorCallback(Color backgroundColor, Color textColor, TextImage textImage) {
        this.backgroundColor = Validate.noNull(backgroundColor, "The backgroundColor may not be null.");
        this.textColor = Validate.noNull(textColor, "The textColor may not be null.");
        this.textImage = Validate.noNull(textImage, "The textImage may not be null.");
    }

    @Override
    public void doWithGraphics(Graphics2D graphics) {
        //Set th color for the background
        graphics.setColor(backgroundColor);
        // Draw a rectangle to apple the given backgrount
        graphics.fillRect(0, 0, textImage.getWidth(), textImage.getHeight());
        // set the color back to black for the text
        graphics.setColor(textColor);
    }
}
