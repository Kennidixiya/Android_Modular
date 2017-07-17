package com.wuhk.training.kotlinEx.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.wuhk.training.R
import com.wuhk.training.activity.BaseActivity
import com.wuhk.training.kotlinEx.adapter.CircleAdapter
import com.wuhk.training.kotlinEx.entity.BaseEntity
import com.wuhk.training.kotlinEx.entity.CircleContentEntity
import com.wuhk.training.kotlinEx.entity.CircleHeadEntity
import kotlinx.android.synthetic.main.activity_circle.*
import kotlinx.android.synthetic.main.view_common_title.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * 圈子Activity
 * Created by wuhk on 2017/7/4.
 */
class CircleActivity : BaseActivity() {

    var dataList : MutableList<BaseEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle)
        initWidgets()
    }

    fun initWidgets() {
        titleTv.text = getString(R.string.circle_title)
        backIv.setOnClickListener { finish() }
        recycleView.layoutManager = LinearLayoutManager(this)
        val adapter = CircleAdapter(this, dataList)
        recycleView.adapter = adapter

        dataList.addAll(getCircleListData())

        adapter.notifyDataSetChanged()
    }


    /**模拟圈子数据
     *
     */
    fun getCircleListData(): List<BaseEntity> {

        val topEntity: CircleHeadEntity = CircleHeadEntity()
        topEntity.id = "0"
        topEntity.userName = "肯尼迪西亚"
        topEntity.headUrl = "http://img1.imgtn.bdimg.com/it/u=1504663910,3494907753&fm=26&gp=0.jpg"
        topEntity.headBgUrl = "http://scimg.jb51.net/allimg/151113/14-15111310522aG.jpg"


        //用户
        val member1: CircleContentEntity.Member = CircleContentEntity.Member()
        member1.id = "1"
        member1.name = "1号用户"
        member1.pic = "http://img.meimi.cc/touxiang/20170522/iqfqbq1vg3f337.png"
        val member2: CircleContentEntity.Member = CircleContentEntity.Member()
        member2.id = "2"
        member2.name = "2号用户"
        member2.pic = "http://img.meimi.cc/touxiang/20170522/iehs3rpnc3e128.jpg"
        val member3: CircleContentEntity.Member = CircleContentEntity.Member()
        member3.id = "3"
        member3.name = "3号用户"
        member3.pic = "http://imgtu.5011.net/uploads/content/20170525/9249231495683172.jpg"

        //图片
        val imageUrlsOne1: Array<String> = arrayOf("http://img4.duitang.com/uploads/item/201508/26/20150826040429_wRPxr.png")
        val imageUrlsOne2: Array<String> = arrayOf("http://imgsrc.baidu.com/image/c0%3Dshijue%2C0%2C0%2C245%2C40/sign=24271003d81b0ef478e5901db5ad3baf/aa64034f78f0f7362f26142c0055b319eac413c6.jpg")

        //链接
        val link1: CircleContentEntity.Link = CircleContentEntity.Link()
        link1.linkImage = "http://cuimg.zuyushop.com/cuxiaoPic/201412/2014120020045241723.jpg"
        link1.linkContent = "百度一下"
        link1.linkUrl = "https://www.baidu.com/"

        //赞
        val praise1: CircleContentEntity.Praise = CircleContentEntity.Praise()
        praise1.id = "001"
        praise1.member = member2
        praise1.creationTime = Date().time

        val praise2: CircleContentEntity.Praise = CircleContentEntity.Praise()
        praise2.id = "002"
        praise2.member = member1
        praise2.creationTime = Date().time

        var praises: Array<CircleContentEntity.Praise> = arrayOf(praise1, praise2)

        //评论
        val evaluate1: CircleContentEntity.Evaluate = CircleContentEntity.Evaluate()
        evaluate1.id = "0001"
        evaluate1.content = "SB"
        evaluate1.creationTime = Date().time
        evaluate1.member = member2

        val evaluate2: CircleContentEntity.Evaluate = CircleContentEntity.Evaluate()
        evaluate2.id = "0002"
        evaluate2.content = "你才是SB"
        evaluate2.creationTime = Date().time
        evaluate2.member = member1
        evaluate2.memberUp = member2

        val evaluate3: CircleContentEntity.Evaluate = CircleContentEntity.Evaluate()
        evaluate3.id = "0003"
        evaluate3.content = "你个SB"
        evaluate3.creationTime = Date().time
        evaluate3.member = member3

        val evaluate4: CircleContentEntity.Evaluate = CircleContentEntity.Evaluate()
        evaluate4.id = "0004"
        evaluate4.content = "全TM煞笔，哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"
        evaluate4.creationTime = Date().time
        evaluate4.member = member3

        val evaluateList1: Array<CircleContentEntity.Evaluate> = arrayOf(evaluate1, evaluate2, evaluate4)
        val evaluateList2: Array<CircleContentEntity.Evaluate> = arrayOf(evaluate3)

        //动态
        val contentEntity1: CircleContentEntity = CircleContentEntity()
        contentEntity1.id = "01"
        contentEntity1.member = member1
        contentEntity1.content = "1号用户的第一条动态:横向单图"
        contentEntity1.creationTime = Date().time
        contentEntity1.imageUrls = imageUrlsOne1
        contentEntity1.praiseList = praises
        contentEntity1.evaluateList = evaluateList1

        val contentEntity2: CircleContentEntity = CircleContentEntity()
        contentEntity2.id = "02"
        contentEntity2.member = member2
        contentEntity2.content = "2号用户的第一条动态:链接"
        contentEntity2.creationTime = Date().time
        contentEntity2.link = link1
        contentEntity2.evaluateList = evaluateList2

        val contentEntity3: CircleContentEntity = CircleContentEntity()
        contentEntity3.id = "03"
        contentEntity3.member = member3
        contentEntity3.content = "3号用户的第一条动态:竖向单图"
        contentEntity3.creationTime = Date().time
        contentEntity3.imageUrls = imageUrlsOne2

        val contentEntity4: CircleContentEntity = CircleContentEntity()
        contentEntity4.id = "04"
        contentEntity4.member = member1
        contentEntity4.content = "1号用户的第二条动态：只有文字，很多很多很多很多很多很多很多很多很多很多很多很多很多很多很多很多很多"
        contentEntity4.creationTime = Date().time

        var resultList: Array<BaseEntity> = arrayOf(topEntity, contentEntity1, contentEntity2, contentEntity3, contentEntity4)

        return resultList.toList()
    }
}