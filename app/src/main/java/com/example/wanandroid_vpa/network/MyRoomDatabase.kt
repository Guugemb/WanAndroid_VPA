package com.example.wanandroid_vpa.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wanandroid_vpa.home.bean.ArticleBean

/**
 * Created by geegumb on 2020/12/9
 *
 */
@Database(entities = [ArticleBean::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract val homeArticleDao: HomeArticleDao

    companion object {
        @Volatile
        private var instance: MyRoomDatabase? = null

        fun getInstance(context: Context): MyRoomDatabase {
            if (null == instance) {
                synchronized(MyRoomDatabase::class) {
                    if (null == instance) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            MyRoomDatabase::class.java,
                            "MyDatabase.db"
                        ).allowMainThreadQueries().build()
                    }
                }
            }
            return instance!!
        }

    }
}