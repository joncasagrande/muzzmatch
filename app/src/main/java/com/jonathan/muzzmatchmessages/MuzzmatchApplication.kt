package com.jonathan.muzzmatchmessages

import android.app.Application
import androidx.room.Room
import com.jonathan.muzzmatchmessages.persistence.RepositoryDatabase
import com.jonathan.muzzmatchmessages.util.DB_NAME


class MuzzmatchApplication : Application() {

    private var myDatabase: RepositoryDatabase? = null

    override fun onCreate() {
        super.onCreate()

        myDatabase = Room.databaseBuilder(this, RepositoryDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration().build()
    }

    fun getDatabase(): RepositoryDatabase {
        return myDatabase!!
    }
}