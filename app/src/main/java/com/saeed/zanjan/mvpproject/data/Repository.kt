package com.saeed.zanjan.mvpproject.data

import android.content.Context
import io.reactivex.Single
import retrofit2.Call

class Repository(context: Context):DataSource {
    var cloudDataSource:CloudDataSource?=CloudDataSource()
    var localDataSource:LocalDataSource?=null
    init {
        localDataSource=AppDataBase.getAppDataBase(context).getLocalDataSource()
    }

    override fun getNews(): Single<List<News>> {
        return cloudDataSource!!.getNews()
    }

    override fun getJournals(): Single<List<Journal>> {

       return cloudDataSource!!.getJournals()
    }

    override fun getContractType(): Single<List<Category>> {
        return cloudDataSource!!.getContractType()
    }

    override fun getBanners(): Single<List<News>> {
        return cloudDataSource!!.getBanners()

    }

    override fun getAnnounce(): Call<List<Announce>> {
        return cloudDataSource!!.getAnnounce()
    }


    override fun bookMark(news: News) {
        localDataSource!!.bookMark(news)
    }

    override fun getBookMarks(): Single<List<News>> {
      return  localDataSource!!.getBookMarks()
    }

    override fun deleteBookMark(news: News) {
        localDataSource!!.deleteBookMark(news)
    }



    override fun getVideos(): Single<List<Videos>> {
        return cloudDataSource!!.getVideos()
    }

    override fun getSearchedData(tile: CharSequence): Single<List<News>> {
        return cloudDataSource!!.getSearchedData(tile)
    }

    override fun getCurrentSavedPackage(currentId: String): Boolean {
        return localDataSource!!.getCurrentSavedPackage(currentId)
    }

    override fun getWelFareNews(): Single<List<News>> {
        return cloudDataSource!!.getWelFareNews()
    }
}