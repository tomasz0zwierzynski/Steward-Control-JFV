package com.oldwoodsoftware.steward.fragment.gui.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.oldwoodsoftware.steward.R;

public class PanelView extends View {

    private Paint panelFramePaint;
    private Paint panelFillPaint;

    private Paint ballPaint;
    private Paint ballTextPaint;
    private Paint targetPaint;
    private Paint targetTextPaint;

    private float realPanelWidth = 297.0f;
    private float realPanelHeight = 210.0f;

    private float widthheightratio = realPanelHeight/realPanelWidth;

    private Rect panelRect;
    private Rect frameRect;

    public final int PADDING_TOP = 30;
    public final int PADDING_SIDE = 60;
    public final int BORDER_SIZE = 6;
    public final int BALL_RADIUS = 12;

    private Point targetPosition;
    private Point ballPosition;

    public PanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        //Calculate Screen Panel Representation
        int viewWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int viewHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        panelRect = new Rect(0 + PADDING_SIDE, 0 + PADDING_TOP, viewWidth - PADDING_SIDE, (int)((viewWidth - 2 * PADDING_SIDE)*widthheightratio));

        panelFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        panelFillPaint.setStyle(Paint.Style.FILL);
        panelFillPaint.setColor(getResources().getColor(R.color.colorPanelFill));

        frameRect = new Rect(panelRect);

        panelFramePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        panelFramePaint.setStyle(Paint.Style.STROKE);
        panelFramePaint.setStrokeWidth(BORDER_SIZE);
        panelFramePaint.setColor(getResources().getColor(R.color.colorPanelFrame));

        ballPosition = new Point(0, 0);
        ballPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ballPaint.setStyle(Paint.Style.STROKE);
        ballPaint.setStrokeWidth(2.5f);
        ballPaint.setColor(getResources().getColor(R.color.colorBall));

        targetPosition = new Point(100, 100);
        targetPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        targetPaint.setStyle(Paint.Style.STROKE);
        targetPaint.setStrokeWidth(2.5f);
        targetPaint.setColor(getResources().getColor(R.color.colorTarget));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(panelRect,panelFillPaint);
        canvas.drawRect(frameRect,panelFramePaint);
        canvas.drawCircle(ballPosition.x, ballPosition.y, BALL_RADIUS, ballPaint);
        canvas.drawCircle(targetPosition.x, targetPosition.y, BALL_RADIUS, targetPaint);

    }

    public float getLeftEdge(){
        return panelRect.left;
    }

    public float getRightEdge(){
        return panelRect.right;
    }

    public float getTopEdge(){
        return panelRect.top;
    }

    public float getBottomEdge(){
        return panelRect.bottom;
    }

    public boolean isInRect(int pos_x, int pos_y){
        return panelRect.contains(pos_x,pos_y);
    }

    public void setPanelRatio(float ratio){
        widthheightratio = ratio;
        init();
        invalidate();
    }

    public void setBallPosition(int pos_x, int pos_y){ //in pixels
        ballPosition = new Point(pos_x,pos_y);
        invalidate();
    }

    public void setTargetPosition(int pos_x, int pos_y){
        targetPosition = new Point(pos_x,pos_y);
        invalidate();
    }

    //TODO: methods to determine if xy is in plate, and that gives general geometrics of representation
}