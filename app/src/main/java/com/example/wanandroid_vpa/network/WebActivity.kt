package com.example.wanandroid_vpa.network

import android.graphics.Bitmap
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.wanandroid_vpa.R
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_webview_x5.*
import kotlinx.android.synthetic.main.view_model_title.*

/**
 * Created by geegumb on 2020/12/3
 *
 */
class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_x5)

        val webLink = intent.getStringExtra(FLAG_WEB_LINK)
        val webTitle = intent.getStringExtra(FLAG_WEB_TITLE)
        tvTitle.text = webTitle
        webView.loadUrl(webLink)
        setUpWebView()
        iv_back.setOnClickListener { onBackPressed() }
    }

    private fun setUpWebView() {
        WebView.setWebContentsDebuggingEnabled(true)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.setAppCacheMaxSize((1024 * 1024 * 4).toLong())
        val appCachePath = applicationContext.cacheDir.absolutePath
        settings.setAppCachePath(appCachePath)
        settings.allowFileAccess = true
        settings.setAppCacheEnabled(true)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(webview: WebView?, url: String?): Boolean {
                return url?.contains(FILTER_URL_JIAN_SHU) ?: false // 阻止恶意跳转
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (webView.parent as ViewGroup).removeView(webView)
        webView.stopLoading()
        webView.loadUrl(null)
        webView.clearHistory()
        webView.removeAllViews()
        webView.pauseTimers()
        webView.clearCache(true)
        webView.settings.javaScriptEnabled = false
        webView.tag = null
        webView.destroy()
    }

    companion object {
        const val FLAG_WEB_LINK = "flag_web_link"
        const val FLAG_WEB_TITLE = "flag_web_title"
        private const val FILTER_URL_JIAN_SHU = "jianshu://notes/"
    }
}