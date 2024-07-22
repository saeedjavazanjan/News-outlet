package com.saeed.zanjan.mvpproject.journals

import com.saeed.zanjan.mvpproject.data.DataSource
import com.saeed.zanjan.mvpproject.data.Journal
import com.saeed.zanjan.mvpproject.data.News
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class JournalsPresenter(dataSource: DataSource):JournalsContractor.Presenter {
    var datas:DataSource?=null
    var view :JournalsContractor.View?=null
    var disposable: CompositeDisposable?= CompositeDisposable()
    var loaded:Boolean=false

    init {
        this.datas=dataSource
    }

    override fun getJournals() {
        view?.showProgressBar(true)
        datas!!.getJournals().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<Journal>>{
                override fun onSubscribe(d: Disposable) {
                    disposable!!.add(d)
                }

                override fun onSuccess(t: List<Journal>) {
                    view!!.showJournals(t)
                    view?.showProgressBar(false)
                    loaded=true                }

                override fun onError(e: Throwable) {
                    view?.showProgressBar(false)
                    view?.showError("خطایی رخ داده است")                }

            })


    }




    override fun onAttachView(view: JournalsContractor.View) {
        this.view=view
        if(!loaded) {
       getJournals()
        }
    }

    override fun onDetachView() {
        this.view=null
        if(disposable!=null || disposable!!.size()>0){

            disposable!!.clear()
        }
    }
}