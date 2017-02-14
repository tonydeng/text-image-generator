package com.github.tonydeng.imagegenerator.textalign;

import com.github.tonydeng.imagegenerator.TextWrapperTest;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class GreedyTextWrapplerTest extends TextWrapperTest {
    private GreedyTextWrapper wrapper = new GreedyTextWrapper();
    private BufferedImage image = new BufferedImage(100, 400, BufferedImage.TYPE_INT_ARGB);
    private FontMetrics fm = image.createGraphics().getFontMetrics();

    @Test
    public void testDoWrap() {

        List<String> lines = wrapper.doWrap(text, image.getWidth(), fm);
        log.info("size:'{}'", lines.size());
        lines.forEach(
                l -> log.info("'{}'", l)
        );

    }

    @Test
    public void testMaxText() {
        final List<String> strings = wrapper.judgeText(wrapper.replaceText(text), image.getWidth(), fm);
        strings.forEach(s -> log.info("{}", s));
//        log.info("{}", text);

    }
}
