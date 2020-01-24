package com.example.kotlin_server1.data

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.kotlin_server1.data.MoneyItem
import com.example.kotlin_server1.data.MoneyItemRepository
import com.example.kotlin_server1.data.MoneyItemRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class MoneyItemViewModel(application: Application, activity: AppCompatActivity) :
    AndroidViewModel(application)
{

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: MoneyItemRepository
    // LiveData gives us updated moneyItems when they change.
    var allMoneyItems: LiveData<List<MoneyItem>>

    init
    {
        // Gets reference to MoneyItemDAO from MoneyItemRoomDatabase to construct
        // the correct MoneyItemRepository.
        val moneyItemDAO =
            MoneyItemRoomDatabase.getDatabase(application, viewModelScope).moneyItemDAO()
        repository =
            MoneyItemRepository(moneyItemDAO, activity)


        allMoneyItems = repository.allMoneyItems

        getAll()
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on the main thread,
     * blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */

//    private fun getAll(): LiveData<List<MoneyItem>> = viewModelScope.launch {
//        repository.getAll()
//    }
    fun getAll()
    {
        viewModelScope.launch {
           repository.getAll()
        }
    }

    fun insert(moneyItem: MoneyItem) = viewModelScope.launch {
        repository.insert(moneyItem)
    }

    fun delete(moneyItem: MoneyItem) = viewModelScope.launch {
        repository.delete(moneyItem)
    }

}