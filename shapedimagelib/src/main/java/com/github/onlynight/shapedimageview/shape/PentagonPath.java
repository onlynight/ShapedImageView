package com.github.onlynight.shapedimageview.shape;

import android.graphics.Point;

/**
 * Created by lion on 2015/12/11 0011.
 */
public class PentagonPath extends ShapedPath {

    @Override
    protected void onDrawPath(int width, int height) {
        super.onDrawPath(width, height);
        //首先计算五角星在整个矩形中哥个顶点到五角星中心的圆心距
        // width = radius + cos(36) * radius
        // radius = width / ( 1 + cos(36) )
        double outRadius = height / 2 / Math.sin(Math.toRadians(72));
        Point a[] = new Point[5];
        for (int i = 0; i < 5; i++) {
            a[i] = new Point();
            a[i].x = (int) (outRadius * Math.sin(Math.toRadians(36 + 72 * i))) + width / 2;
            a[i].y = (int) ((outRadius * Math.cos(Math.toRadians(36 + 72 * i))) + outRadius +
                    (height - outRadius * (1 + Math.cos(Math.toRadians(36)))) / 2);
        }

        drawPath.moveTo(a[0].x, a[0].y);
        for (int i = 1; i < 5; i++) {
            drawPath.lineTo(a[i].x, a[i].y);
        }
    }
}
