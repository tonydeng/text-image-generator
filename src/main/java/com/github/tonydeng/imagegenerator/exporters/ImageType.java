package com.github.tonydeng.imagegenerator.exporters;

/**
 * Specifies the export type of the image.
 * Created by tonydeng on 2017/2/9.
 */
public enum ImageType {
    PNG("png"),
    JPEG("jpg");
    private String value;

    ImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
