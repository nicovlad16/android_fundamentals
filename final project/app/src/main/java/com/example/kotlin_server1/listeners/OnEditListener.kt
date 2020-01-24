package com.example.kotlin_server1.listeners

import com.example.kotlin_server1.data.MoneyItem

interface OnEditListener {
    fun editItem(moneyItem: MoneyItem, index: Int)
}