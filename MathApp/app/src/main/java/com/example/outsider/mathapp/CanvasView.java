package com.example.outsider.mathapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Outsider on 10/5/2015.
 */
public class CanvasView extends View {
    private Canvas canvas;
    private Paint paint;

    private String shape;
    private double width, height;

    public CanvasView(Context c, AttributeSet att){
        super(c, att);
        setWillNotDraw(false);

        paint = new Paint(); // Paint options we use when it comes time to draw one of our shapes
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(10f);
        shape = "None"; // So that nothing is drawn, this could have been anything, could have left it blank, or called it null, or chicken, since none of these are one of our options

        invalidate(); // Tells the canvas to redraw itself... I don't think I actually need this here, but it doesn't hurt

    }

    public void setShape(String shape, double width, double height){
        this.shape = shape;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        canvas = new Canvas(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
    }

    @Override
    protected void onDraw(Canvas canvas){
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        switch(shape) {
            case "Rectangle":
                canvas.drawRect(10, 10, (float)(50 * width), (float)(50 * height), paint); // x, y, width, height, paint we set above
                break;
            case "Ellipse":
                canvas.drawOval(new RectF(10, 10, (float)(50 * width), (float)(50 * height)), paint);
                break;
            case "Triangle":  // There is no built in triangle drawing tool, so we make our own using a path
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
