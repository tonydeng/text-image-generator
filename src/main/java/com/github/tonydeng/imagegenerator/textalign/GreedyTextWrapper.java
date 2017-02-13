package com.github.tonydeng.imagegenerator.textalign;

import com.github.tonydeng.imagegenerator.TextWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tonydeng on 2017/2/9.
 * This class uses a greedy based text wrapping algorithm which uses all
 * available space on the line to fit in the words.
 */
public class GreedyTextWrapper implements TextWrapper {
    private static final Logger log = LoggerFactory.getLogger(GreedyTextWrapper.class);
    private static final String PATTERN = "[\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b]";
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
        final StringTokenizer tokenizer = new StringTokenizer(replaceText(text));
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

    /**
     * 提前对字符串进行判断并处理
     *
     * @param text
     * @return
     */
    public List<String> judgeText(String text, int lineWidth, FontMetrics fm) {

        List<String> lines = new LinkedList<>();
        int width = SwingUtilities.computeStringWidth(fm, text);

        int loop = (width % lineWidth) == 0 ? width / lineWidth : width / lineWidth + 1;
        int flag = text.length() / loop;
        log.info("lineWidth:'{}' width:'{}' loop:'{}' flag:'{}' text length:'{}'", lineWidth, width, loop, flag, text.length());
        int begin = 0, end = 0;
        for (int i = 0; i < loop; i++) {
            if (i == 0) {
                begin = 0;
                end = flag;
            } else if (i == loop - 1) {
                begin = flag * i;
                end = text.length();
            } else {
                begin = flag * i;
                end = begin + flag;
            }
            log.info("i:'{}' begin:'{}' end :'{}', text:'{}'", i, begin, end, text.substring(begin, end));
            lines.add(text.substring(begin, end));

        }
        return lines;
    }

    /**
     * 替换标点符号为标点符号+空格
     *
     * @param source
     * @return
     */
    private String replaceText(String source) {
        Matcher matcher = Pattern.compile(PATTERN).matcher(source);
        StringBuilder sb = new StringBuilder();
        int begin = 0, end = 0;
        while (matcher.find()) {
            end = matcher.end();
            sb.append(source.substring(begin, end) + SPACE);
            begin = matcher.end();
        }
        if (end < source.length())
            sb.append(source.substring(end, source.length()));
        return sb.toString();
    }
}
