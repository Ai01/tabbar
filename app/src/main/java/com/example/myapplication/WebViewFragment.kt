package com.example.myapplication

// webview的销毁管理应该在这里做

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class WebViewFragment(url: String): Fragment() {

    var myWebView: WebView?=null
    var pageUrl: String=url

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myWebView = WebView(container?.context!!)
        // myWebView.evaluateJavascript("js",)
        myWebView!!.webChromeClient =object :WebChromeClient(){}
        myWebView!!.webViewClient =object :WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        myWebView!!.loadUrl(pageUrl)
        myWebView!!.settings.javaScriptEnabled = true
        return myWebView
    }

    override fun onResume() {
        super.onResume()
        if(myWebView != null) {
            myWebView?.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (myWebView!=null) {
            myWebView?.onPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (myWebView!=null) {
            myWebView?.destroy()
        }
    }

}