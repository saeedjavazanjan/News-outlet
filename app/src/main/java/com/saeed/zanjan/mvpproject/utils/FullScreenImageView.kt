package com.saeed.zanjan.mvpproject.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.saeed.zanjan.mvpproject.R
import com.squareup.picasso.Picasso

class FullScreenImageView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image_view)

        var intent: Intent =getIntent()
       var imageUrl= intent.getStringExtra("imageUrl")
        var imageview=findViewById<ImageView>(R.id.fullscreen_imageview)
        var imageBack=findViewById<ImageView>(R.id.imgBack)
        Picasso.get()
            .load(imageUrl)
            .error(R.drawable.ic_baseline_image_search_24)
            .placeholder(R.drawable.ic_baseline_image_search_24)
            .into(imageview)

        imageBack.setOnClickListener {
            finish()
        }




       /* var toolbar:androidx.appcompat.widget.Toolbar=findViewById(R.id.toolbar_fullScreen_Image)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }*/
    }
}