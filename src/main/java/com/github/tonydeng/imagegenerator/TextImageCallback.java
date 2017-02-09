package com.github.tonydeng.imagegenerator;

import java.awt.*;

/**
 * Created by tonydeng on 2017/2/9.
 */
public interface TextImageCallback {

    /**
     * Exposes the graphics object which clients can use to perform more advanced functionality than
     * @param graphics
     */
    void doWithGraphics(Graphics2D graphics);
}
