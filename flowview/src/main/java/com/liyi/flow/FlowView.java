package com.liyi.flow;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.liyi.flow.adapter.BaseFlowAdapter;
import com.liyi.view.R;

import java.util.ArrayList;


/**
 * 流布局控件
 */
public class FlowView extends ViewGroup {
    /**
     * 默认值
     */
    // 默认流布局横向对齐方式
    private final int DEF_FlOW_HORALIGN = FlowConfig.FLOW_HOR_LEFT;
    // 默认流布局纵向对齐方式
    private final int DEF_FlOW_VERTALIGN = FlowConfig.FLOW_VERT_MIDDLE;
    // 默认流布局的行高
    private final int DEF_FLOW_HEIGHT = FlowConfig.INVALID_VAL;
    // 默认流布局最大显示行数
    private final int DEF_FLOW_MAX_ROWS = FlowConfig.INVALID_VAL;
    // 默认流布局的横向间距
    private final int DEF_FlOW_HSPACE = 10;
    // 默认流布局的纵向间距
    private final int DEF_FlOW_VSPACE = 10;

    /**
     * 变量
     */
    // 流布局的横向排列方式
    private int mFlowHorAlign;
    // 流布局的纵向排列方式
    private int mFlowVertAlign;
    // 流布局的行高
    private float mFlowHeight;
    // 流布局的最大显示行数
    private int mFlowMaxRows;
    // 流布局的 item 之间的横向间距
    private float mFlowHspace;
    // 流布局的 item 之间的纵向间距
    private float mFlowVspace;
    // 记录流布局的行信息 ===> item 的个数、最后一个 item 的序号、行高度
    private ArrayList<float[]> mFlowParamList;

    private BaseFlowAdapter mAdapter;
    private OnItemClickListener mItemClickListener;


    public FlowView(Context context) {
        super(context);
        init(context, null);
    }

