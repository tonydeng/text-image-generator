package com.github.tonydeng.imagegenerator;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class Margin {
    private int left = 0;
    private int right = 0;
    private int top = 0;
    private int bottom = 0;

    public Margin(int left, int top) {
        this.left = left;
        this.top = top;
    }

    public Margin(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }
}
