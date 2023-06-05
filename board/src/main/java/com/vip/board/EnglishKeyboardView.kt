package com.vip.board

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

/**
 *AUTHOR:AbnerMing
 *DATE:2023/3/16
 *INTRODUCE:英文键盘
 */
class EnglishKeyboardView : LinearLayout {

    //键盘内容
    private val mEnglishList = arrayListOf(
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
        "Q", "W", "E", "R", "T", "Y", "U", "O", "P",
        "A", "S", "D", "F", "G", "H", "J", "K", "L",
        "Z", "X", "C", "V", "B", "N", "M"
    )

    private var mLength = 10//长度

    private var mSpacing = 10f//间隔

    private var mMarginLeftRight = 20f//左右边距

    private var mMarginTop = 0f//上边距离

    private var mMarginBottom = 60f//下边距离

    private var mRectHeight = 88f//每个格子高度

    private var mRectMarginTop = 16f//距离上边的高度

    private var mBackGroundColor = Color.GRAY//背景颜色

    private var mRectBackGround = R.drawable.view_shape_soloid_ffffff_radius_2//格子背景,传递的资源id

    private var mRectSelectBackGround = R.drawable.view_shape_stroke_8548d2_radius_2//格子选中背景

    private var mIsShowComplete = true//是否显示完成按钮

    private var mCompleteTextColor = Color.parseColor("#087EFD")//完成按钮文字颜色

    private var mCompleteTextSize = 32f//完成文字大小

    private var mCompleteText = "完成"//完成文字内容

    private var mCompleteMarginTop = 20f//完成距离上边

    private var mCompleteMarginBottom = 20f//完成距离上边

    private var mCompleteMarginRight = 20f//完成距离右边

    private var mRectTextSize = 32f//格子的文字大小

    private var mRectTextColor = ContextCompat.getColor(context, R.color.text_333333)//格子文字的默认颜色

    private var mRectSelectTextColor =
        ContextCompat.getColor(context, R.color.text_8548D2)//格子文字的选中颜色

    private var mNumProhibitColor = ContextCompat.getColor(context, R.color.text_999999)//禁止文字颜色

    private var mOtherLinesMargin = 60f//除了第一行其他行的边距

    private var mNumProhibit = false//默认禁止

    private var mTempTextViewList = ArrayList<TextView>()//存储的临时View

    private var mTextClickEffect = false//是否触发点击效果

