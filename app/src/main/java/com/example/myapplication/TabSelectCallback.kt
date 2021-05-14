package com.example.myapplication

import android.view.View
import org.json.JSONObject

// done 接口
interface TabSelectCallback {
    fun onSelect(index: Int, data: JSONObject)
}