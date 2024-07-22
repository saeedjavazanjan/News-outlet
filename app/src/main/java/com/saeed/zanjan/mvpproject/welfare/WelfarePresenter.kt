package com.saeed.zanjan.mvpproject.home

import com.saeed.zanjan.mvpproject.data.DataSource
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.welfare.WelfareContractor
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WelfarePresenter(dataSource: DataSource):WelfareContractor.Presenter {
    var view:WelfareContractor.View?=null
    var datas:DataSource?=null
    var disposable:CompositeDisposable?= CompositeDisposable()
    var loaded:Boolean=false

    init {
        this.datas=dataSource

    }


    override fun getWelfareNews() {
        view?.showProgressBar(true)
      datas!!.getWelFareNews().subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(object :SingleObserver<List<News>>{
              override fun onSubscribe(d: Disposable) {
                  disposable!!.add(d)
              }

              override fun onSuccess(t: List<News>) {
                  view?.showWelfareNews(t)
                  view?.showProgressBar(false)
                  loaded=true              }

              override fun onError(e: Throwable) {
                  view?.showProgressBar(false)
                  view?.showError("خطا در ارتباط")              }

          })

    }


    override fun onAttachView(view:WelfareContractor.View) {
        this.view=view
        if(!loaded) {
            getWelfareNews()
        }
    }

    override fun onDetachView() {
        this.view=null
        if(disposable!=null || disposable!!.size()>0){

            disposable!!.clear()
        }
    }
}