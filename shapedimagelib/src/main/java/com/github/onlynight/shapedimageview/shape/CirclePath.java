package com.github.onlynight.shapedimageview.shape;

import android.graphics.Path;

/**
 * Created by lion on 2015/12/10 0010.
 */
public class CirclePath extends ShapedPath {

    @Override
    protected void onDrawPath(int width, int height) {
        super.onDrawPath(width, height);
        int radius = width > height ? height / 2 : width / 2;
        drawPath.addCircle(width / 2, height / 2, radius, Path.Direction.CW);
    }
}
