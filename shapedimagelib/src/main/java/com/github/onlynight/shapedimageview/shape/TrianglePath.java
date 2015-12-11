package com.github.onlynight.shapedimageview.shape;

import android.graphics.Point;

/**
 * Created by lion on 2015/12/11 0011.
 */
public class TrianglePath extends ShapedPath {

    @Override
    protected void onDrawPath(int width, int height) {
        super.onDrawPath(width, height);
        double h = width / 2 * Math.tan(Math.toRadians(60));
        double blank = (width - h) / 2;
        Point point[] = new Point[3];
        for (int i = 0; i < 3; i++) {
            point[i] = new Point();
        }
        point[0].x = 0;
        point[0].y = (int) (h + blank);
        point[1].x = width / 2;
        point[1].y = (int) blank;
        point[2].x = width;
        point[2].y = (int) (h + blank);

        drawPath.moveTo(point[0].x, point[0].y);
        for (int i = 1; i < 3; i++) {
            drawPath.lineTo(point[i].x, point[i].y);
        }
        drawPath.lineTo(point[0].x, point[0].y);
    }
}
