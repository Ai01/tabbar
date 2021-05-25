package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class TabIconView(context: Context, width: Int, title:String, activeIconUrl:String, unActiveIconUrl: String, isActive: Boolean, id: Int): LinearLayout(context) {

    var isActive:Boolean = isActive // active or not
    var title = title
    var activeIconUrl = activeIconUrl
    var unActiveIconUrl = unActiveIconUrl
    var cwidth = width
    var imageId: Int = id

    init {
        this.createTabIcon()
    }

    fun changeActiveStateById(state:Boolean, id: Int) {
        this.changeImage(state, id)
    }

    fun changeImage(isActive: Boolean, id: Int) {
        val imageView = this.findViewById<ImageView>(id)
        if(imageView == null) return

        if(isActive) {
            DownloadImageTask(imageView).execute(activeIconUrl)
        } else {
            DownloadImageTask(imageView).execute(unActiveIconUrl)
        }
    }

    fun createTabIcon(): View {
        // 设置垂直排列
        this.orientation= VERTICAL

        // 图片
        val imageView = ImageView(context)
        if(isActive) {
            DownloadImageTask(imageView).execute(activeIconUrl)
        } else {
            DownloadImageTask(imageView).execute(unActiveIconUrl)
        }
        imageView.layoutParams?.width = cwidth
        imageView.layoutParams?.height = 100
        imageView.setBackgroundColor(Color.BLACK)
        imageView.id = imageId
        Log.d("test imageId", imageId.toString())
        this.addView(imageView)


        // 字符
        val textView = TextView(context)
        textView.text = title
        textView.height = 50
        textView.setTextColor(Color.WHITE)
        textView.setBackgroundColor(Color.BLACK)
        textView.gravity= Gravity.CENTER
        textView.width = cwidth
        this.addView(textView)

        return this
    }

}