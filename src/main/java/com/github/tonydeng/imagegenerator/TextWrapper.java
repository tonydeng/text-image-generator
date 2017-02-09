package com.github.tonydeng.imagegenerator;


import java.awt.FontMetrics;
import java.util.List;

/**
 * Created by tonydeng on 2017/2/9.
 * Defines the contract for textwrapper implementations. A textwrapper implements de details of how to
 * wrap the given text over multiple lines. Different strategies can be implemented.
 * @see com.github.tonydeng.imagegenerator.textalign.GreedyTextWrapper
 */
public interface TextWrapper {

    /**
     * Wraps the given text and writes in on the specified graphics object.
     *
     * @param text
     * @param lineWidth
     * @param fm
     * @return A List of Strings. Each entry in the List should appear on new line.
     */
    List<String> doWrap(String text, int lineWidth, FontMetrics fm);
}
