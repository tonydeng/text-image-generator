package com.github.tonydeng.imagegenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonydeng on 2017/2/9.
 */
public interface Align {
    String DELIMITER = " ";

    /**
     * Aligns the given text based on the given for font metrics and line width.
     *
     * @param text      The text which should be aligned.
     * @param fm        font metrics used to determine the dimensions of the text.
     * @param lineWidth the total space available for the text.
     * @return List of Items
     */
    List<DrawableText> align(String text, FontMetrics fm, int lineWidth);

    /**
     * Split the given text based on the DELIMITER and returns the results as a String[]
     *
     * @param text
     * @return
     */
    default String[] getWords(String text) {
        return text.split(DELIMITER);
    }

    default List<DrawableText> defuatAlign(String text, int xPosition, FontMetrics fm) {
        List<DrawableText> result = new ArrayList<>();
        for (String word : getWords(text)) {
            if (word.length() == 0) {
                result.add(new DrawableText(DELIMITER, xPosition));
                xPosition += fm.stringWidth(DELIMITER);
            }
            word += DELIMITER;
            result.add(new DrawableText(word, xPosition));
            xPosition += fm.stringWidth(word);
        }
        return result;
    }
}
