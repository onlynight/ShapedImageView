package com.github.onlynight.chatimageview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lion on 15-8-29.
 * 任意形状imageView
 */
public class ChatImageView extends ImageView {

    private static final PorterDuffXfermode paintMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    private Path path = new Path();

    /**
     * default tuber width
     */
    private static final int DEFAULT_SHARP_CORNER_WIDTH = 5; //dp

    /**
     * default tuber width
     */
    private static final int DEFAULT_SHARP_CORNER_HEIGHT = 3; //dp

    /**
     * default tuber from top to bottom where to start
     */
    private static final int DEFAULT_SHARP_CORNER_START = 10; //dp

    /**
     * direction value is a string</br>
     * 0 = left</br>
     * 1 = right</br>
     */
    public static final int SHARP_CORNER_DIRECTION_LEFT = 0;
    public static final int SHARP_CORNER_DIRECTION_RIGHT = 1;
    private static final int DEFAULT_SHARP_CORNER_DIRECTION = SHARP_CORNER_DIRECTION_RIGHT;

    private static final int DEFAULT_ROUND_CORNER_RADIUS = 5;//dp

    private int sharpCornerWidth;
    private int sharpCornerHeight;
    private int sharpCornerDirection;
    private int sharpCornerStart;
    private boolean isRoundCorner;
    private int cornerRadius; // TODO 圆角矩形

    private RectF leftTopCorner;
    private RectF rightTopCorner;
    private RectF leftBottomCorner;
    private RectF rightBottomCorner;

    public ChatImageView(Context context) {
        super(context);
    }

