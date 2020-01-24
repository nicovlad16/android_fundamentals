package com.example.kotlin_server1.listeners

import com.example.kotlin_server1.data.MoneyItem

interface OnDeleteListener {
    fun deleteItem(moneyItem: MoneyItem)
}