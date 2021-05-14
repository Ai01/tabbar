package com.example.myapplication
import android.content.Context
import android.graphics.Color
import android.os.Parcelable
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
    var activeIndex=0

    fun createTabBarView(config: JSONArray): View {
        val len = config.length() - 1

        for (i in 0..len) {
            val tabConfig = config.optJSONObject(i)
            val tabTextView = TextView(context)
            val tabName: String = tabConfig.get("name").toString()
            tabTextView.setText(tabName)
            tabTextView.setHeight(150)

            val dm = this.resources.displayMetrics;
            val widthForDevice = dm.widthPixels;
            tabTextView.setWidth(widthForDevice/(len+1));

            tabTextView.setTextColor(Color.WHITE)
            tabTextView.setBackgroundColor(Color.BLACK)

            tabTextView.gravity=Gravity.CENTER

            tabTextView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    if(activeIndex == i) return;

                    activeIndex=i;
                    callbackForSelect?.onSelect(i, tabConfig);
                }
            });

            this.addView(tabTextView);
        }

        return this;
    }
}