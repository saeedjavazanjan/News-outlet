package com.saeed.zanjan.mvpproject.data

import androidx.room.*
import io.reactivex.Single
import retrofit2.Call

@Dao
abstract class LocalDataSource:DataSource {
    override fun getNews(): Single<List<News>> {
        TODO("Not yet implemented")
    }

    override fun getBanners(): Single<List<News>> {
        TODO("Not yet implemented")
    }

    override fun getWelFareNews(): Single<List<News>> {
        TODO("Not yet implemented")
    }
    override fun getAnnounce(): Call<List<Announce>> {
        TODO("Not yet implemented")
    }
  override fun getVideos(): Single<List<Videos>> {
    TODO("Not yet implemented")
  }

    override fun getSearchedData(tile: CharSequence): Single<List<News>> {
        TODO("Not yet implemented")
    }
@Query("SELECT * FROM book_marks WHERE isBookMarked LIKE 1 ")
  abstract  override fun getBookMarks(): Single<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun bookMark(news: News)


    @Delete
    abstract override fun deleteBookMark(news: News)
    override fun getJournals(): Single<List<Journal>> {
        TODO("Not yet implemented")
    }

    override fun getContractType(): Single<List<Category>> {
        TODO("Not yet implemented")
    }
    @Query("SELECT * FROM book_marks WHERE id= :currentId")
    abstract override fun getCurrentSavedPackage(currentId: String): Boolean
}