package com.github.tonydeng.imagegenerator.textalign;

import com.github.tonydeng.imagegenerator.TextWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by tonydeng on 2017/2/9.
 * This class uses a greedy based text wrapping algorithm which uses all
 * available space on the line to fit in the words.
 */
public class GreedyTextWrapper implements TextWrapper {
    private static final Logger log = LoggerFactory.getLogger(GreedyTextWrapper.class);
    private static final String SPACE = " ";
    private static final char[] CSPACES = new char[]{' ', '\n'};
    private static final char CSPACE = ' ';

    /**
     * @param text
     * @param lineWidth
     * @param fm
     * @return
     * @see TextWrapper#doWrap(String, int, FontMetrics)
     */
    @Override
    public List<String> doWrap(String text, int lineWidth, FontMetrics fm) {
        final List<String> lines = new ArrayList<>();
        final StringTokenizer tokenizer = new StringTokenizer(text, SPACE);
        final StringBuilder builder = new StringBuilder();

        int spaceLeft = lineWidth; //设置空格左边的长度

        boolean removed = false;

        String word = "";
        boolean nospaceLeft;

        while (tokenizer.hasMoreTokens()) {
            if (removed)
                removed = false;
            else
                word = tokenizer.nextToken() + SPACE;

            char[] chars = new char[word.length()];

            word.getChars(0, word.length(), chars, 0);
            for (int i = 0; i < chars.length; i++) {
                if (fm.charWidth(chars[i]) > spaceLeft) {
                    if (chars[i] != CSPACE) {
                        builder.delete(builder.length() - i, builder.length());
                        removed = true;
                    }

                    if (builder.charAt(builder.length() - 1) == CSPACE) {
                        builder.delete(builder.length() - 1, builder.length());
                    }

                    lines.add(builder.toString());
                    spaceLeft = lineWidth;
                    builder.setLength(0);
                    nospaceLeft = true;
                } else {
                    spaceLeft -= fm.charWidth(chars[i]);
                    nospaceLeft = false;
                }
                if (!removed && !nospaceLeft) {
                    builder.append(chars[i]);
                } else {
                    break;
                }
            }

        }

        if (removed)
            builder.append(word.trim());
        if (builder.length() > 1 && builder.charAt(builder.length() - 1) == CSPACE) {
            builder.delete(builder.length() - 1, builder.length());
        }
        lines.add(builder.toString());

        return lines;
    }
}
