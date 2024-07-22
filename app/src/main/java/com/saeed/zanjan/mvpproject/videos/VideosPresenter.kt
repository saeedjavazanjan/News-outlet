package com.saeed.zanjan.mvpproject.videos

import android.view.View
import com.saeed.zanjan.mvpproject.data.DataSource
import com.saeed.zanjan.mvpproject.data.Videos
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class VideosPresenter(dataSource: DataSource):VideosContractor.Presenter {
   var view:VideosContractor.View?=null
    var videoData:DataSource?=null
   var disposable:Disposable?=null
    var dataLoaded:Boolean=false
    init {
        this.videoData=dataSource
    }
    override fun getVideos() {
        view!!.showProgressBar(true)
    videoData!!.getVideos().subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object :SingleObserver<List<Videos>>{
            override fun onSubscribe(d: Disposable) {
                disposable=d
            }

            override fun onSuccess(t: List<Videos>) {
                view!!.showVideos(t)
                dataLoaded=true
                view!!.showProgressBar(false)

            }

            override fun onError(e: Throwable) {
                view!!.showError("خطا در ارتباط")
                view!!.showProgressBar(false)

            }


        })


    }

    override fun onAttachView(view: VideosContractor.View) {
        this.view=view
        if(!dataLoaded){
            getVideos()

        }
    }

    override fun onDetachView() {
        this.view=null

        if(disposable!=null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }
}