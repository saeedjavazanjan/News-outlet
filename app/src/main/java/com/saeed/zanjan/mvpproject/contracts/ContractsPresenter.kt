package com.saeed.zanjan.mvpproject.contracts

import com.saeed.zanjan.mvpproject.data.Category
import com.saeed.zanjan.mvpproject.data.DataSource
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ContractsPresenter(dataSource: DataSource):ContractsContractor.Presenter {
    var data:DataSource?=null
    var disposable:CompositeDisposable?=CompositeDisposable()
    var view:ContractsContractor.View?=null
    var loaded:Boolean=false
    init {
        this.data=dataSource
    }
    override fun getContractTypeList() {
        view!!.showProgressBar(true)
        data!!.getContractType().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<Category>>{
                override fun onSubscribe(d: Disposable) {
                    disposable!!.add(d)
                }

                override fun onSuccess(t: List<Category>) {
                    view!!.showContractTypeList(t)
                    view!!.showProgressBar(false)
                    loaded=true


                }

                override fun onError(e: Throwable) {
                    view!!.showProgressBar(false)
                    view!!.showError("خطایی رخ داده است")

                }
            })


    }

    override fun onAttachView(view: ContractsContractor.View) {
        this.view=view
        if (!loaded){
            getContractTypeList()
        }
    }

    override fun onDetachView() {
        this.view=null
        if(disposable!!.size()>0 ||disposable!=null){
            disposable!!.clear()

        }

    }
}