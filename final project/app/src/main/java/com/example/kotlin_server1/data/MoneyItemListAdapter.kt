package com.example.kotlin_server1.data

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_server1.R
import com.example.kotlin_server1.listeners.OnDeleteListener
import com.example.kotlin_server1.listeners.OnEditListener

class MoneyItemListAdapter internal constructor(
    context: Context,
    private val deleteListener: OnDeleteListener,
    private val editListener: OnEditListener
) : RecyclerView.Adapter<MoneyItemListAdapter.MoneyItemViewHolder>()
{

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var moneyItems = emptyList<MoneyItem>() // cached copy of moneyItems

    inner class MoneyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val moneyItemView: TextView = itemView.findViewById(R.id.money_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyItemViewHolder
    {
        val itemView = inflater.inflate(R.layout.recycler_view_item, parent, false)
        return MoneyItemViewHolder(itemView)
    }

    override fun getItemCount() = moneyItems.size

    override fun onBindViewHolder(holder: MoneyItemViewHolder, position: Int)
    {
        val current = moneyItems[position]
        val text = current.name + "\n" + current.id
        holder.moneyItemView.text = text

        holder.itemView.setOnClickListener {
            editListener.editItem(moneyItems[position], position)
            Log.d("adapter", "edit")
        }

//        holder.itemView.setOnClickListener {
//            deleteListener.deleteItem(moneyItems[position])
//            Log.d("adapter", "delete")
//        }
    }

    internal fun setMoneyItems(moneyItems: List<MoneyItem>)
    {
        this.moneyItems = moneyItems
        notifyDataSetChanged()
    }
}