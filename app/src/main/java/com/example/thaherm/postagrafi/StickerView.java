package com.example.thaherm.postagrafi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Thaher.m on 13-12-2017.
 */

public abstract class StickerView extends FrameLayout {

    public static final String TAG = "TOUCH";
    private BorderView iv_border;
    private ImageView iv_scale;
//    private ImageView iv_delete;
//    private ImageView iv_flip;

    // For scalling
    private float this_orgX = -1, this_orgY = -1;
    private float scale_orgX = -1, scale_orgY = -1;
    private double scale_orgWidth = -1, scale_orgHeight = -1;
    // For rotating
    private float rotate_orgX = -1, rotate_orgY = -1, rotate_newX = -1, rotate_newY = -1;
    // For moving
    private float move_orgX =-1, move_orgY = -1;

    private double centerX, centerY;

    private final static int BUTTON_SIZE_DP = 30;
    private final static int SELF_SIZE_DP = 100;
    private Boolean contlerinvisible = true;



    public StickerView(Context context) {
        super(context);
        init(context);
    }

    public StickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }



//------------------------------------------


    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public float getThis_orgX() {
        return this_orgX;
    }

    public void setThis_orgX(float this_orgX) {
        this.this_orgX = this_orgX;
    }

    public float getThis_orgY() {
        return this_orgY;
    }

    public void setThis_orgY(float this_orgY) {
        this.this_orgY = this_orgY;
    }

    public float getScale_orgX() {
        return scale_orgX;
    }

    public void setScale_orgX(float scale_orgX) {
        this.scale_orgX = scale_orgX;
    }

    public float getScale_orgY() {
        return scale_orgY;
    }

    public void setScale_orgY(float scale_orgY) {
        this.scale_orgY = scale_orgY;
    }

    public double getScale_orgWidth() {
        return scale_orgWidth;
    }

    public void setScale_orgWidth(double scale_orgWidth) {
        this.scale_orgWidth = scale_orgWidth;
    }

    public double getScale_orgHeight() {
        return scale_orgHeight;
    }

    public void setScale_orgHeight(double scale_orgHeight) {
        this.scale_orgHeight = scale_orgHeight;
    }

    public float getRotate_orgX() {
        return rotate_orgX;
    }

    public void setRotate_orgX(float rotate_orgX) {
        this.rotate_orgX = rotate_orgX;
    }

    public float getRotate_orgY() {
        return rotate_orgY;
    }

    public void setRotate_orgY(float rotate_orgY) {
        this.rotate_orgY = rotate_orgY;
    }

    public float getRotate_newX() {
        return rotate_newX;
    }

    public void setRotate_newX(float rotate_newX) {
        this.rotate_newX = rotate_newX;
    }

    public float getRotate_newY() {
        return rotate_newY;
    }

    public void setRotate_newY(float rotate_newY) {
        this.rotate_newY = rotate_newY;
    }

    public float getMove_orgX() {
        return move_orgX;
    }

    public void setMove_orgX(float move_orgX) {
        this.move_orgX = move_orgX;
    }

    public float getMove_orgY() {
        return move_orgY;
    }

    public void setMove_orgY(float move_orgY) {
        this.move_orgY = move_orgY;
    }

    //------------------------------------

    private void init(Context context){
        this.iv_border = new BorderView(context);
        this.iv_scale = new ImageView(context);
//        this.iv_delete = new ImageView(context);
//        this.iv_flip = new ImageView(context);

        this.iv_scale.setImageResource(R.drawable.zoominout);
//        this.iv_delete.setImageResource(R.drawable.remove);
//        this.iv_flip.setImageResource(R.drawable.flip);

        this.setTag("DraggableViewGroup");
        this.iv_border.setTag("iv_border");
        this.iv_scale.setTag("iv_scale");
//        this.iv_delete.setTag("iv_delete");
//        this.iv_flip.setTag("iv_flip");
        setControlItemsHidden();

        int margin = convertDpToPixel(BUTTON_SIZE_DP, getContext())/2;
        int size = convertDpToPixel(SELF_SIZE_DP, getContext());

        LayoutParams this_params =
                new LayoutParams(
                        size,
                        size
                );
        this_params.gravity = Gravity.NO_GRAVITY;

        LayoutParams iv_main_params =
                new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
        iv_main_params.setMargins(margin,margin,margin,margin);

        LayoutParams iv_border_params =
                new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
        iv_border_params.setMargins(margin,margin,margin,margin);

        LayoutParams iv_scale_params =
                new LayoutParams(
                        convertDpToPixel(BUTTON_SIZE_DP, getContext()),
                        convertDpToPixel(BUTTON_SIZE_DP, getContext())
                );
        iv_scale_params.gravity = Gravity.BOTTOM | Gravity.RIGHT;

        LayoutParams iv_delete_params =
                new LayoutParams(
                        convertDpToPixel(BUTTON_SIZE_DP, getContext()),
                        convertDpToPixel(BUTTON_SIZE_DP, getContext())
                );
        iv_delete_params.gravity = Gravity.TOP | Gravity.RIGHT;

        LayoutParams iv_flip_params =
                new LayoutParams(
                        convertDpToPixel(BUTTON_SIZE_DP, getContext()),
                        convertDpToPixel(BUTTON_SIZE_DP, getContext())
                );
        iv_flip_params.gravity = Gravity.TOP | Gravity.LEFT;

        this.setLayoutParams(this_params);
        this.addView(getMainView(), iv_main_params);
        this.addView(iv_border, iv_border_params);
        this.addView(iv_scale, iv_scale_params);
//        this.addView(iv_delete, iv_delete_params);
//        this.addView(iv_flip, iv_flip_params);
        this.setOnTouchListener(mTouchListener);
        this.iv_scale.setOnTouchListener(mTouchListener);
//        this.iv_delete.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(StickerView.this.getParent()!=null){
//                    ViewGroup myCanvas = ((ViewGroup)StickerView.this.getParent());
//                    myCanvas.removeView(StickerView.this);
//                }
//            }
//        });
//        this.iv_flip.setOnClickListener(new OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                Log.v(TAG, "flip the view");
//
//                View mainView = getMainView();
//                mainView.setRotationY(mainView.getRotationY() == -180f? 0f: -180f);
//                mainView.invalidate();
//                requestLayout();
//            }
//        });
    }

    public boolean isFlip(){
        return getMainView().getRotationY() == -180f;
    }

    protected abstract View getMainView();

    private OnTouchListener mTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {


            if(view.getTag().equals("DraggableViewGroup")) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.v(TAG, "sticker view action down");
                        move_orgX = event.getRawX();
                        move_orgY = event.getRawY();
                        contlerinvisible=false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.v(TAG, "sticker view action move");
                        float offsetX = event.getRawX() - move_orgX;
                        float offsetY = event.getRawY() - move_orgY;
                        StickerView.this.setX(StickerView.this.getX()+offsetX);
                        StickerView.this.setY(StickerView.this.getY() + offsetY);
                        move_orgX = event.getRawX();
                        move_orgY = event.getRawY();
                        Log.v("XXXXXXXXX", "move_orgX "+move_orgX +" centerx" + getX());
                        Log.v("XXXXXXXXX", "move move_orgY "+move_orgY+" centery" + getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.v(TAG, "sticker view action up");
                        contlerinvisible = true;
                        break;
                }
                setControlItemsHidden();

            }else if(view.getTag().equals("iv_scale")){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.v(TAG, "iv_scale action down");
                        contlerinvisible = false;
                        this_orgX = StickerView.this.getX();
                        this_orgY = StickerView.this.getY();

                        scale_orgX = event.getRawX();
                        scale_orgY = event.getRawY();
                        scale_orgWidth = StickerView.this.getLayoutParams().width;
                        scale_orgHeight = StickerView.this.getLayoutParams().height;

                        rotate_orgX = event.getRawX();
                        rotate_orgY = event.getRawY();

                        centerX = StickerView.this.getX()+
                                ((View)StickerView.this.getParent()).getX()+
                                (float)StickerView.this.getWidth()/2;


                        //double statusBarHeight = Math.ceil(25 * getContext().getResources().getDisplayMetrics().density);
                        int result = 0;
                        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                        if (resourceId > 0) {
                            result = getResources().getDimensionPixelSize(resourceId);
                        }
                        double statusBarHeight = result;
                        centerY = StickerView.this.getY()+
                                ((View)StickerView.this.getParent()).getY()+
                                statusBarHeight+
                                (float)StickerView.this.getHeight()/2;



                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.v(TAG, "iv_scale action move");
                        Log.v("BUHAHAH", "iv_scale action move");

                        rotate_newX = event.getRawX();
                        rotate_newY = event.getRawY();

                        double angle_diff = Math.abs(
                                Math.atan2(event.getRawY() - scale_orgY , event.getRawX() - scale_orgX)
                                        - Math.atan2(scale_orgY - centerY, scale_orgX - centerX))*180/Math.PI;

                        Log.v(TAG, "angle_diff: "+angle_diff);
                        Log.v("BUHAHAH", "angle_diff: "+angle_diff);

                        double length1 = getLength(centerX, centerY, scale_orgX, scale_orgY);
                        double length2 = getLength(centerX, centerY, event.getRawX(), event.getRawY());

                        int size = convertDpToPixel(SELF_SIZE_DP, getContext());
                        if(length2 > length1
                                && (angle_diff < 25 || Math.abs(angle_diff-180)<25)
                                ) {
                            //scale up
                            double offsetX = Math.abs(event.getRawX() - scale_orgX);
                            double offsetY = Math.abs(event.getRawY() - scale_orgY);
                            double offset = Math.max(offsetX, offsetY);
                            offset = Math.round(offset);
                            StickerView.this.getLayoutParams().width += offset;
                            StickerView.this.getLayoutParams().height += offset;
                            onScaling(true);
                            //DraggableViewGroup.this.setX((float) (getX() - offset / 2));
                            //DraggableViewGroup.this.setY((float) (getY() - offset / 2));
                        }else if(length2 < length1
                                && (angle_diff < 25 || Math.abs(angle_diff-180)<25)
                                && StickerView.this.getLayoutParams().width > size/2
                                && StickerView.this.getLayoutParams().height > size/2) {
                            //scale down
                            double offsetX = Math.abs(event.getRawX() - scale_orgX);
                            double offsetY = Math.abs(event.getRawY() - scale_orgY);
                            double offset = Math.max(offsetX, offsetY);
                            offset = Math.round(offset);
                            StickerView.this.getLayoutParams().width -= offset;
                            StickerView.this.getLayoutParams().height -= offset;
                            onScaling(false);
                        }

                        //rotate

                        double angle = Math.atan2(event.getRawY() - centerY, event.getRawX() - centerX) * 180 / Math.PI;
                        Log.v(TAG, "log angle: " + angle);
                        Log.v("BUHAHAH", "log angle: " + angle);

                        //setRotation((float) angle - 45);
                        setRotation((float) angle - 45);
                        Log.v(TAG, "getRotation(): " + getRotation());
                        Log.v("BUHAHAH", "getRotation(): " + getRotation());

                        onRotating();

                        rotate_orgX = rotate_newX;
                        rotate_orgY = rotate_newY;

                        scale_orgX = event.getRawX();
                        scale_orgY = event.getRawY();
                        Log.v("BUHAHAH", "scale_orgX(): " + scale_orgX);
                        Log.v("BUHAHAH", "scale_orgY(): " + scale_orgY);

                        postInvalidate();
                        requestLayout();
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.v(TAG, "iv_scale action up");
                        contlerinvisible = true;
                        break;
                }
                setControlItemsHidden();
            }
            return true;
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private double getLength(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(y2-y1, 2)+Math.pow(x2-x1, 2));
    }

    public float[] getRelativePos(float absX, float absY){
        Log.v("ken", "getRelativePos getX:"+((View)this.getParent()).getX());
        Log.v("ken", "getRelativePos getY:"+((View)this.getParent()).getY());
        float [] pos = new float[]{
                absX-((View)this.getParent()).getX(),
                absY-((View)this.getParent()).getY()
        };
        Log.v(TAG, "getRelativePos absY:"+absY);
        Log.v(TAG, "getRelativePos absX:"+absX);
        Log.v(TAG, "getRelativePos relativeY:"+pos[1]);
        Log.v(TAG, "getRelativePos relativeX:"+pos[0]);
        Log.v("BUHAHAH", "getRelativePos absY:"+absY);
        Log.v("BUHAHAH", "getRelativePos absX:"+absX);
        Log.v("BUHAHAH", "getRelativePos relativeY:"+pos[1]);
        Log.v("BUHAHAH", "getRelativePos relativeX:"+pos[0]);
        return pos;
    }

    public void setControlItemsHidden( ){
        if(contlerinvisible) {

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    if(contlerinvisible)
                    {
                        iv_border.setVisibility(View.INVISIBLE);
                        iv_scale.setVisibility(View.INVISIBLE);
                    }
                }
            }, 2000);
