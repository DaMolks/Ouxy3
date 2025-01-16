package com.damolks.ouxy3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.damolks.ouxy3.data.database.dao.SiteDao
import com.damolks.ouxy3.data.database.dao.TechnicianDao
import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.data.model.Technician

@Database(
    entities = [Technician::class, Site::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class OuxyDatabase : RoomDatabase() {
    abstract fun technicianDao(): TechnicianDao
    abstract fun siteDao(): SiteDao

    companion object {
        private const val DATABASE_NAME = "ouxy_database"

        @Volatile
        private var INSTANCE: OuxyDatabase? = null

        fun getInstance(context: Context): OuxyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OuxyDatabase::class.java,
                    DATABASE_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}