package com.github.onlynight.shapedimageview.shape;

import android.graphics.Point;

/**
 * Created by lion on 2015/12/10 0010.
 */
public class StarPath extends ShapedPath {

    @Override
    protected void onDrawPath(int width, int height) {
        super.onDrawPath(width, height);

        //首先计算五角星在整个矩形中哥个顶点到五角星中心的圆心距
        // width = radius + cos(36) * radius
        // radius = width / ( 1 + cos(36) )
        double outRadius = height / 2 / Math.sin(Math.toRadians(72));
        double temp1 = outRadius * Math.cos(Math.toRadians(36));
        double temp2 = temp1 * Math.tan(Math.toRadians(36)) * Math.tan(Math.toRadians(36));
        double innerRadius = outRadius * 0.5;
        Point a[] = new Point[5];
        Point b[] = new Point[5];
        for (int i = 0; i < 5; i++) {
            a[i] = new Point();
            a[i].x = (int) (outRadius * Math.sin(Math.toRadians(36 + 72 * i))) + width / 2;
            a[i].y = (int) ((outRadius * Math.cos(Math.toRadians(36 + 72 * i))) + outRadius +
                    (height - outRadius * (1 + Math.cos(Math.toRadians(36)))) / 2);

            b[i] = new Point();
            b[i].x = (int) (innerRadius * Math.cos(Math.toRadians(72 * i + 18))) + width / 2;
            b[i].y = (int) ((innerRadius * Math.sin(Math.toRadians(72 * i + 18))) + outRadius +
                    (height - outRadius * (1 + Math.cos(Math.toRadians(36)))) / 2);
        }

        drawPath.moveTo(a[0].x, a[0].y);
        drawPath.lineTo(b[1].x, b[1].y);
        drawPath.lineTo(a[4].x, a[4].y);
        drawPath.lineTo(b[2].x, b[2].y);
        drawPath.lineTo(a[3].x, a[3].y);
        drawPath.lineTo(b[3].x, b[3].y);
        drawPath.lineTo(a[2].x, a[2].y);
        drawPath.lineTo(b[4].x, b[4].y);
        drawPath.lineTo(a[1].x, a[1].y);
        drawPath.lineTo(b[0].x, b[0].y);
        drawPath.lineTo(a[0].x, a[0].y);
    }
}
