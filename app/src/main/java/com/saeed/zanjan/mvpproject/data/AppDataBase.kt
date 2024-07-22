package com.saeed.zanjan.mvpproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [News::class],version = 1,exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    public abstract fun getLocalDataSource():LocalDataSource
    companion object{

        fun getAppDataBase(context: Context):AppDataBase{
            var instance:AppDataBase?=null

            if(instance==null){
                instance=Room.databaseBuilder(context,AppDataBase::class.java,"news_db").allowMainThreadQueries().build()

            }
            return instance
        }


    }

}