package com.saeed.zanjan.mvpproject.newsDetile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseActivity
import com.saeed.zanjan.mvpproject.data.Announce
import com.saeed.zanjan.mvpproject.data.DataSource
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.data.Repository
import com.saeed.zanjan.mvpproject.utils.CustomImageView
import com.saeed.zanjan.mvpproject.utils.FullScreenImageView
import com.squareup.picasso.Picasso
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener


class AnnouceDetiles : BaseActivity() {
    var imageView: CustomImageView?=null
    var bookmark: ImageView?=null
    var datas: DataSource?=null

    var announce: Announce = Announce()
    var dataSource: DataSource?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annouce_detiles)
        datas= Repository(this)
        dataSource= Repository(this)
        var intent: Intent =getIntent()
        announce= intent.getSerializableExtra("announce") as Announce
        getLayoutSetUp()
    }
    override fun getLayoutSetUp() {
        var text: TextView =findViewById(R.id.txt_content_news)
        text.setText(announce.description!!)

        imageView=findViewById(R.id.image_news_details)

        imageView!!.setOnClickListener {

            var  intent:Intent= Intent(this, FullScreenImageView::class.java)
            intent.putExtra("imageUrl",announce.image)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        var toolbar:androidx.appcompat.widget.Toolbar=findViewById(R.id.toolbar_news_details)
        var collapsingLayout: CollapsingToolbarLayout =findViewById(R.id.collapsing_toolbar)
            imageView!!.visibility= View.VISIBLE
            Picasso.get()
                .load(announce.image)
                .error(R.drawable.ic_baseline_image_search_24)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(imageView)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        toolbar.title=(announce.title)
        collapsingLayout.setCollapsedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.transparent)!!)
        collapsingLayout.setCollapsedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.white)!!)
        collapsingLayout.setExpandedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.transparent)!!)
     //   collapsingLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        val typeface = ResourcesCompat.getFont(this, R.font.iransans)
        collapsingLayout.setCollapsedTitleTypeface(typeface)
       // collapsingLayout.setExpandedTitleTypeface(typeface)
        collapsingLayout.title=announce.title




    }
}