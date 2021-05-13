package com.example.myapplication

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

// 管理webviewFragment的实例。
// 用hashMap来管理webviewFragment
// 需要传入activity, configData
// 提供创建webviewFragment的方法，切换webviewFragment的方法

class WebviewManager(activity: AppCompatActivity, configData: JSONArray) {
    var webviewFragments: HashMap<Int, WebViewFragment> = HashMap()
    var configData: JSONArray = configData
    var activity: AppCompatActivity = activity
    var lastFragment: WebViewFragment?=null

    fun changeActiveWebviewFragment(index: Int){
        val fragmentManager = activity.supportFragmentManager

        Log.println(Log.WARN, "webviewManager is not been craeted", (webviewFragments[index] == null).toString());
        if (webviewFragments[index] == null) {
            var path = configData.optJSONObject(index).get("path").toString();
            var newFragment = WebViewFragment(path)
            webviewFragments[index] = newFragment

            val transaction = fragmentManager.beginTransaction()
            if(lastFragment != null) {
                transaction.hide(lastFragment!!)
            }
            lastFragment = newFragment;
            transaction.add(R.id.webviewPart, newFragment, path)
            transaction.commitNow()

            Log.println(Log.WARN, "webviewManager is hidden $index", newFragment.isHidden.toString());
            Log.println(Log.WARN, "webviewManager is added $index", newFragment.isAdded.toString());
            Log.println(Log.WARN, "webviewManager is url $index", newFragment.pageUrl.toString());
            Log.println(Log.WARN, "webviewManager is visible $index", newFragment.isVisible.toString());
        } else {
            val transaction = fragmentManager.beginTransaction()
            if(lastFragment != null) {
                transaction.hide(lastFragment!!)
            }
            var nextFragment = webviewFragments[index]!!;
            transaction.show(nextFragment)
            lastFragment = nextFragment;
            transaction.commitNow()

            Log.println(Log.WARN, "webviewManager is hidden $index", nextFragment.isHidden.toString());
            Log.println(Log.WARN, "webviewManager is added $index", nextFragment.isAdded.toString());
            Log.println(Log.WARN, "webviewManager is url $index", nextFragment.pageUrl.toString());
        }

    }

}