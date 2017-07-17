package com.wuhk.training.kotlinEx.entity

/**
 * 圈子内容数据
 * Created by wuhk on 2017/7/6.
 */
class CircleContentEntity : BaseEntity() {
    var id: String? = null //动态Id
    var content: String? = null//动态文字
    var creationTime: Long? = null //发布时间
    var member: Member? = null//发布人
    var imageUrls: Array<String>? = null//图片数组
    var link: Link? = null//连接
    var praiseList: Array<Praise>? = null//赞的人
    var evaluateList: Array<Evaluate>? = null//评论


    /**用户
     *
     */
    class Member {
        var id: String? = null//用户Id
        var name: String? = null//用户姓名
        var pic: String? = null//用户头像
    }

    /**链接
     *
     */
    class Link {
        var linkImage: String? = null//连接图片
        var linkContent: String? = null//连接内容
        var linkUrl: String? = null//连接网址
    }

    /**赞
     *
     */
    class Praise {
        var id: String? = null//赞Id
        var member: Member? = null//赞的人
        var creationTime: Long? = null//赞的时间
    }

    /**评论
     *
     */
    class Evaluate {
        var id: String? = null//评论Id
        var content: String? = null//评论内容
        var creationTime: Long? = null//评论时间
        var member: Member? = null//评论的人
        var memberUp: Member? = null//被评论的评论人
    }

}