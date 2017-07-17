package com.wuhk.training.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.wuhk.training.R
import com.wuhk.training.kotlinEx.activity.CircleActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_common_title.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWidgets()
    }

    fun initWidgets(){
        titleTv.text = getString(R.string.index_title)
        backIv.visibility = View.GONE

        addButton(getString(R.string.kotlin_circle_train) , CircleActivity:: class.java)
    }

    fun addButton(text: String, cls: Class<*>) {
        val button = Button(this@MainActivity)
        button.text = text
        button.setOnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, cls)
            startActivity(intent)
        }
        contentLayout.addView(button)
    }


}
