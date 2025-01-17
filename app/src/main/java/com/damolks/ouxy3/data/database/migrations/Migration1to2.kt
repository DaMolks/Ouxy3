package com.damolks.ouxy3.data.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Template pour une future migration de la version 1 à 2
 * À implémenter quand des changements seront nécessaires
 */
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Exemple de migration :
        // database.execSQL("""
        //    ALTER TABLE technicians 
        //    ADD COLUMN new_column TEXT DEFAULT NULL
        // """)
    }
}