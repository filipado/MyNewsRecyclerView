package com.example.mynewsrecyclerview.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.mynewsrecyclerview.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private val webView: WebView? = null

    private lateinit var binding: ActivityWebViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get link to web view from recycler view intent

        val articleUrl = intent.getStringExtra("URL")!!

        val webView = binding.webView
        webView.webViewClient = WebViewClient()
        webView.loadUrl(articleUrl)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

    }

}