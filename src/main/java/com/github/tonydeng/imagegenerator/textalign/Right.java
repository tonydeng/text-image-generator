package com.github.tonydeng.imagegenerator.textalign;

import com.github.tonydeng.imagegenerator.Align;
import com.github.tonydeng.imagegenerator.DrawableText;

import java.awt.*;
import java.util.List;

/**
 * Right aligns the given text bsed on the linewidth.
 * Created by tonydeng on 2017/2/9.
 */
public final class Right implements Align {
    @Override
    public List<DrawableText> align(String text, FontMetrics fm, int lineWidth) {
        int xPosition = lineWidth - fm.stringWidth(text);
        return defuatAlign(text, xPosition, fm);
    }
}
