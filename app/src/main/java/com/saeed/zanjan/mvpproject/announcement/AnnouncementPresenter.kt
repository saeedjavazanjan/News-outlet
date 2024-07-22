package com.saeed.zanjan.mvpproject.announcement

import androidx.lifecycle.MutableLiveData
import com.saeed.zanjan.mvpproject.data.Announce
import com.saeed.zanjan.mvpproject.data.DataSource
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

class AnnouncementPresenter(dataSource: DataSource):AnnouncementContractor.Presenter {
    var view:AnnouncementContractor.View?=null
    var dataSource:DataSource?=null
    var disposable:CompositeDisposable= CompositeDisposable()
    var loaded:Boolean=false
    var announcement: MutableLiveData<List<Announce>>?= MutableLiveData<List<Announce>>()

    init {
        this.dataSource=dataSource
    }
    override fun getAnnounce() {
        view!!.showProgressBar(true)

        dataSource!!.getAnnounce().enqueue(object :retrofit2.Callback<List<Announce>>{
            override fun onResponse(
                call: Call<List<Announce>>,
                response: Response<List<Announce>>
            ) {
                loaded=true
                announcement!!.value=response.body()
                view!!.showAnnounce(announcement!!)
                view!!.showProgressBar(false)                }

            override fun onFailure(call: Call<List<Announce>>, t: Throwable) {
                view!!.showError("خطا در ارتباط")
                view?.showProgressBar(false)            }
        })


     /*   dataSource!!.getCategory().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object:SingleObserver<List<MyAnnouncement>>{
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onSuccess(t: List<MyAnnouncement>) {
                    loaded=true
                    view!!.showCategory(t)
                    view!!.showProgressBar(false)

                }

                override fun onError(e: Throwable) {
                    view!!.showError("خطا در ارتباط")
                    view?.showProgressBar(false)
                }


            })
*/
    }

    override fun onAttachView(view: AnnouncementContractor.View) {
        this.view=view
        if(!loaded){
            getAnnounce()
        }
    }

    override fun onDetachView() {
        this.view=null
        if(disposable!=null||disposable.size()>0){
            disposable.clear()
        }



    }
}