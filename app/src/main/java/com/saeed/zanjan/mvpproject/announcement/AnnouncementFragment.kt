package com.saeed.zanjan.mvpproject.announcement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.saeed.zanjan.mvpproject.data.Announce
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.data.Repository
import com.saeed.zanjan.mvpproject.home.NewsAdapter
import com.saeed.zanjan.mvpproject.newsDetile.AnnouceDetiles
import com.saeed.zanjan.mvpproject.newsDetile.NewsDetiles


class AnnouncementFragment:BaseFragment(),AnnouncementContractor.View {
    var categoryRecycler:RecyclerView?=null
    var presenter:AnnouncementContractor.Presenter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter=AnnouncementPresenter(Repository(context()))


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
        return R.layout.category_fragment

    }

    override fun getLayoutSetUps() {
        categoryRecycler=rootView!!.findViewById(R.id.categoryRecycler)
    }

    override fun showAnnounce(showCategory: MutableLiveData<List<Announce>>) {
        var categoryAdapter:AnnouncementAdapter= AnnouncementAdapter(showCategory.value!!)
        categoryRecycler!!.layoutManager=GridLayoutManager(context,1)
        categoryRecycler!!.adapter=categoryAdapter

        showCategory.observe(this, {

            //TODO use notification
            // Toast.makeText(context,"CHANGED",Toast.LENGTH_SHORT).show()
        })

        categoryAdapter.setonClickItem(object: AnnouncementAdapter.OnClickItem{
            override fun onItemClickListener(announce: Announce) {
                var intent:Intent=Intent(context,AnnouceDetiles::class.java)
                intent.putExtra("announce",announce)
                startActivity(intent)            }
        })
    }




    override fun showProgressBar(showOrNot: Boolean) {
        var  progressBar: LinearLayout = rootView!!.findViewById(R.id.linProgress)

        if (showOrNot)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.GONE    }

    override fun context(): Context {
        return requireContext()
    }

    override fun showError(text: String) {
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }
}