package com.saeed.zanjan.mvpproject.data

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CloudDataSource:DataSource {


    var apiService:ApiService?=null

    init {

        var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://news.devejumpgroup.ir/panel/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService=retrofit.create(ApiService::class.java)

    }

    override fun getNews(): Single<List<News>> {
       return apiService!!.getNews()
    }

    override fun getJournals(): Single<List<Journal>> {
        return apiService!!.getJournals()
    }

    override fun getContractType(): Single<List<Category>> {
        return apiService!!.getContractType()
    }

    override fun getBanners(): Single<List<News>> {
        return apiService!!.getBanners()
    }

    override fun getAnnounce(): Call<List<Announce>> {
        return apiService!!.getAnnounce()
    }


    override fun bookMark(news: News) {
        TODO("Not yet implemented")
    }



    override fun getBookMarks(): Single<List<News>> {
        TODO("Not yet implemented")
    }

    override fun deleteBookMark(news: News) {
        TODO("Not yet implemented")
    }



    override fun getVideos(): Single<List<Videos>> {
        return apiService!!.getVideos()
    }

    override fun getSearchedData(tile: CharSequence): Single<List<News>> {

        return apiService!!.getSearchedData(tile.toString())
    }

    override fun getCurrentSavedPackage(currentId: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWelFareNews(): Single<List<News>> {
        return apiService!!.getWelfareNews()
    }


}