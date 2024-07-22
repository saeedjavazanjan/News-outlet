package com.saeed.zanjan.mvpproject.data

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface ApiService {

@GET("senddata.php")
fun getNews():Single<List<News>>

    @GET("group.php")
    fun getContractType():Single<List<Category>>

    @GET("journals.php")
    fun getJournals():Single<List<Journal>>

    @GET("welfare.php")
    fun getWelfareNews():Single<List<News>>
@GET("banner.php")
fun getBanners():Single<List<News>>

@GET("lastnews.php")
fun getAnnounce():Call<List<Announce>>

@GET("video_news.php")
fun getVideos():Single<List<Videos>>
@GET("search.php")
fun getSearchedData(@Query("search") search:String):Single<List<News>>

}