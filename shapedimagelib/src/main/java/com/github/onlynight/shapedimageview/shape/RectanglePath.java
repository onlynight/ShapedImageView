package com.github.onlynight.shapedimageview.shape;

/**
 * Created by lion on 2015/12/10 0010.
 */
public class RectanglePath extends ShapedPath {

    @Override
    protected void onDrawPath(int width, int height) {
        super.onDrawPath(width, height);
        drawPath.moveTo(0, 0);
        drawPath.lineTo(width, 0);
        drawPath.lineTo(width, height);
        drawPath.lineTo(0, height);
        drawPath.lineTo(0, 0);
    }
}