//            iv_delete.setVisibility(View.INVISIBLE);
//            iv_flip.setVisibility(View.INVISIBLE);
        }else{


            iv_border.setVisibility(View.VISIBLE);
            iv_scale.setVisibility(View.VISIBLE);
//            iv_delete.setVisibility(View.VISIBLE);
//            iv_flip.setVisibility(View.VISIBLE);
        }
    }

//    protected View getImageViewFlip(){
//        return iv_flip;
//    }

    protected void onScaling(boolean scaleUp){}

    protected void onRotating(){}

    private class BorderView extends View{

        public BorderView(Context context) {
            super(context);
        }

        public BorderView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public BorderView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // Draw sticker border

            LayoutParams params = (LayoutParams)this.getLayoutParams();

            Log.v(TAG,"params.leftMargin: "+params.leftMargin);

            Rect border = new Rect();
            border.left = (int)this.getLeft()-params.leftMargin;
            border.top = (int)this.getTop()-params.topMargin;
            border.right = (int)this.getRight()-params.rightMargin;
            border.bottom = (int)this.getBottom()-params.bottomMargin;
            Paint borderPaint = new Paint();
            borderPaint.setStrokeWidth(6);
            borderPaint.setColor(Color.WHITE);
            borderPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(border, borderPaint);

        }
    }

    private static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int)px;
    }
}
