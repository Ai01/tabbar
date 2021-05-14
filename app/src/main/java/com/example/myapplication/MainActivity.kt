package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject


// done: kotlin的构造函数学习
class MainActivity : AppCompatActivity() {

    // TODO: js bridge实现，来替换获取config
    // 获取mock的tab bar配置
    fun getMockConfig():JSONArray {
        var jsonConfigForTabbar = JSONArray();

        var jsonConfigForTab1 = JSONObject();
        jsonConfigForTab1.put("path","https://www.baidu.com");
        jsonConfigForTab1.put("name","百度");
        jsonConfigForTabbar.put(jsonConfigForTab1);

        var jsonConfigForTab2 = JSONObject();
        jsonConfigForTab2.put("path","https://www.sina.com.cn");
        jsonConfigForTab2.put("name","新浪");
        jsonConfigForTabbar.put(jsonConfigForTab2);

        var jsonConfigForTab3 = JSONObject();
        jsonConfigForTab3.put("path","https://www.taobao.com");
        jsonConfigForTab3.put("name","淘宝");
        jsonConfigForTabbar.put(jsonConfigForTab3);

        return jsonConfigForTabbar;
    }

    var configForTabbar: JSONArray = getMockConfig();

    // done webView manager中的面向对象思想,为什么这么设计,好处在哪里
    var webviewFragmentManager: WebviewManager = WebviewManager(this, configForTabbar)

    // TODO activity的生命周期，activity作为context如何理解
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO android中自定义视图的实现总结
        var tabBarView = TabbarView(this, configForTabbar)

        // TODO 学习匿名对象
        // TODO 先定义一个接口，然后实现这个接口。泛型
        tabBarView.callbackForSelect = object: TabSelectCallback {
            override fun onSelect(index: Int, data: JSONObject) {
                webviewFragmentManager.changeActiveWebviewFragment(index)
            }
        }

        // TODO R在android中的意义
        // TODO android的ui编程的基本思想框架
        setContentView(R.layout.activity_main)

        var mainView = findViewById<FrameLayout>(R.id.fl_rootview)
        var parms = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,150)
        parms.gravity=Gravity.BOTTOM

        mainView.addView(tabBarView, parms)
        webviewFragmentManager.changeActiveWebviewFragment(0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
