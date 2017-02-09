package com.github.tonydeng.imagegenerator.textalign;

import com.github.tonydeng.imagegenerator.Align;
import com.github.tonydeng.imagegenerator.DrawableText;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Justifies the given text bsed on the linewidth.
 * Created by tonydeng on 2017/2/9.
 */
public class Justify implements Align {
    @Override
    public List<DrawableText> align(String text, FontMetrics fm, int lineWidth) {
        List<DrawableText> results = new ArrayList<>();
        int spaceLeft = lineWidth - fm.stringWidth(text);
        String[] words = getWords(text);
        for (int i = 0; i < words.length - 1; i++) {
            spaceLeft += fm.charWidth(' ');
        }
        int glue = 0;

        if (words.length > 0) {
            glue = Math.round((float) spaceLeft / (words.length - 1));
        }

        int xPostion = 0;

        for (String word : words) {
            results.add(new DrawableText(word, xPostion));
            xPostion += fm.stringWidth(word) + glue;
        }
        return results;
    }
}
