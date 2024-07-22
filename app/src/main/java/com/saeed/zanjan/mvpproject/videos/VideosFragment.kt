package com.saeed.zanjan.mvpproject.videos

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.Jzvd
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseFragment
import com.saeed.zanjan.mvpproject.data.Repository
import com.saeed.zanjan.mvpproject.data.Videos

class VideosFragment:BaseFragment(),VideosContractor.View {
    var videosRecycler:RecyclerView?=null
    var emptyView: View?= null
    var presenter:VideosPresenter?=null
    var progressLayout:LinearLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter= VideosPresenter(Repository(context()))
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
        return R.layout.videos_fragment
    }

    override fun getLayoutSetUps() {
        videosRecycler=rootView!!.findViewById(R.id.videoRecycler)
        emptyView=rootView!!.findViewById(R.id.emptyFrame)
        progressLayout=rootView!!.findViewById(R.id.linProgress)
    }

    override fun showVideos(videoList: List<Videos>) {
        videosRecycler!!.layoutManager=LinearLayoutManager(context)
        videosRecycler!!.adapter=VideosAdapter(videoList)
    }


    override fun showProgressBar(showOrNot: Boolean) {
        if(showOrNot){
            progressLayout!!.visibility=View.VISIBLE

        }else{
            progressLayout!!.visibility=View.GONE

        }
    }

    override fun context(): Context {
        return requireContext()
    }

    override fun showError(text: String) {
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }
    override fun onResume() {
        super.onResume()
        Jzvd.goOnPlayOnResume();

    }

    override fun onPause() {
        super.onPause()
        Jzvd.goOnPlayOnPause();

    }

    override fun onDestroy() {
        super.onDestroy()
        Jzvd.releaseAllVideos();

    }
}