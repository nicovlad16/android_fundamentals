package com.example.kotlin_server1.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_server1.*
import com.example.kotlin_server1.data.MoneyItem
import com.example.kotlin_server1.data.MoneyItemListAdapter
import com.example.kotlin_server1.data.MoneyItemViewModel
import com.example.kotlin_server1.listeners.OnDeleteListener
import com.example.kotlin_server1.listeners.OnEditListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class MainActivity : AppCompatActivity(), OnDeleteListener, OnEditListener
{
    private lateinit var moneyItemViewModel: MoneyItemViewModel
    private lateinit var moneyItemListAdapter: MoneyItemListAdapter
    private val newMoneyItemActivityRequestCode = 1
    private val editMoneyItemActivityRequestCode = 2


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        moneyItemListAdapter = MoneyItemListAdapter(this, this, this)
        recyclerView.adapter = moneyItemListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        moneyItemViewModel = ViewModelProvider(this).get(MoneyItemViewModel::class.java)
        moneyItemViewModel.allMoneyItems?.observe(this, Observer { moneyItems ->
            // Update the cached copy of the words in the adapter.
            moneyItems?.let { moneyItemListAdapter.setMoneyItems(it) }
        })


        setFloatingActionButtonListener()

    }

    private fun setFloatingActionButtonListener()
    {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddMoneyItemActivity::class.java)
            startActivityForResult(intent, newMoneyItemActivityRequestCode)
        }
    }

    override fun deleteItem(moneyItem: MoneyItem)
    {
        moneyItemViewModel.delete(moneyItem)
    }

    override fun editItem(moneyItem: MoneyItem, index: Int)
    {
        Log.d("main", "edit")

        val intent = Intent(this@MainActivity, EditMoneyItemActivity::class.java)
        intent.putExtra(MONEY_ITEM_INTENT_INDEX, index)
        intent.putExtra(EXTRA_REPLY_NAME, moneyItem.name)
        intent.putExtra(EXTRA_REPLY_CATEGORY, moneyItem.category)
        intent.putExtra(EXTRA_REPLY_TYPE, moneyItem.type)
        intent.putExtra(EXTRA_REPLY_AMOUNT, moneyItem.amount)

        startActivityForResult(intent, editMoneyItemActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newMoneyItemActivityRequestCode && resultCode == Activity.RESULT_OK)
        {

            data?.let {
                moneyItemViewModel.insert(
                    MoneyItem(
                        UUID.randomUUID().toString(),
                        it.getStringExtra(EXTRA_REPLY_NAME),
                        it.getStringExtra(EXTRA_REPLY_CATEGORY),
                        it.getStringExtra(EXTRA_REPLY_TYPE),
                        it.getIntExtra(EXTRA_REPLY_AMOUNT, 0)
                    )
                )
                myToast("Item successfully added.")
            }

        }
        else if (requestCode == newMoneyItemActivityRequestCode)
        {
            myToast("Money item not saved because one of the fields is empty.")
        }

        if (requestCode == editMoneyItemActivityRequestCode && resultCode == Activity.RESULT_OK)
        {

            // that means edit listener was triggered
            if (data?.getStringExtra(EXTRA_REPLY_ACTION_TYPE) == "edited")
            {

//            val bundle = data?.extras
//            val index = bundle?.getInt(MONEY_ITEM_INTENT_INDEX, -1)
//            val moneyItemName = bundle?.getString(EXTRA_REPLY_NAME)
//            val moneyItemCategory = bundle?.getString(EXTRA_REPLY_CATEGORY)
//            val moneyItemType = bundle?.getString(EXTRA_REPLY_TYPE)
//            val moneyItemAmount = bundle?.getInt(EXTRA_REPLY_AMOUNT, 0)

                // TO-DO

                myToast("Item successfully edited.")
            }
            else // delete listener was triggered
            {
                myToast("Item successfully deleted.")

            }
        }
        // cancel listener was triggered
        else if (requestCode == editMoneyItemActivityRequestCode && resultCode == Activity.RESULT_CANCELED)
        {
            // edit listener was triggered, but with empty fields
            if (data?.getStringExtra(EXTRA_REPLY_ACTION_TYPE) == "empty")
            {
                myToast("Money item not saved because one of the fields is empty.")
            }
            else // cancel listener was triggered
            {
                myToast("Edit was canceled.")
            }
        }
    }

    private fun myToast(text: String)
    {
        Toast.makeText(
            applicationContext,
            text,
            Toast.LENGTH_LONG
        ).show()
    }
}
