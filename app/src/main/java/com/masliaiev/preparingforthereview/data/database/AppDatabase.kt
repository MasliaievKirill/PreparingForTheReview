package com.masliaiev.preparingforthereview.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masliaiev.preparingforthereview.data.database.models.*

@Database(
    version = 1,
    entities = [
        AutomobileDbModel::class,
        EmployeeDbModel::class,
        EmployeeJobTitleDbModel::class,
        JobTitleDbModel::class,
        PhoneNumberDbModel::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    abstract fun addEmployeeDao(): AddEmployeeDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "app_db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}