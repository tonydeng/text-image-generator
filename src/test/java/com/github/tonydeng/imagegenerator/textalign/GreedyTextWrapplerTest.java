package com.github.tonydeng.imagegenerator.textalign;

import com.github.tonydeng.imagegenerator.TextWrapperTest;
import org.junit.Test;

import java.util.List;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class GreedyTextWrapplerTest extends TextWrapperTest {


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

    @Test
    public void testDoWrapByList(){
        texts.forEach(
                t->{
                    List<String> strings = wrapper.doWrap(t,image.getWidth(),fm);
                    strings.forEach(s -> log.info("{}", s));
                }
        );
    }
}
