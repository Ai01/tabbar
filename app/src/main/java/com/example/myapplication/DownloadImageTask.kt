package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.lang.Exception

// 根据地址来展示图片
class DownloadImageTask(var imageView: ImageView): AsyncTask<String, Void, Bitmap?>() {

    override fun doInBackground(vararg urls: String): Bitmap? {
        val imageURL = urls[0]
        var image:Bitmap ?= null
        try {
            val `in` = java.net.URL(imageURL).openStream()
            image = BitmapFactory.decodeStream(`in`)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return image;
    }

    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }
}