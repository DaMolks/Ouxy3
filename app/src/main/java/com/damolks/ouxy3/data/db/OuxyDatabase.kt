package com.damolks.ouxy3.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.damolks.ouxy3.data.model.MarketplaceModule

@Database(
    entities = [
        MarketplaceModule::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class OuxyDatabase : RoomDatabase() {
    abstract fun marketplaceDao(): MarketplaceDao
}