package com.github.tonydeng.imagegenerator;

import com.github.tonydeng.imagegenerator.textalign.GreedyTextWrapper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class TextWrapperTest extends BaseTest {
    protected final static GreedyTextWrapper wrapper = new GreedyTextWrapper();
    protected final static BufferedImage image = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB);
    protected final static FontMetrics fm = image.createGraphics().getFontMetrics();



}
