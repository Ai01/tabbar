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

class MainActivity : AppCompatActivity() {
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
    var webviewFragmentManager: WebviewManager = WebviewManager(this, configForTabbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var tabBarView = TabbarView(this, configForTabbar);
        tabBarView.callbackForSelect = object: TabSelectCallback {
            override fun onSelect(index: Int, data: JSONObject) {
                webviewFragmentManager.changeActiveWebviewFragment(index)
            }
        }

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
