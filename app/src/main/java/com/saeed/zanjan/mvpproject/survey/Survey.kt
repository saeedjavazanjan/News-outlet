package com.saeed.zanjan.mvpproject.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import com.saeed.zanjan.mvpproject.R

class Survey : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        var webView:WebView=findViewById(R.id.webSurvey)
        var imgBack:ImageView=findViewById(R.id.imgBack)
        imgBack.setOnClickListener {
            finish()
        }
        webView.loadUrl("https://survey.porsline.ir/s/riS5YNv")

        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}