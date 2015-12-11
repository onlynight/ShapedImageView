package com.github.onlynight.shapedimageview;

import android.content.Context;
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

import com.github.onlynight.shapedimageview.shape.CirclePath;
import com.github.onlynight.shapedimageview.shape.PentagonPath;
import com.github.onlynight.shapedimageview.shape.RectanglePath;
import com.github.onlynight.shapedimageview.shape.RoundRectanglePath;
import com.github.onlynight.shapedimageview.shape.ShapedPath;
import com.github.onlynight.shapedimageview.shape.StarPath;
import com.github.onlynight.shapedimageview.shape.TrianglePath;

/**
 * Created by lion on 2015/12/10 0010.
 */
public class ShapedImageView extends ImageView {

    public enum SHAPE {
        RECTANGLE(0),
        CIRCLE(1),
        STAR(2),
        TRIANGLE(3),
        ROUND_RECTANGLE(4),
        PENTAGON(5);

        private int value;

        public int getValue() {
            return value;
        }

        SHAPE(int value) {
            this.value = value;
        }
    }

    private static final PorterDuffXfermode PAINT_MODE = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private ShapedPath shapedPath = null;
    private SHAPE shape = SHAPE.RECTANGLE;
    private float roundCornerRadius;
    private Path path;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public ShapedImageView(Context context) {
        super(context);
    }

    public ShapedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapedImageView);
        initAttrs(typedArray);
    }

    public ShapedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapedImageView);
        initAttrs(typedArray);
    }

    private void initAttrs(TypedArray typedArray) {
        int shape = typedArray.getInt(R.styleable.ShapedImageView_shape, this.shape.getValue());
        roundCornerRadius = typedArray.getDimension(R.styleable.ShapedImageView_round_rect_corner_radius, 0);
        setShape(shape);
        typedArray.recycle();
    }

    public void setCustomPath(ShapedPath path) {
        this.shapedPath = path;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (shapedPath != null) {
            if (shapedPath instanceof RoundRectanglePath) {
                ((RoundRectanglePath) shapedPath).setRoundCornerRadius(roundCornerRadius);
            }
            path = shapedPath.getDrawPath(w, h);
        }
    }

    public void setShape(SHAPE shape) {
        this.shape = shape;
        switch (this.shape) {
            case RECTANGLE:
                shapedPath = new RectanglePath();
                break;
            case CIRCLE:
                shapedPath = new CirclePath();
                break;
            case STAR:
                shapedPath = new StarPath();
                break;
            case TRIANGLE:
                shapedPath = new TrianglePath();
                break;
            case ROUND_RECTANGLE:
                shapedPath = new RoundRectanglePath();
                break;
            case PENTAGON:
                shapedPath = new PentagonPath();
                break;
        }
    }

    public void setShape(int value) {
        SHAPE shape = SHAPE.values()[value];
        setShape(shape);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (path != null) {
            Drawable drawable = getDrawable();
            Bitmap bitmap = createFramedPhoto(getWidth(), getHeight(), drawable);
            canvas.drawBitmap(bitmap, 0, 0, paint);
        } else {
            super.onDraw(canvas);
        }
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
        paint.setXfermode(PAINT_MODE);
        drawable.setBounds(0, 0, x, y);
        canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
        drawable.draw(canvas);
        canvas.restore();

        return output;
    }
}