    public ChatImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChatImageView);
        initAttrs(typedArray);
    }

    public ChatImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChatImageView, defStyle, 0);
        initAttrs(typedArray);
    }

    private void initAttrs(TypedArray typedArray) {
        sharpCornerWidth = (int) typedArray.getDimension(R.styleable.ChatImageView_sharp_corner_width, dpToPx(DEFAULT_SHARP_CORNER_WIDTH));
        sharpCornerHeight = (int) typedArray.getDimension(R.styleable.ChatImageView_sharp_corner_height, dpToPx(DEFAULT_SHARP_CORNER_HEIGHT));
        sharpCornerDirection = typedArray.getInt(R.styleable.ChatImageView_sharp_corner_direction, DEFAULT_SHARP_CORNER_DIRECTION);
        isRoundCorner = typedArray.getBoolean(R.styleable.ChatImageView_round_corner, false);
        cornerRadius = (int) typedArray.getDimension(R.styleable.ChatImageView_corner_radius, dpToPx(DEFAULT_ROUND_CORNER_RADIUS));
        sharpCornerStart = (int) typedArray.getDimension(R.styleable.ChatImageView_sharp_corner_start, dpToPx(DEFAULT_SHARP_CORNER_START));

        typedArray.recycle();
    }

    public int getSharpCornerWidth() {
        return sharpCornerWidth;
    }

    public void setSharpCornerWidth(int sharpCornerWidth) {
        this.sharpCornerWidth = sharpCornerWidth;
    }

    public int getSharpCornerHeight() {
        return sharpCornerHeight;
    }

    public void setSharpCornerHeight(int sharpCornerHeight) {
        this.sharpCornerHeight = sharpCornerHeight;
    }

    public int getSharpCornerDirection() {
        return sharpCornerDirection;
    }

    public void setSharpCornerDirection(int sharpCornerDirection) {
        this.sharpCornerDirection = sharpCornerDirection;
    }

    public int getSharpCornerStart() {
        return sharpCornerStart;
    }

    public void setSharpCornerStart(int sharpCornerStart) {
        this.sharpCornerStart = sharpCornerStart;
    }

    public boolean isRoundCorner() {
        return isRoundCorner;
    }

    public void setIsRoundCorner(boolean isRoundCorner) {
        this.isRoundCorner = isRoundCorner;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    private void initShape(int w, int h) {
//        //------------------
//        path.moveTo(10, 10);
//        path.lineTo(getWidth(), 10);
//
//        // |
//        // |
//        // |
//        path.lineTo(getWidth(), 50);
//
//        // \
//        path.lineTo(getWidth() + 20, 70);
//
//        // /
//        path.lineTo(getWidth(), 90);
//
//        // |
//        // |
//        // |
//        path.lineTo(getWidth(), getHeight() + 10);
//
//        //------------------
//        path.lineTo(10, getHeight() + 10);
//
//        // |
//        // |
//        // |
//        // |
//        // |
//        // |
//        path.lineTo(10, 10);
//        path.close();

        switch (sharpCornerDirection) {
            case SHARP_CORNER_DIRECTION_LEFT:
                initLeft(w, h);
                break;
            case SHARP_CORNER_DIRECTION_RIGHT:
                initRight(w, h);
                break;
        }
    }

    private void initRight(int w, int h) {
        if (isRoundCorner) {
            leftTopCorner = new RectF(0, 0, cornerRadius * 2, cornerRadius * 2);
            rightTopCorner = new RectF(w - sharpCornerWidth - cornerRadius * 2, 0, w - sharpCornerWidth, cornerRadius * 2);
            leftBottomCorner = new RectF(0, h - cornerRadius * 2, cornerRadius * 2, h);
            rightBottomCorner = new RectF(w - sharpCornerWidth - cornerRadius * 2,
                    h - cornerRadius * 2, w - sharpCornerWidth, h);
            path.reset();
            path.moveTo(0, cornerRadius);
            path.arcTo(leftTopCorner, 180, 90);
            path.lineTo(w - sharpCornerWidth - cornerRadius, 0);
            path.arcTo(rightTopCorner, 270, 90);
            path.lineTo(w, cornerRadius + sharpCornerHeight / 2);
            path.lineTo(w - sharpCornerWidth, cornerRadius + sharpCornerHeight);
            path.lineTo(w - sharpCornerWidth, h - cornerRadius);
            path.arcTo(rightBottomCorner, 0, 90);
            path.lineTo(cornerRadius, h);
            path.arcTo(leftBottomCorner, 90, 90);
            path.lineTo(0, h - cornerRadius);
        } else {
            int right = w - sharpCornerWidth;
            path.reset();
            path.moveTo(0, 0);
            path.lineTo(right, 0);
            path.lineTo(right, cornerRadius);
            path.lineTo(w, cornerRadius + sharpCornerWidth);
            path.lineTo(right, cornerRadius + sharpCornerWidth * 2);
            path.lineTo(right, h);
            path.lineTo(0, h);
            path.lineTo(0, 0);
            path.close();
        }
    }

    private void initLeft(int w, int h) {
        if (isRoundCorner) {
            leftTopCorner = new RectF(sharpCornerWidth, 0, sharpCornerWidth + cornerRadius * 2, cornerRadius * 2);
            rightTopCorner = new RectF(w - cornerRadius * 2, 0, w, cornerRadius * 2);
            leftBottomCorner = new RectF(sharpCornerWidth, h - cornerRadius * 2, sharpCornerWidth + cornerRadius * 2, h);
            rightBottomCorner = new RectF(w - cornerRadius * 2,
                    h - cornerRadius * 2, w, h);
            path.reset();
            path.moveTo(sharpCornerWidth, cornerRadius);
            path.arcTo(leftTopCorner, 180, 90);
            path.lineTo(w - cornerRadius, 0);
            path.arcTo(rightTopCorner, 270, 90);
            path.lineTo(w, h - cornerRadius);
            path.arcTo(rightBottomCorner, 0, 90);
            path.lineTo(sharpCornerWidth + cornerRadius, h);
            path.arcTo(leftBottomCorner, 90, 90);
            path.lineTo(sharpCornerWidth, cornerRadius + sharpCornerHeight + sharpCornerStart);

            path.lineTo(0, cornerRadius + sharpCornerStart + sharpCornerHeight / 2);
            path.lineTo(sharpCornerWidth, cornerRadius + sharpCornerStart);
            path.lineTo(sharpCornerWidth, cornerRadius);
        } else {
            path.reset();
            path.moveTo(sharpCornerWidth, 0);
            path.lineTo(sharpCornerWidth, cornerRadius);
            path.lineTo(0, cornerRadius + sharpCornerWidth);
            path.lineTo(sharpCornerWidth, cornerRadius + sharpCornerWidth * 2);
            path.lineTo(sharpCornerWidth, h);
            path.lineTo(w, h);
            path.lineTo(w, 0);
            path.lineTo(sharpCornerWidth, 0);
            path.close();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initShape(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        Drawable drawable = getHierarchy().getTopLevelDrawable();
        Drawable drawable = getDrawable();
        Bitmap bitmap = createFramedPhoto(getWidth(), getHeight(), drawable);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    private Bitmap createFramedPhoto(int x, int y, Drawable drawable) {
        // 新建一个新的输出图片
        Bitmap output = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        // 新建一个矩形
        RectF outerRect = new RectF(0, 0, x, y);

        // 产生一个path特殊形状图形
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawPath(path, paint);

        // 将源图片绘制到这个特殊形状上
        //详解见http://lipeng88213.iteye.com/blog/1189452
        paint.setXfermode(paintMode);
        drawable.setBounds(0, 0, x, y);
        canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
        drawable.draw(canvas);
        canvas.restore();

        return output;
    }

    //dp to px
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
