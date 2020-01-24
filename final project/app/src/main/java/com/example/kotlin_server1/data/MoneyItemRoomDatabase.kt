package com.example.kotlin_server1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*


// Annotates class to be a Room Database with a table (entity) of the MoneyItem class.
@Database(entities = [MoneyItem::class], version = 2, exportSchema = false)
abstract class MoneyItemRoomDatabase : RoomDatabase()
{
    abstract fun moneyItemDAO(): MoneyItemDAO

    private class MoneyItemDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback()
    {
        override fun onOpen(db: SupportSQLiteDatabase)
        {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.moneyItemDAO())
                }
            }
        }

        suspend fun populateDatabase(moneyItemDAO: MoneyItemDAO)
        {
            // delete all content here
            moneyItemDAO.deleteAll()

            // add sample money items
            var moneyItem = MoneyItem(
                UUID.randomUUID().toString(),
                "ulei",
                "mâncare",
                "cheltuieli",
                5
            )
            moneyItemDAO.insert(moneyItem)
            moneyItem = MoneyItem(
                UUID.randomUUID().toString(),
                "făină",
                "mâncare",
                "cheltuieli",
                3
            )
            moneyItemDAO.insert(moneyItem)

        }
    }

    companion object
    {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: MoneyItemRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MoneyItemRoomDatabase
        {
            val tempInstance =
                INSTANCE
            if (tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyItemRoomDatabase::class.java,
                    "money_item_database"
                ).addCallback(
                    MoneyItemDatabaseCallback(
                        scope
                    )
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}