    constructor(
        context: Context
    ) : super(context) {
        initData(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        context.obtainStyledAttributes(attrs, R.styleable.EnglishKeyboardView)
            .apply {
                //整体的背景颜色
                mBackGroundColor =
                    getColor(
                        R.styleable.EnglishKeyboardView_ek_background,
                        Color.parseColor("#f5f5f5")
                    )
                //每个格子的默认背景
                mRectBackGround =
                    getResourceId(
                        R.styleable.EnglishKeyboardView_ek_rect_background,
                        mRectBackGround
                    )

                //每个格子的选中背景
                mRectSelectBackGround =
                    getResourceId(
                        R.styleable.EnglishKeyboardView_ek_rect_select_background,
                        mRectSelectBackGround
                    )
                //格子的文字大小
                mRectTextSize =
                    getDimension(R.styleable.EnglishKeyboardView_ek_rect_text_size, mRectTextSize)
                //格子的文字颜色
                mRectTextColor =
                    getColor(R.styleable.EnglishKeyboardView_ek_rect_text_color, mRectTextColor)
                //格子的选中文字颜色
                mRectSelectTextColor =
                    getColor(
                        R.styleable.EnglishKeyboardView_ek_rect_select_text_color,
                        mRectSelectTextColor
                    )
                //每个格子的边距
                mSpacing = getDimension(R.styleable.EnglishKeyboardView_ek_rect_spacing, mSpacing)
                //每个格子的高度
                mRectHeight =
                    getDimension(R.styleable.EnglishKeyboardView_ek_rect_height, mRectHeight)
                //格子距离上边的距离
                mRectMarginTop =
                    getDimension(R.styleable.EnglishKeyboardView_ek_rect_margin_top, mRectMarginTop)
                //视图距离左右的距离
                mMarginLeftRight =
                    getDimension(
                        R.styleable.EnglishKeyboardView_ek_margin_left_right,
                        mMarginLeftRight
                    )
                //视图距离上边的距离
                mMarginTop =
                    getDimension(R.styleable.EnglishKeyboardView_ek_margin_top, mMarginTop)
                //视图距离下边的距离
                mMarginBottom =
                    getDimension(R.styleable.EnglishKeyboardView_ek_margin_bottom, mMarginBottom)
                //视图距离左右的距离
                mMarginLeftRight =
                    getDimension(
                        R.styleable.EnglishKeyboardView_ek_margin_left_right,
                        mMarginLeftRight
                    )
                //是否显示完成按钮
                mIsShowComplete =
                    getBoolean(R.styleable.EnglishKeyboardView_ek_is_show_complete, true)
                //完成按钮文字颜色
                mCompleteTextColor =
                    getColor(
                        R.styleable.EnglishKeyboardView_ek_complete_text_color,
                        mCompleteTextColor
                    )
                //完成按钮文字大小
                mCompleteTextSize =
                    getDimension(
                        R.styleable.EnglishKeyboardView_ek_complete_text_size,
                        mCompleteTextSize
                    )
                //完成按钮文字内容
                getString(R.styleable.EnglishKeyboardView_ek_complete_text)?.let {
                    mCompleteText = it
                }
                //完成按钮距离上边
                mCompleteMarginTop =
                    getDimension(
                        R.styleable.EnglishKeyboardView_ek_complete_margin_top,
                        mCompleteMarginTop
                    )
                //完成按钮距离下边
                mCompleteMarginBottom =
                    getDimension(
                        R.styleable.EnglishKeyboardView_ek_complete_margin_bottom,
                        mCompleteMarginBottom
                    )
                //完成按钮距离上边
                mCompleteMarginRight =
                    getDimension(
                        R.styleable.EnglishKeyboardView_ek_complete_margin_right,
                        mCompleteMarginRight
                    )
                //其他行的边距
                mOtherLinesMargin =
                    getDimension(
                        R.styleable.EnglishKeyboardView_ek_other_lines_margin,
                        mOtherLinesMargin
                    )
                //数字是否禁止
                mNumProhibit =
                    getBoolean(
                        R.styleable.EnglishKeyboardView_ek_is_num_prohibit,
                        false
                    )
                //数字是否禁止时的颜色
                mNumProhibitColor =
                    getColor(
                        R.styleable.EnglishKeyboardView_ek_text_prohibit_color,
                        mNumProhibitColor
                    )
                //是否触发点击效果
                mTextClickEffect =
                    getBoolean(
                        R.styleable.EnglishKeyboardView_ek_text_click_effect,
                        mTextClickEffect
                    )
            }

        initData(context)
    }


    //每行对应的车牌号的View视图
    var mLineLayout: LinearLayout? = null

    private fun initData(context: Context) {
        //设置背景颜色
        setBackgroundColor(mBackGroundColor)
        orientation = VERTICAL//设置纵向

        //设置距离底部
        setPadding(0, mMarginTop.toInt(), 0, mMarginBottom.toInt())

        if (mIsShowComplete) {
            //添加完成的View视图
            val textView = TextView(context)
            textView.apply {
                setOnClickListener {
                    //点击了完成
                    mKeyboardComplete?.invoke()
                }
                gravity = Gravity.RIGHT
                text = mCompleteText
                setTextColor(mCompleteTextColor)
                textSize = px2sp(mCompleteTextSize)
            }

            addView(textView)
            val submitParams = textView.layoutParams as LayoutParams
            submitParams.apply {
                width = LayoutParams.MATCH_PARENT
                topMargin = mCompleteMarginTop.toInt()
                bottomMargin = (mCompleteMarginBottom - mRectMarginTop).toInt()
                rightMargin = mCompleteMarginRight.toInt()
                textView.layoutParams = this
            }

        }

        //遍历数字
        eachData(mEnglishList.subList(0, 10), mLength, true)
        //遍历字母
        eachData(mEnglishList.subList(10, mEnglishList.size), mLength - 1, false)
        //追加最后一个删除按钮View,动态计算宽度
        addEndView(mLineLayout)

    }


    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:遍历数据
     */
    private fun eachData(
        list: List<String>,
        len: Int,
        isNumber: Boolean = false
    ) {
        list.forEachIndexed { index, s ->
            if (index % len == 0) {
                //重新创建，并添加View
                mLineLayout = createLinearLayout()
                mLineLayout?.weightSum = len.toFloat()
                addView(mLineLayout)
                val params = mLineLayout?.layoutParams as LayoutParams
                params.apply {
                    topMargin = mRectMarginTop.toInt()
                    height = mRectHeight.toInt()
                    if (isNumber) {
                        //是数字
                        leftMargin = mMarginLeftRight.toInt()
                        rightMargin = mMarginLeftRight.toInt() - mSpacing.toInt()
                    } else {
                        //是字母
                        leftMargin = mOtherLinesMargin.toInt()
                        rightMargin = mOtherLinesMargin.toInt() - mSpacing.toInt()
                    }
                    mLineLayout?.layoutParams = this
                }
            }

            //创建文字视图
            val textView = TextView(context).apply {
                text = s
                //设置文字的属性
                textSize = px2sp(mRectTextSize)
                //禁止
                if (isNumber) {
                    //是数字
                    if (mNumProhibit) {
                        setTextColor(mRectTextColor)
                    } else {
                        setTextColor(mNumProhibitColor)
                    }
                } else {
                    setTextColor(mRectTextColor)
                }
                setBackgroundResource(mRectBackGround)
                gravity = Gravity.CENTER
                setOnClickListener {
                    //每个格子的点击事件
                    if (isNumber && !mNumProhibit) {
                        //如果是数字，根据规则暂时不触发点击
                        return@setOnClickListener
                    }
                    changeTextViewState(this)
                }
            }
            //是数字
            if (isNumber) {
                mTempTextViewList.add(textView)
            }
            addRectView(textView, mLineLayout, 1f)
        }
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:追加最后一个View
     */
    private fun addEndView(layout: LinearLayout?) {
        val endViewLayout = LinearLayout(context)
        endViewLayout.gravity = Gravity.RIGHT
        //删除按钮
        val endView = RelativeLayout(context)
        //添加删除按钮
        val deleteImage = ImageView(context)
        deleteImage.setImageResource(R.drawable.view_ic_key_delete)
        endView.addView(deleteImage)

        val imageParams = deleteImage.layoutParams as RelativeLayout.LayoutParams
        imageParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        deleteImage.layoutParams = imageParams

        endView.setOnClickListener {
            //删除
            mKeyboardDelete?.invoke()
        }
        endView.setBackgroundResource(mRectBackGround)
        endViewLayout.addView(endView)
        val params = endView.layoutParams as LayoutParams
        params.width = LayoutParams.MATCH_PARENT
        params.height = LayoutParams.MATCH_PARENT
        endView.layoutParams = params

        layout?.addView(endViewLayout)
        val endParams = endViewLayout.layoutParams as LayoutParams
        endParams.width = mSpacing.toInt()
        endParams.height = LayoutParams.MATCH_PARENT
        endParams.weight = 2f
        endParams.rightMargin = mSpacing.toInt()
        endViewLayout.layoutParams = endParams

    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:追加视图
     */
    private fun addRectView(view: View, layout: LinearLayout?, w: Float) {
        layout?.addView(view)
        val textParams = view.layoutParams as LayoutParams
        textParams.apply {
            weight = w
            width = 0
            height = LayoutParams.MATCH_PARENT
            //每行的最后一个
            rightMargin = mSpacing.toInt()
            view.layoutParams = this
        }

    }


    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:改变TextView的状态
     */
    private var mOldTextView: TextView? = null
    private fun changeTextViewState(textView: TextView) {
        if (mTextClickEffect) {
            textView.setSelectTextStyle()
            textView.postDelayed({
                textView.setUnSelectTextStyle()
            }, 200)
        } else {
            mOldTextView?.setUnSelectTextStyle()
            textView.setSelectTextStyle()
            mOldTextView = textView
        }
        //每次点击后进行赋值
        mKeyboardContent?.invoke(textView.text.toString())
    }

    private fun TextView.setSelectTextStyle() {
        setBackgroundResource(mRectSelectBackGround)
        setTextColor(mRectSelectTextColor)
    }

    private fun TextView.setUnSelectTextStyle() {
        setBackgroundResource(mRectBackGround)
        setTextColor(mRectTextColor)
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:打开禁止
     */
    fun openProhibit(isOpen: Boolean) {
        //禁止解开
        mNumProhibit = isOpen
        mTempTextViewList.forEach {
            if (isOpen) {
                it.setTextColor(mRectTextColor)
            } else {
                it.setTextColor(mNumProhibitColor)
            }
        }
    }


    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:创建LinearLayout
     */
    private fun createLinearLayout(): LinearLayout {
        val layout = LinearLayout(context)
        layout.orientation = HORIZONTAL
        return layout
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:获取点击的键盘内容
     */
    private var mKeyboardContent: ((content: String) -> Unit?)? = null
    fun keyboardContent(block: (String) -> Unit) {
        mKeyboardContent = block
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:键盘完成
     */
    private var mKeyboardComplete: (() -> Unit?)? = null
    fun keyboardComplete(block: () -> Unit) {
        mKeyboardComplete = block
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:键盘删除
     */
    private var mKeyboardDelete: (() -> Unit?)? = null
    fun keyboardDelete(block: () -> Unit) {
        mKeyboardDelete = block
    }

    private fun px2sp(pxValue: Float): Float {
        val fontScale = resources.displayMetrics.scaledDensity
        return pxValue / fontScale + 0.5f
    }

}