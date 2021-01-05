package com.example.wanandroid_vpa.discover.activity;


import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.wanandroid_vpa.util.ScreenUtils;


public class QRCodeView extends View {

    /**
     * view开始和结束时后的位置
     */
    private PointF currentPoint, startPoint;
    private float maxScaleX;
    private float mHeight, mWidth;
    private Bitmap mBitMap;
    private long mDuration;
    private Paint mPaint;

    /**
     * 原始View的位置
     */
    Rect viewRect, startRect;

    public QRCodeView(Context context) {
        super(context);
        initView();
    }

    public QRCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public QRCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        currentPoint = new PointF(0, 0);
        startPoint = new PointF(0, 0);
        setClickable(true);
        mDuration = 300;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (viewRect != null && mBitMap != null) {
            double v = viewRect.width() * 1.0 / startRect.width();
            mPaint.setColor(Color.BLACK);
            mPaint.setAlpha((int) ((int) (255 * v) / maxScaleX));
            canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
            canvas.save();
            canvas.translate(currentPoint.x, currentPoint.y);
            Bitmap bitmap = createBitmap(this.mBitMap, viewRect);
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.restore();
        }
    }

    /**
     * 缩放bitmap
     *
     * @param bitmap
     * @param viewRect
     * @return
     */
    private Bitmap createBitmap(Bitmap bitmap, Rect viewRect) {
        float width = viewRect.width();
        float height = width * (bitmap.getHeight() * 1.0f / bitmap.getWidth());
        return Bitmap.createScaledBitmap(bitmap, (int) width, (int) height, true);
    }

    private void startAnimation() {
        //起始数据
        float startTranslateY = startPoint.y;
        float startTranslateX = startPoint.x;
        int right = startRect.right;
        int bottom = startRect.bottom;
        PointF pointF = new PointF();
        pointF.x = right;
        pointF.y = bottom;
        ViewPoint point = new ViewPoint(startTranslateX, startTranslateY, pointF);
        //结束数据
        float width = mWidth;
        float height = mHeight;
        double v = mBitMap.getWidth() * 1.0 / mBitMap.getHeight();
        float endTranX = 0;
        float endTranY = 0;

        if (mWidth / mHeight > v) {
            width = (float) (mHeight * v);
            endTranX = (mWidth - width) / 2;
        } else {
            height = (float) (width / v);
            endTranY = (mHeight - height) / 2;
        }
        PointF pointF1 = new PointF(width, height);
        ViewPoint endPoint = new ViewPoint(endTranX, endTranY, pointF1);
        getAnimation(point, endPoint);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(() -> {
            mWidth = getWidth();
            mHeight = getHeight();
            //由于部分时候,图片并不是完全填充View,所以需要处理一下
            float width = startRect.width();
            float height = startRect.height();
            double v = mBitMap.getWidth() * 1.0 / mBitMap.getHeight();
            float endTranX = 0;
            float endTranY = 0;

            if (width / height > v) {
                endTranX = (float) ((width - height * v) / 2);
                width = (float) (height * v);
            } else {
                endTranY = (float) ((height - width / v) / 2);
                height = (float) (width / v);
            }
            viewRect.right = (int) width;
            viewRect.bottom = (int) height;
            startRect.right = (int) width;
            startRect.bottom = (int) height;
            currentPoint.x += endTranX;
            currentPoint.y += endTranY;
            startPoint.x += endTranX;
            startPoint.y += endTranY;
            float maxScaleX = mWidth * 1.0f / startRect.width();
            float maxScaleY = mHeight * 1.0f / startRect.height();
            QRCodeView.this.maxScaleX = Math.min(maxScaleX, maxScaleY);
            startAnimation();
        });
    }

    /**
     * 自定义估值算法
     * Created by Administrator on 2016/2/2.
     */
    public static class MyTypeEvaluator implements TypeEvaluator<ViewPoint> {
        @Override
        public ViewPoint evaluate(float fraction, ViewPoint startValue, ViewPoint endValue) {
            ViewPoint point = new ViewPoint();
            float value = fraction * fraction;
            value = (float) Math.pow(value, 0.5);
            point.translateX = startValue.translateX + (endValue.translateX - startValue.translateX) * value;
            point.translateY = startValue.translateY + (endValue.translateY - startValue.translateY) * value;
            point.sizePoint.x = startValue.sizePoint.x + (endValue.sizePoint.x - startValue.sizePoint.x) * value;
            point.sizePoint.y = startValue.sizePoint.y + (endValue.sizePoint.y - startValue.sizePoint.y) * value;
            return point;
        }
    }

    /**
     * 保存小球坐标的类
     * Created by Administrator on 2016/2/2.
     */
    public static class ViewPoint {
        public float translateX;
        public float translateY;
        public PointF sizePoint;

        public ViewPoint() {
            sizePoint = new PointF();
        }

        public ViewPoint(float translateX, float translateY, PointF sizePoint) {
            this.translateX = translateX;
            this.translateY = translateY;
            this.sizePoint = sizePoint;
        }
    }

    public void setOriginView(int[] info, Bitmap bitmap) {
        viewRect = new Rect();
        startRect = new Rect();
        //获取控件的宽高
        int width = info[2] - info[0];
        int height = info[3] - info[1];
        viewRect.right = width;
        viewRect.bottom = height;
        startRect.right = width;
        startRect.bottom = height;
        currentPoint.x = info[0];
        currentPoint.y = info[1] - ScreenUtils.INSTANCE.getStatusBarHeight(getContext());
        startPoint.x = info[0];
        startPoint.y = info[1] - ScreenUtils.INSTANCE.getStatusBarHeight(getContext());
        this.mBitMap = bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
    }

    ValueAnimator animator;

    private void getAnimation(ViewPoint point, ViewPoint endPoint) {
        clear();
        animator = ValueAnimator.ofObject(new MyTypeEvaluator(), point, endPoint);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            ViewPoint point1 = (ViewPoint) animation.getAnimatedValue();
            viewRect.right = (int) point1.sizePoint.x;
            viewRect.left = 0;
            viewRect.top = 0;
            viewRect.bottom = (int) point1.sizePoint.y;
            currentPoint.y = point1.translateY;
            currentPoint.x = point1.translateX;
            postInvalidate();
        });
        animator.setDuration(mDuration);
        animator.start();
    }

    private void clear() {
        if (animator != null) {
            animator.cancel();
        }
    }

}
