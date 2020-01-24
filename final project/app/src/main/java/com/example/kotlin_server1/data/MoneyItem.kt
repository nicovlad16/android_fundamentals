package com.example.kotlin_server1.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "money_item_table")
data class MoneyItem(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "name")
    @field:SerializedName("name")
    var name: String? = "",

    @ColumnInfo(name = "category")
    @field:SerializedName("category")
    var category: String? = "",

    @ColumnInfo(name = "type")
    @field:SerializedName("type")
    var type: String? = "",

    @ColumnInfo(name = "amount")
    @field:SerializedName("amount")
    var amount: Int? = 0
)
{
    override fun equals(other: Any?): Boolean
    {
        if (other == null)
            return false // null check
        if (javaClass != other.javaClass)
            return false // type check

        val mOther = other as MoneyItem
        return id == mOther.id
                && name == mOther.name
                && category == mOther.category
                && type == mOther.type
                && amount == mOther.amount
    }

    override fun hashCode(): Int
    {
        return Objects.hash(id, name, category, type, amount)
    }
}
