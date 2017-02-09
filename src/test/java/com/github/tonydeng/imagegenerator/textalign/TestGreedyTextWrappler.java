package com.github.tonydeng.imagegenerator.textalign;

import com.github.tonydeng.imagegenerator.TestTextWrapper;
import com.github.tonydeng.imagegenerator.TextWrapper;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class TestGreedyTextWrappler extends TestTextWrapper {
    private TextWrapper wrapper = new GreedyTextWrapper();

    @Test
    public void testDoWrap() {
        BufferedImage image = new BufferedImage(1000, 400, BufferedImage.TYPE_INT_ARGB);
        FontMetrics fm = image.createGraphics().getFontMetrics();
//        int lineWidth = 1000 - this.margin.getLeft() - this.margin.getRight();
        List<String> lines = wrapper.doWrap(text, image.getWidth(), fm);
        log.info("size:'{}'", lines.size());
        lines.forEach(
                l -> log.info("'{}'", l)
        );
    }
}
