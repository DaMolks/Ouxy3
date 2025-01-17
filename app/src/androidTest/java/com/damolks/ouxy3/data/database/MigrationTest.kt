package com.damolks.ouxy3.data.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        OuxyDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        // Dans la version future si on a besoin de migrer de la v1 à la v2
        // Créer une base de données avec la version 1
        helper.createDatabase(TEST_DB, 1).apply {
            // Insérer des données de test pour la v1
            execSQL("""
                CREATE TABLE IF NOT EXISTS `technicians` (
                    `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    `firstName` TEXT NOT NULL,
                    `lastName` TEXT NOT NULL,
                    `sector` TEXT NOT NULL,
                    `matricule` TEXT NOT NULL,
                    `teamLeader` TEXT NOT NULL,
                    `signaturePath` TEXT,
                    `createdAt` TEXT NOT NULL,
                    `updatedAt` TEXT NOT NULL
                )
            """)

            execSQL("""
                CREATE TABLE IF NOT EXISTS `sites` (
                    `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    `name` TEXT NOT NULL,
                    `address` TEXT NOT NULL,
                    `contactName` TEXT,
                    `contactPhone` TEXT,
                    `notes` TEXT,
                    `createdAt` TEXT NOT NULL,
                    `updatedAt` TEXT NOT NULL
                )
            """)

            // Insérer des données de test
            val technicianValues = ContentValues().apply {
                put("firstName", "John")
                put("lastName", "Doe")
                put("sector", "Nord")
                put("matricule", "T123")
                put("teamLeader", "Jane Smith")
                put("createdAt", LocalDateTime.now().toString())
                put("updatedAt", LocalDateTime.now().toString())
            }
            insert("technicians", SQLiteDatabase.CONFLICT_REPLACE, technicianValues)

            val siteValues = ContentValues().apply {
                put("name", "Site Test")
                put("address", "123 Test Street")
                put("createdAt", LocalDateTime.now().toString())
                put("updatedAt", LocalDateTime.now().toString())
            }
            insert("sites", SQLiteDatabase.CONFLICT_REPLACE, siteValues)
            close()
        }

        // Pour les futures migrations, on pourra valider ici que les données sont correctement migrées
        val db = helper.runMigrationsAndValidate(TEST_DB, 2, true)
        
        // Vérifier que les données ont été correctement migrées
        val cursor = db.query("SELECT * FROM technicians")
        assert(cursor.count > 0)
        cursor.close()
    }
}