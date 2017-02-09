package com.github.tonydeng.imagegenerator.validate;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class Validate {


    public static <T> T noNull(final T param, final String message) {
        if(param == null)
            throw new IllegalArgumentException(message);

        return param;
    }
}
