package com.triplebro.aran.hustlestore.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.triplebro.aran.hustlestore.R;


/**
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * .
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 * .
 * Created by Aran on 2018/10/23.
 */


public class DeleteEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable mDeleteDrawable;
    private boolean hasFocus;

    public DeleteEditText(Context context) {
        super(context);
    }

    public DeleteEditText(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.editTextStyle);
    }

    public DeleteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("A","ed初始化成功");
        if (!isInEditMode()) {
            init();
        }
    }

    private void init() {
        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片,获取图片的顺序是左上右下（0,1,2,3,）  
        mDeleteDrawable = getCompoundDrawables()[2];
        if (mDeleteDrawable == null) {
            mDeleteDrawable = getResources().getDrawable(
                    R.drawable.bt_deletedrawable);
        }

        mDeleteDrawable.setBounds(0, 0, mDeleteDrawable.getIntrinsicWidth(),mDeleteDrawable.getIntrinsicHeight());
        // 默认设置隐藏图标  
        setClearIconVisible(false);
        // 设置焦点改变的监听  
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听  
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int x = (int)event.getX();
                int y = (int)event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height)/2;
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y <(distance + height);
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }

    }

    private void setBackgroundDark(){

    }
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mDeleteDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (hasFocus) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
