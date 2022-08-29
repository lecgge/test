package com.wisdplat.hmi.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

/**
 * @author zoushunhua
 * @Email zouhs@foxmail.com
 * @create 2022-08-4 14:20
 */


public class ViewBuilder {
    /**
     * 内间距
     */
    private int padLeft, padRight, padTop, padBottom;
    /**
     * 外间距
     */
    private int marLeft, marTop, marRight, marBottom;
    /**
     * 背景圆角值
     */
    private int radiusLeftTop, radiusRightTop, radiusLeftBottom, radiusRightBottom;
    /**
     * 宽高
     */
    private int width, height;
    /**
     * 背景色
     */
    private int[] backColor;
    /**
     * 点击的颜色
     */
    private int selectColor;
    /**
     * 边框的大小，颜色
     */
    private int borderWidth, borderColor;
    /**
     * 权重
     */
    private int weight;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 布局方式：0.ViewGroup；1.LinearLayout；2.RelativeLayout
     */
    private int layoutType;
    /**
     * tag
     */
    private Object tag;

    /**
     * 默认布局
     */
    public static ViewBuilder builder(Context context) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 0;
        builder.width = -2;
        builder.height = -2;
        return builder;
    }

    /**
     * 默认布局
     */
    public static ViewBuilder builder(Context context, int width, int height) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 0;
        builder.width = width;
        builder.height = height;
        return builder;
    }

    /**
     * 默认布局
     */
    public static ViewBuilder builder12(Context context) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 0;
        builder.width = -1;
        builder.height = -2;
        return builder;
    }

    /**
     * 默认布局
     */
    public static ViewBuilder builder11(Context context) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 0;
        builder.width = -1;
        builder.height = -1;
        return builder;
    }

    /**
     * 线性布局
     */
    public static ViewBuilder linBuilder(Context context) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 1;
        builder.width = -2;
        builder.height = -2;
        return builder;
    }

    /**
     * 线性布局
     */
    public static ViewBuilder linBuilder(Context context, int width, int height) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 1;
        builder.width = width;
        builder.height = height;
        return builder;
    }

    /**
     * 线性布局
     */
    public static ViewBuilder conBuilder(Context context, int width, int height) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 3;
        builder.width = width;
        builder.height = height;
        return builder;
    }

    /**
     * 线性布局
     */
    public static ViewBuilder linBuilder12(Context context) {

        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 1;
        builder.width = -1;
        builder.height = -2;
        return builder;
    }

    /**
     * 线性布局
     */
    public static ViewBuilder linBuilder11(Context context) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 1;
        builder.width = -1;
        builder.height = -1;
        return builder;
    }

    /**
     * 线性布局---------权重，横向权重
     */
    public static ViewBuilder linBuilder01(Context context, int weight) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 1;
        builder.width = 0;
        builder.height = -2;
        builder.weight = weight;
        return builder;
    }

    /**
     * 相对布局
     */
    public static ViewBuilder relBuilder(Context context) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 2;
        builder.width = -2;
        builder.height = -2;
        return builder;
    }

    /**
     * 相对布局
     */
    public static ViewBuilder relBuilder(Context context, int width, int height) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 2;
        builder.width = width;
        builder.height = height;
        return builder;
    }

    /**
     * 相对布局
     */
    public static ViewBuilder relBuilder12(Context context) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 2;
        builder.width = -1;
        builder.height = -2;
        return builder;
    }

    /**
     * 相对布局
     */
    public static ViewBuilder relBuilder11(Context context) {
        ViewBuilder builder = new ViewBuilder();
        builder.context = context;
        builder.layoutType = 2;
        builder.width = -1;
        builder.height = -12;
        return builder;
    }


    public ViewBuilder pad(int padLeft, int padTop,
                           int padRight, int padBottom) {
        this.padLeft = padLeft;
        this.padTop = padTop;
        this.padRight = padRight;
        this.padBottom = padBottom;
        return this;
    }

    public ViewBuilder mar(int marLeft, int marTop,
                           int marRight, int marBottom) {
        this.marLeft = marLeft;
        this.marTop = marTop;
        this.marRight = marRight;
        this.marBottom = marBottom;
        return this;
    }

    public ViewBuilder radius(int radiusLeftTop, int radiusRightTop,
                              int radiusLeftBottom, int radiusRightBottom) {
        this.radiusLeftTop = radiusLeftTop;
        this.radiusRightTop = radiusRightTop;
        this.radiusLeftBottom = radiusLeftBottom;
        this.radiusRightBottom = radiusRightBottom;
        return this;
    }

    public ViewBuilder radius(int radius) {
        this.radiusLeftTop = radius;
        this.radiusRightTop = radius;
        this.radiusLeftBottom = radius;
        this.radiusRightBottom = radius;
        return this;
    }

    public ViewBuilder backs(int... backColor) {
        this.backColor = backColor;
        return this;
    }

    public ViewBuilder back(int backColor) {
        this.backColor = new int[]{backColor};
        return this;
    }

    public ViewBuilder back(int backColor, int selectColor) {
        this.backColor = new int[]{backColor, selectColor};
        this.selectColor = selectColor;
        return this;
    }

    public ViewBuilder border(int borderColor, int borderWidth) {
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        return this;
    }


    public ViewBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    protected Float toFloat(double per) {
        return Double.valueOf(1.6 * per).floatValue();
    }

    protected int colorByRes(int resId) {
        return ContextCompat.getColor(context, resId);
    }

    protected void setLayout(View target) {
        switch (layoutType) {
            case 0:
                ViewGroup.LayoutParams vgGarams = new ViewGroup.LayoutParams(width, height);
                target.setLayoutParams(vgGarams);
                break;
            case 1:
                LinearLayout.LayoutParams llParams;
                if (weight == 0) {
                    llParams = new LinearLayout.LayoutParams(width, height);
                } else {
                    llParams = new LinearLayout.LayoutParams(width, height, weight);
                }
                llParams.setMargins(marLeft, marTop, marRight, marBottom);
                target.setLayoutParams(llParams);
                break;
            case 2:
                RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(width, height);
                target.setLayoutParams(rlParams);
                rlParams.setMargins(marLeft, marTop, marRight, marBottom);
                target.setLayoutParams(rlParams);
                break;
            case 3:
                ConstraintLayout.LayoutParams conParams = new ConstraintLayout.LayoutParams(width, height);
                target.setLayoutParams(conParams);
                conParams.setMargins(marLeft, marTop, marRight, marBottom);
                target.setLayoutParams(conParams);
                break;
        }
        target.setPadding(padLeft, padTop, padRight, padBottom);
        if (tag != null) {
            target.setTag(tag);
        }

    }

    protected void setBackStyle(View target) {
        if (backColor != null) {
            float tl = toFloat(radiusLeftTop);
            float tr = toFloat(radiusRightTop);
            float bl = toFloat(radiusLeftBottom);
            float br = toFloat(radiusRightBottom);
            float[] radius = new float[]{tl, tl, tr, tr, br, br, bl, bl};
            if (backColor.length == 1) {
                //设置普通背景
                GradientDrawable normal = new GradientDrawable();
                normal.setColor(backColor[0]);
                normal.setCornerRadii(radius);
                normal.setStroke(borderWidth, borderColor);
                target.setBackground(normal);
            } else if (backColor.length == 2) {
                target.setClickable(true);
                if (selectColor != 0) {
                    //点击事件
                    GradientDrawable normal = new GradientDrawable();
                    normal.setColor(backColor[0]);
                    normal.setCornerRadii(radius);
                    normal.setStroke(borderWidth, borderColor);
                    //
                    GradientDrawable select = new GradientDrawable();
                    select.setColor(selectColor);
                    select.setCornerRadii(radius);
                    select.setStroke(borderWidth, borderColor);
                    //
                    StateListDrawable drawable = new StateListDrawable();
                    drawable.addState(new int[]{android.R.attr.state_selected}, select);
                    drawable.addState(new int[]{android.R.attr.state_pressed}, select);
                    drawable.addState(new int[]{android.R.attr.state_enabled}, normal);
                    drawable.addState(new int[]{}, normal);
                    target.setBackground(drawable);
                } else {
                    //处理渐变
                    GradientDrawable normal = new GradientDrawable(
                            GradientDrawable.Orientation.TOP_BOTTOM, backColor);
                    normal.setCornerRadii(radius);
                    normal.setStroke(borderWidth, borderColor);
                    StateListDrawable drawable = new StateListDrawable();
                    drawable.addState(new int[]{android.R.attr.state_selected}, normal);
                    drawable.addState(new int[]{android.R.attr.state_pressed}, normal);
                    drawable.addState(new int[]{android.R.attr.state_enabled}, normal);
                    drawable.addState(new int[]{}, normal);
                    target.setBackground(normal);
                }
            }
        }
    }

    public void setTextStyle(View target, int color1, int color2) {
        if (!(target instanceof TextView)) {
            return;
        }
        if (backColor != null) {
            float tl = toFloat(radiusLeftTop);
            float tr = toFloat(radiusRightTop);
            float bl = toFloat(radiusLeftBottom);
            float br = toFloat(radiusRightBottom);
            float[] radius = new float[]{tl, tl, tr, tr, br, br, bl, bl};
            if (backColor.length == 1) {
                //设置普通背景
                GradientDrawable normal = new GradientDrawable();
                normal.setColor(backColor[0]);
                normal.setCornerRadii(radius);
                normal.setStroke(borderWidth, borderColor);
                target.setBackground(normal);
            } else if (backColor.length == 2) {
                if (selectColor != 0) {
                    //点击事件
                    GradientDrawable normal = new GradientDrawable();
                    normal.setColor(backColor[0]);
                    normal.setCornerRadii(radius);
                    normal.setStroke(borderWidth, borderColor);
                    //
                    GradientDrawable select = new GradientDrawable();
                    select.setColor(selectColor);
                    select.setCornerRadii(radius);
                    select.setStroke(borderWidth, borderColor);
                    //
                    StateListDrawable drawable = new StateListDrawable();
                    drawable.addState(new int[]{android.R.attr.state_selected}, select);
                    drawable.addState(new int[]{android.R.attr.state_pressed}, select);
                    drawable.addState(new int[]{android.R.attr.state_enabled}, normal);
                    drawable.addState(new int[]{}, normal);
                    target.setBackground(drawable);
                } else {
                    //处理渐变
                    GradientDrawable normal = new GradientDrawable(
                            GradientDrawable.Orientation.TOP_BOTTOM, backColor);
                    normal.setCornerRadii(radius);
                    StateListDrawable drawable = new StateListDrawable();
                    drawable.addState(new int[]{android.R.attr.state_selected}, normal);
                    drawable.addState(new int[]{android.R.attr.state_pressed}, normal);
                    drawable.addState(new int[]{android.R.attr.state_enabled}, normal);
                    drawable.addState(new int[]{}, normal);
                    target.setBackground(normal);
                }
            }
        }
    }

    public LinearLayout createLinVer() {
        LinearLayout layout = new LinearLayout(context);
        setLayout(layout);
        layout.setOrientation(LinearLayout.VERTICAL);
        return layout;
    }

    public LinearLayout createLinHor() {
        LinearLayout layout = new LinearLayout(context);
        setLayout(layout);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        return layout;
    }

    public TextView createTextView(String text) {
        return createTextView(text, Color.DKGRAY, 0);
    }

    public TextView createTextView(String text, int color) {
        return createTextView(text, color, 0);
    }

    public TextView createTextView(String text, int foreColor1, int foreColor2) {
        TextView tv = new TextView(context);
        setLayout(tv);
        setBackStyle(tv);
        if (!TextUtils.isEmpty(text)) {
            tv.setText(text);
        }
        if (foreColor2 != 0) {

            ColorStateList colorStateList = new ColorStateList(new int[][]{
                    {-android.R.attr.state_pressed},
                    {android.R.attr.state_pressed}
            }, new int[]{foreColor1, foreColor2});
            tv.setTextColor(colorStateList);
        } else {
            tv.setTextColor(foreColor1);
        }
        return tv;
    }


    public EditText createEdt(String text) {
        return createEdt(text, null);
    }

    public EditText createEdt(String text, String hint) {
        EditText edt = new EditText(context);
        setLayout(edt);
        setBackStyle(edt);
        edt.setTextSize(14f);
        if (!TextUtils.isEmpty(text)) {
            edt.setText(text);
        }
        if (!TextUtils.isEmpty(hint)) {
            edt.setHint(hint);
        }
        return edt;
    }

    public ImageView createImageView() {
        ImageView iv = new ImageView(context);
        setLayout(iv);
        return iv;
    }

}