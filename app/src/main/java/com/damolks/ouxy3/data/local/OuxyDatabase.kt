package com.damolks.ouxy3.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.data.model.Technician
import com.damolks.ouxy3.data.local.converter.DateTimeConverter

@Database(
    entities = [
        Technician::class,
        Site::class
    ],
    version = 2,  // Incrémenté la version
    exportSchema = true
)
@TypeConverters(DateTimeConverter::class)
abstract class OuxyDatabase : RoomDatabase() {
    abstract fun technicianDao(): TechnicianDao
    abstract fun siteDao(): SiteDao

    companion object {
        private const val DATABASE_NAME = "ouxy.db"

        @Volatile
        private var instance: OuxyDatabase? = null

        fun getInstance(context: Context): OuxyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): OuxyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                OuxyDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()  // En dev uniquement ! Pour la prod, il faudra une vraie stratégie de migration
            .build()
        }
    }
}