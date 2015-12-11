package com.github.onlynight.shapedimageview.shape;

import android.graphics.Path;

/**
 * Created by lion on 2015/12/10 0010.
 */
public abstract class ShapedPath {

    protected Path drawPath = new Path();

    public ShapedPath() {
    }

    public Path getDrawPath(int width, int height) {
        onDrawPath(width, height);
        return drawPath;
    }

    protected void onDrawPath(int width, int height) {
    }
}
