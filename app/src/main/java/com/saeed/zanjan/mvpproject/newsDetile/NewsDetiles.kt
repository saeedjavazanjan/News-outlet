package com.saeed.zanjan.mvpproject.newsDetile

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseActivity
import com.saeed.zanjan.mvpproject.data.DataSource
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.data.Repository
import com.saeed.zanjan.mvpproject.utils.CustomImageView
import com.saeed.zanjan.mvpproject.utils.FullScreenImageView
import com.squareup.picasso.Picasso
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.HtmlTextView
import org.sufficientlysecure.htmltextview.OnClickATagListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.app.ActivityOptions
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.saeed.zanjan.mvpproject.data.Announce


class NewsDetiles : BaseActivity() {
    var imageView:CustomImageView?=null
    var bookmark:ImageView?=null
    var datas:DataSource?=null

    var news:News= News()
    var dataSource:DataSource?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detiles)
        datas=Repository(this)

        dataSource=Repository(this)
        var intent:Intent=getIntent()
        news= intent.getSerializableExtra("information") as News
        getLayoutSetUp()
    }

    override fun getLayoutSetUp() {
        bookmark=findViewById(R.id.image_bookmark)
        bookmark!!.setOnClickListener{

            //   news.isBookMarked!=(news.isBookMarked)

            // dataSource!!.bookMark(news)


            if (datas!!.getCurrentSavedPackage(news.id)){
                news.isBookMarked=false
                bookmark!!.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                dataSource!!.deleteBookMark(news)
            }else{
                news.isBookMarked=true
                bookmark!!.setImageResource(R.drawable.ic_baseline_bookmark_added_24)
                dataSource!!.bookMark(news)
            }
        }
        if (datas!!.getCurrentSavedPackage(news.id)){
            bookmark!!.setImageResource(R.drawable.ic_baseline_bookmark_added_24)
        }else{
            bookmark!!.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
        }
        var text:TextView=findViewById(R.id.txt_content_news)
        var data:TextView=findViewById(R.id.txt_date_news)
        data.text=news.date
        text.setText(news.description!!)
      /*  text.setHtml(news.description!!,HtmlHttpImageGetter(text,"http://novindevelopers.ir/mvp/",true))

        text.setOnClickATagListener(object:OnClickATagListener{
            override fun onClick(widget: View?, spannedText: String?, href: String?): Boolean {
                Toast.makeText(this@NewsDetiles,"link",Toast.LENGTH_SHORT).show()
                return true
            }

        })*/
        var videoFrameLayout:FrameLayout=findViewById(R.id.fram_container_video)
        var videoPlayer:JzvdStd=findViewById(R.id.jz)
        imageView=findViewById(R.id.image_news_details)

        imageView!!.setOnClickListener {

           var  intent:Intent= Intent(this,FullScreenImageView::class.java)
            intent.putExtra("imageUrl",news.image)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        var toolbar:androidx.appcompat.widget.Toolbar=findViewById(R.id.toolbar_news_details)
        var collapsingLayout:CollapsingToolbarLayout=findViewById(R.id.collapsing_toolbar)
        if (news.video==""){
            videoFrameLayout.visibility= View.GONE
            imageView!!.visibility= View.VISIBLE
            Picasso.get()
                .load(news.image)
                .error(R.drawable.ic_baseline_image_search_24)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(imageView)
        }else{
            videoFrameLayout.visibility= View.VISIBLE
            imageView!!.visibility= View.GONE

            videoPlayer.setUp(news.video,"")
            videoPlayer.setScreen(JzvdStd.SCREEN_NORMAL)
            Picasso.get()
                .load(news.image)
                .error(R.drawable.ic_baseline_image_search_24)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(videoPlayer.posterImageView)
            videoPlayer.fullscreenButton.visibility=View.GONE
            videoPlayer.backButton.visibility=View.GONE
            videoPlayer.batteryLevel.visibility=View.GONE
            videoPlayer.tinyBackImageView.visibility=View.GONE
            videoPlayer.currentTimeTextView.visibility=View.GONE
        }



        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        toolbar.title=(news.title)
        collapsingLayout.setCollapsedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.transparent)!!)
        collapsingLayout.setCollapsedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.white)!!)
        collapsingLayout.setExpandedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.transparent)!!)
        //collapsingLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);        val typeface = ResourcesCompat.getFont(this, R.font.iransans)
        collapsingLayout.setCollapsedTitleTypeface(typeface)
      //  collapsingLayout.setExpandedTitleTypeface(typeface)
        collapsingLayout.title=news.title





    }




    override fun onResume() {
        super.onResume()
        Jzvd.goOnPlayOnResume();

    }

    override fun onPause() {
        super.onPause()
        Jzvd.goOnPlayOnPause();

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    override fun onDestroy() {
        super.onDestroy()
        Jzvd.releaseAllVideos();

    }


}