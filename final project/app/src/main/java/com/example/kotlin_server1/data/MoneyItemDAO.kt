package com.example.kotlin_server1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlin_server1.data.MoneyItem

@Dao
interface MoneyItemDAO {
    @Query("SELECT * from money_item_table")
    fun getAllMoneyItems(): LiveData<List<MoneyItem>>

    @Query("DELETE FROM money_item_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(moneyItem: MoneyItem)

    @Delete
    suspend fun delete(moneyItem: MoneyItem)

    @Update
    suspend fun update(moneyItem: MoneyItem)
}