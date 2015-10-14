package com.example.outsider.mathapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Outsider on 10/5/2015.
 */
public class CanvasView extends View {
    private int width, height;
    private Context context;
    private Path path;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;

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


        invalidate();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawRect(10, 10, 500, 500, paint);

    }
    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
    {
        int viewWidth = MeasureSpec.getSize( widthMeasureSpec );
        int viewHeight = MeasureSpec.getSize( heightMeasureSpec );

        setMeasuredDimension( viewWidth, viewHeight );
    }

}
