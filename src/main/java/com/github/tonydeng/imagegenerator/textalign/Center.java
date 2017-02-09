package com.github.tonydeng.imagegenerator.textalign;

import com.github.tonydeng.imagegenerator.Align;
import com.github.tonydeng.imagegenerator.DrawableText;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Centers the given text bsed on the linewidth
 * <p>
 * Created by tonydeng on 2017/2/9.
 */
public final class Center implements Align {
    @Override
    public List<DrawableText> align(String text, FontMetrics fm, int lineWidth) {
        final List<DrawableText> result = new ArrayList<>();
        int xPosition = (lineWidth - fm.stringWidth(text) / 2);
        return defuatAlign(text, xPosition, fm);
    }
}
