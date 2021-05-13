package com.example.myapplication

// webview的销毁管理应该在这里做

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.webkit.WebViewClient


class WebViewFragment(url: String): Fragment() {

    var myWebView: WebView?=null
    var pageUrl: String=url

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container!!.childCount > 0){
            container?.removeView(myWebView);
            return myWebView;
        }else{
            myWebView = WebView(container?.context);
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
            myWebView!!.settings.javaScriptEnabled = true;
            return myWebView;
        }

    }

}