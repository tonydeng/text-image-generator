package com.github.tonydeng.imagegenerator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class BaseTest {
    protected  Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() throws IOException {
        log.info("thie test......");
    }
}
