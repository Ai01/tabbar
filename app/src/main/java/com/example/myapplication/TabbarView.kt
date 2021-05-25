package com.example.myapplication
import android.content.Context
import android.graphics.Color
import android.os.Parcelable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray


class TabbarView(context: Context) : LinearLayout(context) {
    constructor(context: Context, config: JSONArray): this(context){
        createTabBarView(config);
    }

    // TODO: 和闫占坤讨论为什么这里不放到constructor中
    var callbackForSelect: TabSelectCallback?=null
        set

    // 用来保存当前的index
    var activeIndex:Int = 0
    // 用来保存当前active的view
    var activeTabIconView:TabIconView?=null

    fun createTabBarView(config: JSONArray): View {
        val len = config.length() - 1

        for (i in 0..len) {
            val tabConfig = config.optJSONObject(i)
            val tabName: String = tabConfig.get("name").toString()

            val dm = this.resources.displayMetrics;
            val widthForDevice = dm.widthPixels/3;

            var tabIconView = TabIconView(context, widthForDevice, tabName, "https://gw.alicdn.com/tfs/TB1zfstmlBh1e4jSZFhXXcC9VXa-68-68.png", "https://gw.alicdn.com/tfs/TB1QxvpkmR26e4jSZFEXXbwuXXa-68-68.png", i == 0, i)
            if(i == activeIndex) {
                activeTabIconView = tabIconView
            }

            Log.d("test", i.toString())
            tabIconView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    if(activeIndex == i) return;
                    activeTabIconView?.changeActiveStateById(false, activeIndex)
                    tabIconView.changeActiveStateById(true, i)

                    activeIndex=i;
                    activeTabIconView = tabIconView;
                    callbackForSelect?.onSelect(i, tabConfig);
                }
            });

            this.addView(tabIconView);
        }

        return this;
    }
}