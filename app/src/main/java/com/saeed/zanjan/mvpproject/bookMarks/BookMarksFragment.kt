package com.saeed.zanjan.mvpproject.bookMarks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseFragment
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.data.Repository
import com.saeed.zanjan.mvpproject.home.HomeContractor
import com.saeed.zanjan.mvpproject.home.HomePresenter
import com.saeed.zanjan.mvpproject.home.NewsAdapter
import com.saeed.zanjan.mvpproject.newsDetile.NewsDetiles

class BookMarksFragment:BaseFragment(),BookMarksContractor.View {
    var recyclerBookMarks:RecyclerView?=null
    var emptyView: View?=null
    var presenter:BookMarksContractor.Presenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter=BookMarksPresenter(Repository(context()))
        activity?.title="علاقه مندی ها"
    }

    override fun onStart() {
        super.onStart()
        presenter!!.onAttachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter!!.onDetachView()
    }
    override fun getLayoutRes(): Int {
        return R.layout.bookmarks_fragment
    }

    override fun getLayoutSetUps() {
        recyclerBookMarks=rootView!!.findViewById(R.id.bookMarkRecycler)
        emptyView=rootView!!.findViewById(R.id.emptyFrame)
    }

    override fun showBookMArks(bookMarks: List<News>) {
        var bookMarksAdapter=BookmarkedNewsAdapter(bookMarks)
        recyclerBookMarks!!.layoutManager=LinearLayoutManager(context)
        recyclerBookMarks!!.adapter=bookMarksAdapter
        bookMarksAdapter.setonClickItem(object:BookmarkedNewsAdapter.OnClickItem{
            override fun onItemClickListener(news: News) {
                var intent: Intent = Intent(context, NewsDetiles::class.java)
                intent.putExtra("information",news)
                startActivity(intent)
            }


        })


    }

    override fun showEmptyLayout(isEmpty: Boolean) {
        if(isEmpty){
            emptyView!!.visibility=View.VISIBLE

        }else{
            emptyView!!.visibility=View.GONE

        }

    }


    override fun showProgressBar(showOrNot: Boolean) {
    }

    override fun context(): Context {
        return requireContext()
    }

    override fun showError(text: String) {
    }
}