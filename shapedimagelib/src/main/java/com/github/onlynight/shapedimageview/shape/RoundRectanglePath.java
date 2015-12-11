package com.github.onlynight.shapedimageview.shape;

import android.graphics.RectF;

/**
 * Created by lion on 2015/12/11 0011.
 */
public class RoundRectanglePath extends ShapedPath {

    private float roundCornerRadius;

    public void setRoundCornerRadius(float roundCornerRadius) {
        this.roundCornerRadius = roundCornerRadius;
    }

    @Override
    protected void onDrawPath(int width, int height) {
        super.onDrawPath(width, height);
        RectF leftTopCorner = new RectF(0, 0, roundCornerRadius * 2, roundCornerRadius * 2);
        RectF rightTopCorner = new RectF(width - roundCornerRadius * 2, 0, width, roundCornerRadius * 2);
        RectF leftBottomCorner = new RectF(0, height - roundCornerRadius * 2, roundCornerRadius * 2, height);
        RectF rightBottomCorner = new RectF(width - roundCornerRadius * 2,
                height - roundCornerRadius * 2, width, height);
        drawPath.reset();
        drawPath.moveTo(0, roundCornerRadius);
        drawPath.arcTo(leftTopCorner, 180, 90);
        drawPath.lineTo(width - roundCornerRadius, 0);
        drawPath.arcTo(rightTopCorner, 270, 90);
        drawPath.lineTo(width, roundCornerRadius);
        drawPath.lineTo(width, height - roundCornerRadius);
        drawPath.arcTo(rightBottomCorner, 0, 90);
        drawPath.lineTo(roundCornerRadius, height);
        drawPath.arcTo(leftBottomCorner, 90, 90);
        drawPath.lineTo(0, height - roundCornerRadius);
    }
}
