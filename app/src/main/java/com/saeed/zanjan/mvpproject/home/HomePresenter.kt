package com.saeed.zanjan.mvpproject.home

import android.view.View
import com.saeed.zanjan.mvpproject.data.Banners
import com.saeed.zanjan.mvpproject.data.DataSource
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.search.SearchContractor
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(dataSource: DataSource):HomeContractor.Presenter {
    var view:HomeContractor.View?=null
    var datas:DataSource?=null
    var disposable:CompositeDisposable?= CompositeDisposable()
    var loaded:Boolean=false

    init {
this.datas=dataSource

    }


    override fun getNews() {
        view?.showProgressBar(true)
            datas!!.getNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<News>> {
                    override fun onSubscribe(d: Disposable) {
                        disposable!!.add(d)
                    }

                    override fun onSuccess(t: List<News>) {
                        view?.showNews(t)
                        view?.showProgressBar(false)
                        loaded=true
                    }

                    override fun onError(e: Throwable) {
                        view?.showProgressBar(false)
                        view?.showError("خطا در ارتباط")

                    }
                })

    }

    override fun getBanners() {

        view?.showProgressBar(true)
            datas!!.getBanners().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<News>>{
                override fun onSubscribe(d: Disposable) {
                    disposable!!.add(d)
                }

                override fun onSuccess(t: List<News>) {
                    view?.showBanners(t)
                    view?.showProgressBar(false)
                    loaded=true

                }

                override fun onError(e: Throwable) {
                    view?.showError("خطا در ارتباط")
                    view?.showProgressBar(false)

                }


            })

    }

    override fun onAttachView(view:HomeContractor.View) {
        this.view=view
        if(!loaded) {
            getNews()
            getBanners()
        }
    }

    override fun onDetachView() {
        this.view=null
        if(disposable!=null || disposable!!.size()>0){

            disposable!!.clear()
        }
    }
}