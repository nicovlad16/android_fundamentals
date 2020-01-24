package com.example.kotlin_server1.data

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.example.kotlin_server1.data.MoneyItem
import com.example.kotlin_server1.data.MoneyItemDAO

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO.
class MoneyItemRepository(
    private val moneyItemDAO: MoneyItemDAO,
    private val activity: AppCompatActivity
)
{

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allMoneyItems: LiveData<List<MoneyItem>> = moneyItemDAO.getAllMoneyItems()
    private val networkApiAdapter = NetworkApiAdapter.INSTANCE

    suspend fun getAll()
    {
        if (verifyAvailableNetwork(activity))
        {
            moneyItemDAO.deleteAll()
            for (moneyItem in networkApiAdapter.fetchAll())
            {
                moneyItemDAO.insert(moneyItem)
            }
        }
    }

    suspend fun insert(moneyItem: MoneyItem)
    {
        if (verifyAvailableNetwork(activity))
        {
            networkApiAdapter.insert(moneyItem)
        }
        else
        {
            moneyItemDAO.insert(moneyItem)
        }
    }

    suspend fun delete(moneyItem: MoneyItem)
    {
        moneyItemDAO.delete(moneyItem)
    }

    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean
    {
        return false
//        val connectivityManager =
//            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
    }

}