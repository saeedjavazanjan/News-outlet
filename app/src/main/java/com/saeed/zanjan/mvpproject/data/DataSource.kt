package com.saeed.zanjan.mvpproject.data

import io.reactivex.Single


interface DataSource {

    fun getNews():Single<List<News>>
    fun getJournals():Single<List<Journal>>
    fun getContractType():Single<List<Category>>
    fun getBanners():Single<List<News>>
    fun getAnnounce():retrofit2.Call<List<Announce>>
    fun bookMark(news:News)
    fun getBookMarks():Single<List<News>>
    fun deleteBookMark(news:News)
    fun getVideos():Single<List<Videos>>
    fun getSearchedData(tile: CharSequence):Single<List<News>>
    fun  getCurrentSavedPackage(currentId: String): Boolean
    fun getWelFareNews():Single<List<News>>


}