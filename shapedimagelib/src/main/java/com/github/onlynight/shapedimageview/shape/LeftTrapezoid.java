package com.github.onlynight.shapedimageview.shape;

/**
 * Created by lion on 2017/6/23.
 */

public class LeftTrapezoid extends ShapedPath {

    @Override
    protected void onDrawPath(int width, int height) {
        super.onDrawPath(width, height);
        int leftBlank = width / 4;

        drawPath.moveTo(leftBlank, 0);
        drawPath.lineTo(width, 0);
        drawPath.lineTo(width, height);
        drawPath.lineTo(0, height);
        drawPath.lineTo(leftBlank, 0);
    }
}
