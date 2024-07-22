package com.saeed.zanjan.mvpproject.search

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseActivity
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.data.Repository
import com.saeed.zanjan.mvpproject.home.NewsAdapter
import com.saeed.zanjan.mvpproject.newsDetile.NewsDetiles
import org.w3c.dom.Text

class SearchActivity : BaseActivity(),SearchContractor.View {
    var imgClean:ImageView?=null
    var imgBack:ImageView?=null
    var edtSearch:TextInputEditText?=null
    var txtNotFound:TextView?=null
    var emptyImage:ImageView?=null
    var recycler:RecyclerView?=null
    var presenter:SearchContractor.Presenter?=null
    var adapter:NewsAdapter?= NewsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        getLayoutSetUp()
        imgClean!!.visibility= View.GONE
        emptyImage!!.visibility=View.VISIBLE
        presenter=SearchPresenter(Repository(this))
        presenter?.onAttachView(this)

        edtSearch!!.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length>0){
                    presenter!!.getSearchedData(s.toString())
                    imgClean!!.visibility=View.VISIBLE
                    recycler!!.visibility=View.VISIBLE
                    txtNotFound!!.visibility=View.GONE
                    emptyImage!!.visibility=View.GONE
                }else{
                    imgClean!!.visibility=View.GONE
                    recycler!!.visibility=View.GONE
                    txtNotFound!!.visibility=View.GONE
                    emptyImage!!.visibility=View.VISIBLE

                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }


    override fun getLayoutSetUp() {
        imgClean=findViewById(R.id.imgClean)
        imgBack=findViewById(R.id.imgBack)
        edtSearch=findViewById(R.id.edtSearch)
        txtNotFound=findViewById(R.id.txtNotFound)
        recycler=findViewById(R.id.searchRecycler)
        emptyImage=findViewById(R.id.emptyImage)
        imgClean!!.setOnClickListener {
            edtSearch!!.setText("")
        }
        imgBack!!.setOnClickListener {
            finish()
        }
        recycler!!.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        recycler!!.adapter=adapter
        adapter?.setonClickItem(object:NewsAdapter.OnClickItem{
            override fun onItemClickListener(news: News) {
                var intent: Intent = Intent(this@SearchActivity, NewsDetiles::class.java)
                intent.putExtra("information",news)
                startActivity(intent)
            }


        })

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showSearchedData(news: List<News>) {
        if(news.size>0){
            adapter!!.setNewsList(news)
            adapter!!.notifyDataSetChanged()
            recycler!!.visibility=View.VISIBLE
            txtNotFound!!.visibility=View.INVISIBLE
            emptyImage!!.visibility=View.INVISIBLE
        }else{
            recycler!!.visibility=View.INVISIBLE
            txtNotFound!!.visibility=View.VISIBLE
        }



    }




    override fun showProgressBar(showOrNot: Boolean) {
    }

    override fun context(): Context {
        return this
    }

    override fun showError(text: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDetachView()
    }

}