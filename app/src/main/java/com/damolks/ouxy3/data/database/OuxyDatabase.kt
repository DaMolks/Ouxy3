package com.damolks.ouxy3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.damolks.ouxy3.data.database.dao.SiteDao
import com.damolks.ouxy3.data.database.dao.TechnicianDao
import com.damolks.ouxy3.data.database.entity.Site
import com.damolks.ouxy3.data.database.entity.Technician

@Database(
    entities = [
        Technician::class,
        Site::class
    ],
    version = 1
)
abstract class OuxyDatabase : RoomDatabase() {
    abstract fun technicianDao(): TechnicianDao
    abstract fun siteDao(): SiteDao
}