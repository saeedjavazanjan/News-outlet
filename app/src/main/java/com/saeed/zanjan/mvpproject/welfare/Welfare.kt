package com.saeed.zanjan.mvpproject.welfare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseActivity
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.data.Repository
import com.saeed.zanjan.mvpproject.home.HomeContractor
import com.saeed.zanjan.mvpproject.home.HomePresenter
import com.saeed.zanjan.mvpproject.home.NewsAdapter
import com.saeed.zanjan.mvpproject.home.WelfarePresenter
import com.saeed.zanjan.mvpproject.newsDetile.NewsDetiles

class Welfare : BaseActivity(),WelfareContractor.View{
    var newsRecycler: RecyclerView?=null
    var imgBack:ImageView?=null
    var presenter:WelfareContractor.Presenter?=null
    override fun getLayoutSetUp() {
        newsRecycler=findViewById(R.id.newsRecycler)
        imgBack=findViewById(R.id.imgBack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welfare)
        presenter=WelfarePresenter(Repository(this))
        getLayoutSetUp()

        imgBack!!.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)

    }

    override fun onStop() {
        super.onStop()
        presenter?.onDetachView()

    }
    override fun showWelfareNews(showWelfareNews: List<News>) {
        var newsAdapter: WelfareNewsAdapter = WelfareNewsAdapter(showWelfareNews)
        newsRecycler!!.layoutManager= LinearLayoutManager(this)
        newsRecycler!!.adapter=newsAdapter

        newsAdapter.setonClickItem(object: WelfareNewsAdapter.OnClickItem{
            override fun onItemClickListener(news: News) {
                var intent: Intent = Intent(this@Welfare, NewsDetiles::class.java)
                intent.putExtra("information",news)
                startActivity(intent)
            }


        })
        newsAdapter.setonBookMarkItem(object : WelfareNewsAdapter.OnBookMarkItem{
            override fun onBookMark(news: News) {


            }

        })
    }

    override fun showProgressBar(showOrNot: Boolean) {
        var  progressBar: LinearLayout = findViewById(R.id.linProgress)

        if (showOrNot)
            progressBar!!.visibility = View.VISIBLE
        else
            progressBar!!.visibility = View.GONE    }

    override fun context(): Context {
return  this
    }

    override fun showError(text: String) {
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
    }
}