package com.saeed.zanjan.mvpproject.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseFragment
import com.saeed.zanjan.mvpproject.data.Banners
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.data.Repository
import com.saeed.zanjan.mvpproject.newsDetile.NewsDetiles
import com.saeed.zanjan.mvpproject.search.SearchActivity
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class HomeFragment : BaseFragment(),HomeContractor.View  {
   // var banner:ViewPager2?=null
    var newsRecycler:RecyclerView?=null
    var sliderView:SliderView?=null
    var presenter:HomeContractor.Presenter?=null
    override fun getLayoutRes(): Int {
        return R.layout.home_fragment
    }
    override fun getLayoutSetUps() {
       // banner=rootView?.findViewById(R.id.banners)
        sliderView=rootView?.findViewById(R.id.imageSlider)
        newsRecycler=rootView?.findViewById(R.id.newsRecycler)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter=HomePresenter(Repository(context()))
       // presenter!!.getNews()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter?.onDetachView()
    }



    override fun showNews(showNewsList: List<News>) {
        var newsAdapter:NewsAdapter= NewsAdapter(showNewsList)
        newsRecycler!!.layoutManager=LinearLayoutManager(context)
        newsRecycler!!.adapter=newsAdapter

        newsAdapter.setonClickItem(object:NewsAdapter.OnClickItem{
            override fun onItemClickListener(news: News) {
                var intent:Intent=Intent(context,NewsDetiles::class.java)
                intent.putExtra("information",news)
                startActivity(intent)
            }


        })
        newsAdapter.setonBookMarkItem(object :NewsAdapter.OnBookMarkItem{
            override fun onBookMark(news: News) {


            }

        })


    }

    override fun showBanners(showBannersList: List<News>) {
      //  var bannerAdapter:BannersAdapter= BannersAdapter(showBannersList)
     //   banner!!.layoutManager=LinearLayoutManager(context)
     //   banner!!.adapter=bannerAdapter
        var sliderAdapter=SliderAdapter(context,showBannersList)
        sliderView!!.setSliderAdapter( sliderAdapter)
       // sliderView!!.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView!!.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView!!.startAutoCycle();

        sliderAdapter.setOnClickSlide {
            var intent:Intent=Intent(context,NewsDetiles::class.java)
            intent.putExtra("information",it)
            startActivity(intent)
        }








    }


    override fun showProgressBar(showOrNot:Boolean) {
       var  progressBar:LinearLayout = rootView!!.findViewById(R.id.linProgress)

        if (showOrNot)
            progressBar!!.visibility = View.VISIBLE
        else
            progressBar!!.visibility = View.GONE

        }

    override fun context(): Context {
        return requireContext()
    }

    override fun showError(text: String) {
Toast.makeText(view?.context,text,Toast.LENGTH_LONG).show()
    }


}