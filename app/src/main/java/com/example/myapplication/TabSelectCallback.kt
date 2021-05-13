package com.example.myapplication

import android.view.View
import org.json.JSONObject

interface TabSelectCallback {
    fun onSelect(index: Int, data: JSONObject)
}