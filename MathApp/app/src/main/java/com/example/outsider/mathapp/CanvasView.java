package com.example.outsider.mathapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Outsider on 10/5/2015.
 */
public class CanvasView extends View {
    private Context context;
    private Path path;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;

    private String shape;
    private double width, height;

    public CanvasView(Context c, AttributeSet att){
        super(c, att);
        setWillNotDraw(false);

        context = c;
        path = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(4f);
        shape = "None";

        invalidate();

    }

    public void setShape(String shape, double width, double height){
        this.shape = shape;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas){
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        switch(shape) {
            case "Rectangle":
                canvas.drawRect(10, 10, (float)(50 * width), (float)(50 * height), paint);
                break;
            case "Ellipse":
                canvas.drawOval(new RectF(10, 10, (float)(50 * width), (float)(50 * height)), paint);
                break;
            case "Triangle":
                Path path = new Path();
                path.moveTo(10,10);
                path.lineTo(10, (float)(50 * height));
                path.moveTo(10, (float)(50 * height));
                path.lineTo((float)(50 * width), (float)(50 * height));
                path.moveTo((float)(50 * width), (float)(50 * height));
                path.lineTo(10,10);
                path.close();
                canvas.drawPath(path, paint);
                break;
            default:

                break;
        }
    }
    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
    {
        int viewWidth = MeasureSpec.getSize( widthMeasureSpec );
        int viewHeight = MeasureSpec.getSize( heightMeasureSpec );

        setMeasuredDimension( viewWidth, viewHeight );
    }

}