    public FlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        initParams();
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowView);
            if (a != null) {
                mFlowHorAlign = a.getInt(R.styleable.FlowView_flow_horalign, mFlowHorAlign);
                mFlowVertAlign = a.getInt(R.styleable.FlowView_flow_vertalign, mFlowVertAlign);
                mFlowHeight = a.getDimension(R.styleable.FlowView_flow_height, mFlowHeight);
                mFlowHspace = a.getDimension(R.styleable.FlowView_flow_hspace, mFlowHspace);
                mFlowVspace = a.getDimension(R.styleable.FlowView_flow_vspace, mFlowVspace);
                mFlowMaxRows = a.getInt(R.styleable.FlowView_flow_maxRows, mFlowMaxRows);
                a.recycle();
            }
        }
    }

    private void initParams() {
        mFlowHeight = DEF_FLOW_HEIGHT;
        mFlowMaxRows = DEF_FLOW_MAX_ROWS;
        mFlowHspace = DEF_FlOW_HSPACE;
        mFlowVspace = DEF_FlOW_VSPACE;
        mFlowHorAlign = DEF_FlOW_HORALIGN;
        mFlowVertAlign = DEF_FlOW_VERTALIGN;
        mFlowParamList = new ArrayList<float[]>();
    }

    public void setAdapter(BaseFlowAdapter adapter) {
        this.mAdapter = adapter;
        removeAllViews();
        if (mAdapter != null && mAdapter.getCount() > 0) {
            addItemViews();
        }
    }

    /**
     * 添加 itemView
     */
    private void addItemViews() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View itemView = mAdapter.getView(i, null, this);
            addItemClickListener(itemView, i);
            addView(itemView);
        }
    }

    /**
     * 为 itemView 添加点击事件
     *
     * @param view
     * @param position
     */
    private void addItemClickListener(View view, final int position) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position, v);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (mAdapter == null || getChildCount() == 0) {
            setMeasuredDimension(widthSize, heightMode == MeasureSpec.EXACTLY ? heightSize : getPaddingTop() + getPaddingBottom());
            return;
        }
        int width = widthSize - getPaddingLeft() - getPaddingRight();
        int childCount = getChildCount();
        // child 的横坐标
        int x = 0;
        // child 的纵坐标
        int y = 0;
        // 每行 item 的个数
        int ecount = 0;
        // 流布局中 children 的总高度（多加了一个 mFlowVspace ）
        float h = 0;
        mFlowParamList.clear();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            // 宽：将 width 作为 child 可以使用的最大宽
            // 高：不指定 child 的高，child 想要多高，就给它多高
            child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST), MeasureSpec.UNSPECIFIED);
            int cw = child.getMeasuredWidth();
            int ch = child.getMeasuredHeight();
            x += cw;
            // 判断 child 是否放入本行（小于 width ，则 child 放入本行，否则转入下行）
            if (x <= width) {
                ecount++;
                // 取最大的值作为 child 的高度
                y = y < ch ? ch : y;
                // 判断是否有可能放入下个 child
                if (x <= (width - mFlowHspace)) {
                    x += mFlowHspace;
                    if (i == (childCount - 1)) {
                        /** 提交最后一行的信息 */
                        // 如果没有设置 child 的高度，则采用测量出的高度 y
                        if (mFlowHeight == FlowConfig.INVALID_VAL) {
                            mFlowParamList.add(new float[]{ecount, i, y});
                            h = h + y + mFlowVspace;
                        } else {
                            mFlowParamList.add(new float[]{ecount, i, mFlowHeight});
                            h = h + mFlowHeight + mFlowVspace;
                        }
                    }
                }
                // 下个 child 换行，提交本行 child 的信息
                else {
                    /** 提交本行信息 */
                    // 如果没有设置 child 的高度，则采用测量出的高度 y
                    if (mFlowHeight == FlowConfig.INVALID_VAL) {
                        mFlowParamList.add(new float[]{ecount, i, y});
                        h = h + y + mFlowVspace;
                    } else {
                        mFlowParamList.add(new float[]{ecount, i, mFlowHeight});
                        h = h + mFlowHeight + mFlowVspace;
                    }
                    // 下个item转入下行前，将记录 item 宽度的 X 和记录行高度的 Y 重置
                    x = 0;
                    y = 0;
                    ecount = 0;
                }
            }
            // child 已转入下行
            else {
                /** 提交上一行信息 */
                // 如果没有设置 child 的高度，则采用测量出的高度 y
                if (mFlowHeight == FlowConfig.INVALID_VAL) {
                    mFlowParamList.add(new float[]{ecount, i - 1, y});
                    h = h + y + mFlowVspace;
                } else {
                    mFlowParamList.add(new float[]{ecount, i - 1, mFlowHeight});
                    h = h + mFlowHeight + mFlowVspace;
                }
                // 转入下行后的第一个 child 的宽和高
                x = (int) (cw + mFlowHspace);
                y = ch;
                ecount = 1;
                /** 如果是最后一个 child 需要换行，还需再提交最后一行信息 */
                if (i == (childCount - 1)) {
                    // 如果没有设置 child 的高度，则采用测量出的高度 y
                    if (mFlowHeight == FlowConfig.INVALID_VAL) {
                        mFlowParamList.add(new float[]{ecount, i, y});
                        h = h + ch + mFlowVspace;
                    } else {
                        mFlowParamList.add(new float[]{ecount, i, mFlowHeight});
                        h = h + mFlowHeight + mFlowVspace;
                    }
                }
            }
        }
        // 如果设置了最大显示行数
        if (mFlowMaxRows != FlowConfig.INVALID_VAL && mFlowMaxRows >= 0) {
            // 如果测量的行数已经大于最大显示行数
            if (mFlowParamList.size() > mFlowMaxRows) {
                float tempH = 0;
                // 取最大显示行数的总高度
                for (int i = 0; i < mFlowMaxRows; i++) {
                    tempH += mFlowParamList.get(i)[2] + mFlowVspace;
                }
                h = tempH;
            }
        }
        int height = (int) (h - mFlowVspace + getPaddingTop() + getPaddingBottom());
        setMeasuredDimension(widthSize, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mAdapter == null || getChildCount() == 0) {
            return;
        }
        // child 的横坐标
        float x;
        // child 的纵坐标
        float y = getPaddingTop();
        for (int i = 0; i < mFlowParamList.size(); i++) {
            // 如果最大显示行数的属性设置有效
            if (mFlowMaxRows != FlowConfig.INVALID_VAL && mFlowMaxRows >= 0) {
                // 如果已经到达最大显示行数，则接下来的逻辑不执行
                if (mFlowMaxRows < i + 1) {
                    return;
                }
            }
            float[] param = mFlowParamList.get(i);
            // 每行的 item 个数
            int ecount = (int) param[0];
            // 一行中最后一个 item 的编号
            int maxIndex = (int) param[1];
            // 本行的高度
            float height = param[2];
            // 如果 child 在一行中是横向左对齐
            if (mFlowHorAlign == FlowConfig.FLOW_HOR_LEFT) {
                x = getPaddingLeft();
            } else {
                int tempWidth = 0;
                for (int j = 0; j < ecount; j++) {
                    View child = getChildAt(j + maxIndex + 1 - ecount);
                    int cw = child.getMeasuredWidth();
                    tempWidth += cw;
                }
                tempWidth += (ecount - 1) * mFlowHspace;
                // 如果 child 在一行中是横向居中对齐
                if (mFlowHorAlign == FlowConfig.FLOW_HOR_MIDDLE) {
                    x = (r - l - tempWidth) / 2.f;
                }
                // child 在一行中是横向右对齐
                else {
                    x = r - getPaddingRight() - tempWidth;
                }
            }
            for (int j = 0; j < ecount; j++) {
                View child = getChildAt(j + maxIndex + 1 - ecount);
                int cw = child.getMeasuredWidth();
                int ch = child.getMeasuredHeight();
                // 如果没有设置 child 的高度
                if (mFlowHeight == FlowConfig.INVALID_VAL) {
                    // 默认 child 在一行中是纵向顶部对齐
                    float yh = y;
                    if (ch < height) {
                        // 如果 child 在一行中是纵向居中对齐
                        if (mFlowVertAlign == FlowConfig.FLOW_VERT_MIDDLE) {
                            yh = y + (height - ch) / 2f;
                        }
                        // 如果 child 在一行中是纵向底部对齐
                        else if (mFlowVertAlign == FlowConfig.FLOW_VERT_BOTTOM) {
                            yh = y + height - ch;
                        }
                    }
                    child.layout((int) x, (int) yh, (int) x + cw, (int) (yh + ch));
                } else {
                    child.layout((int) x, (int) y, (int) x + cw, (int) (y + height));
                }
                x += cw + mFlowHspace;
            }
            y += height + mFlowVspace;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setFlowHeight(float flowHeight) {
        this.mFlowHeight = flowHeight;
        requestLayout();
    }

    public float getFlowHeight() {
        return mFlowHeight;
    }

    public void setFlowMaxRows(int flowMaxRows) {
        this.mFlowMaxRows = flowMaxRows;
        requestLayout();
    }

    public int getFlowMaxRows() {
        return mFlowMaxRows;
    }

    public void setFlowHorAlign(int flowHorAlign) {
        this.mFlowHorAlign = flowHorAlign;
        requestLayout();
    }

    public int getFlowHorAlign() {
        return mFlowHorAlign;
    }

    /**
     * 注：当设置了 flow_height 后，此属性无效
     *
     * @param flowVertAlign
     */
    public void setFlowVertAlign(int flowVertAlign) {
        this.mFlowVertAlign = flowVertAlign;
        requestLayout();
    }

    public int getFlowVertAlign() {
        return mFlowVertAlign;
    }

    public void setFlowHspace(float flowHspace) {
        this.mFlowHspace = flowHspace;
        requestLayout();
    }

    public float getFlowHspace() {
        return mFlowHspace;
    }

    public void setFlowVspace(float flowVspace) {
        this.mFlowVspace = flowVspace;
        requestLayout();
    }

    public float getFlowVspace() {
        return mFlowVspace;
    }
}
