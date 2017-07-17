package com.wuhk.training.kotlinEx.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * 友好时间显示工具类
 * Created by wuhk on 2017/7/6.
 */
object FriendlyTimeUtil {
    private val DAY_INTER = 24 * 60 * 60 * 1000
    private val HOUR_INTER = 60 * 60 * 1000
    private val MINUTE_INTER = 60 * 1000
    private val SECOND_INTER = 1000

    private val dateFormater1 = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("HH:mm")
        }
    }

    private val dateFormater2 = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("yy-MM-dd")
        }
    }

    /**
     * 规则如下，和当前时间之差：<br></br>

     * 0、如果小于1秒钟内，显示刚刚<br></br>
     * 1、如果在1分钟内，显示XXX秒前<br></br>
     * 2、如果在1小时内，显示XXX分钟前<br></br>
     * 3、如果在1小时外的今天内，显示今天15:32<br></br>
     * 4、如果是昨天的，显示昨天15:32<br></br>
     * 5、其余显示，13-10-15<br></br>
     * 6、时间不合法的情况下显示：年-月-日，如25-10-21

     * @param time
     * *
     * @return
     */
    fun friendlyTime(time: Date?): String {
        if (null == time) {
            return "unknown"
        }

        var ftime : String
        val cal = Calendar.getInstance()
        val intervals = cal.timeInMillis - time.time
        if (intervals < 0) {
            return dateFormater2.get().format(time)
        }

        val ltd = time.time / DAY_INTER
        val ctd = cal.timeInMillis / DAY_INTER
        val days = (ctd - ltd).toInt()
        if (days == 0) { // 今天
            val lth = time.time / HOUR_INTER
            val cth = cal.timeInMillis / HOUR_INTER
            val hours = (cth - lth).toInt()
            if (hours == 0) {// 1小时内
                val ltm = time.time / MINUTE_INTER
                val ctm = cal.timeInMillis / MINUTE_INTER
                val minutes = (ctm - ltm).toInt()
                if (minutes == 0) {
                    val lts = time.time / SECOND_INTER
                    val cts = cal.timeInMillis / SECOND_INTER
                    val seconds = (cts - lts).toInt()
                    if (seconds <= 0) {
                        ftime = "刚刚"
                    } else {
                        ftime = seconds.toString() + "秒前"
                    }
                } else {
                    ftime = minutes.toString() + "分钟前"
                }
            } else {
                ftime = "今天  " + dateFormater1.get().format(time)
            }
        } else if (days == 1) {// 昨天
            ftime = "昨天  " + dateFormater1.get().format(time)
        } else {// 其余时间
            ftime = dateFormater2.get().format(time)
        }

        return ftime
    }

}
