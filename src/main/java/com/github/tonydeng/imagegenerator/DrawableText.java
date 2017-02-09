package com.github.tonydeng.imagegenerator;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class DrawableText {
    private final String text;
    private final int xPos;

    public DrawableText(String text, int xPos) {
        this.text = text;
        this.xPos = xPos;
    }

    public String getText() {
        return text;
    }

    public int getxPos() {
        return xPos;
    }
}
