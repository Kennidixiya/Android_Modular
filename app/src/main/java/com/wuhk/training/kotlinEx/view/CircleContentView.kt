package com.wuhk.training.kotlinEx.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.wuhk.training.R
import com.wuhk.training.kotlinEx.activity.CircleActivity
import com.wuhk.training.kotlinEx.entity.CircleContentEntity
import com.wuhk.training.kotlinEx.utils.ContextUtil
import com.wuhk.training.kotlinEx.utils.FriendlyTimeUtil
import com.wuhk.training.kotlinEx.utils.GlideImageUtil
import kotlinx.android.synthetic.main.activity_circle.*
import kotlinx.android.synthetic.main.view_circle_content.view.*
import java.util.*


/**
 * 圈子内容View
 * Created by wuhk on 2017/7/6.
 */
class CircleContentView : RelativeLayout {
    var operationPopWindow: PopupWindow? = null
    var commentEditPopWindow : PopupWindow? = null
    var position = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.view_circle_content, this)
    }

    fun bindData(contentEntity: CircleContentEntity , position : Int) {
        this.position = position
        GlideImageUtil.glideImage(context, avatarIv, contentEntity.member!!.pic.toString(), R.mipmap.default_avatar)
        //姓名和文字
        nameTv.text = contentEntity.member!!.name
        contentTv.text = contentEntity.content

        //图片处理
        if (contentEntity.imageUrls != null) {
            if (contentEntity.imageUrls!!.size == 1) {
                singleImage.visibility = View.VISIBLE
                singleImage.imageMode = SingleImageView.IMAGE_MODE_SINGLE
                GlideImageUtil.glideImageViewTarget(context, singleImage, contentEntity.imageUrls!![0], R.mipmap.default_img)
            } else {
                singleImage.visibility = View.GONE
            }
        } else {
            singleImage.visibility = View.GONE
        }

        //链接处理
        if (contentEntity.link != null) {
            linkLayout.visibility = View.VISIBLE
            GlideImageUtil.glideImage(context, linkIv, contentEntity.link!!.linkImage.toString(), R.mipmap.default_url)
            linkTextTv.text = contentEntity.link!!.linkContent
            linkLayout.setOnClickListener { Toast.makeText(context, contentEntity.link!!.linkContent, Toast.LENGTH_SHORT).show() }
        } else {
            linkLayout.visibility = View.GONE
        }

        //时间处理,转为友好时间格式
        timeTv.text = FriendlyTimeUtil.friendlyTime(Date(contentEntity.creationTime!!.toLong()))

        topArrowIv.visibility = View.GONE
        //赞
        if (null != contentEntity.praiseList && contentEntity.praiseList!!.size > 0) {
            topArrowIv.visibility = View.VISIBLE
            praiseLayout.visibility = View.VISIBLE
            var praiseNames: StringBuilder? = StringBuilder()
            for (praise in contentEntity.praiseList!!.toList()) {
                praiseNames!!.append(praise.member!!.name)
                praiseNames.append("、")
            }
            praiseNames!!.deleteCharAt(praiseNames.length - 1)
            praiseNameTv.text = praiseNames.toString()
        } else {
            praiseLayout.visibility = View.GONE
        }

        //评论
        if (null != contentEntity.evaluateList && contentEntity.evaluateList!!.size > 0) {
            topArrowIv.visibility = View.VISIBLE
            commentLayout.visibility = View.VISIBLE

            addCommentView(DefaultCircleCommentAdapter(contentEntity.evaluateList!!.toList()))

        } else {
            commentLayout.visibility = View.GONE
        }

        //操作
        remarkIv.setOnClickListener {
            showOperationPopWindow()
        }
    }


    /**赞和评论PopWindow
     *
     */
    fun showOperationPopWindow() {
        val operationView: View = View.inflate(context, R.layout.view_circle_operation, null)
        if (null == operationPopWindow) {
            operationPopWindow = PopupWindow(operationView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            operationPopWindow!!.setBackgroundDrawable(ColorDrawable(0))
            operationPopWindow!!.isFocusable = true
            operationPopWindow!!.animationStyle = R.style.operationPopAnimal

            val praiseLayout = operationView.findViewById(R.id.praiseLayout) as RelativeLayout
            val commentLayout = operationView.findViewById(R.id.commentLayout) as RelativeLayout
            praiseLayout.setOnClickListener {
                Toast.makeText(context, resources.getString(R.string.praise), Toast.LENGTH_SHORT).show()
                operationPopWindow!!.dismiss()
            }

            commentLayout.setOnClickListener {
                operationPopWindow!!.dismiss()

                showCommentEditPopWindow(contentRootLayout)
            }

        }

        operationPopWindow!!.showAsDropDown(popView, 0, -resources.getDimension(R.dimen.dimen_40).toInt())
        operationPopWindow!!.update()
    }

    fun showCommentEditPopWindow(view : View){
        val  act = context as CircleActivity
        act.recycleView.scrollToPosition(position + 1)

        val commentEditView : View = View.inflate(context , R.layout.view_circle_edit , null)
        val sendEt : EditText =  commentEditView.findViewById(R.id.sendEt) as EditText
        val sendBtn : Button = commentEditView.findViewById(R.id.sendBtn) as Button
        if (null == commentEditPopWindow){
            commentEditPopWindow = PopupWindow(commentEditView , ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT)
            commentEditPopWindow!!.setBackgroundDrawable(ColorDrawable(0))
            commentEditPopWindow!!.isFocusable = true

            commentEditPopWindow!!.setOnDismissListener {
                ContextUtil.showSoftInput(sendEt , false)
                //清空数据
                sendEt.setText("")
            }

            sendBtn.setOnClickListener {
                Toast.makeText(context , sendEt.text.toString() , Toast.LENGTH_SHORT).show()
            }
        }


//        commentEditPopWindow!!.showAsDropDown(view)
        commentEditPopWindow!!.showAtLocation(contentRootLayout , Gravity.BOTTOM , 0 , 0)

        ContextUtil.showSoftInput(sendEt , true)


    }

    /**动态添加评论布局
     *
     */
    fun addCommentView(commentAdapter: CircleCommentAdapter) {
        commentLayout.removeAllViews()
        for (i in 0..commentAdapter.getItemCount() - 1) {
            val view: View = LayoutInflater.from(context).inflate(R.layout.view_circle_commnet, null)
            val commentTv: TextView = view.findViewById(R.id.commentTv) as TextView
            val evaluate: CircleContentEntity.Evaluate = commentAdapter.getItem(i, view)
            var evaluateStr: String
            if (null == evaluate.memberUp) {
                evaluateStr = evaluate.member!!.name + ":" + evaluate.content
            } else {
                evaluateStr = evaluate.member!!.name + "回复" + evaluate.memberUp!!.name + ":" + evaluate.content
            }

            val spannableString = SpannableString(evaluateStr)
            val colorSpan = ForegroundColorSpan(resources.getColor(R.color.colorPrimary))

            spannableString.setSpan(colorSpan, 0, evaluate.member!!.name!!.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            if (null != evaluate.memberUp) {
                val colorSpanforMemberUp = ForegroundColorSpan(resources.getColor(R.color.colorPrimary))
                spannableString.setSpan(colorSpanforMemberUp, evaluate.member!!.name!!.length + 2, evaluate.member!!.name!!.length + 2 + evaluate.memberUp!!.name!!.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            }

            commentTv.text = spannableString

            view.setOnClickListener {
                showCommentEditPopWindow(view)
            }

            commentLayout.addView(view)
        }
    }


    /**默认评价适配器实现
     *
     */
    class DefaultCircleCommentAdapter(val items: List<CircleContentEntity.Evaluate>) : CircleCommentAdapter {
        override fun getItemCount(): Int {
            return items.size
        }

        override fun getItem(position: Int, view: View): CircleContentEntity.Evaluate {
            return items[position]
        }
    }

    /**评论适配器接口
     *
     */
    interface CircleCommentAdapter {
        fun getItemCount(): Int
        fun getItem(position: Int, view: View): CircleContentEntity.Evaluate
    }
}