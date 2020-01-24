package com.example.kotlin_server1.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.kotlin_server1.*

class AddMoneyItemActivity : AppCompatActivity()
{

    private lateinit var nameMoneyItemView: EditText
    private lateinit var categoryMoneyItemView: EditText
    private lateinit var typeMoneyItemView: EditText
    private lateinit var amountMoneyItemView: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_money_item)

        nameMoneyItemView = findViewById(R.id.edit_text_new_money_item_name)
        categoryMoneyItemView = findViewById(R.id.edit_text_new_money_item_category)
        typeMoneyItemView = findViewById(R.id.edit_text_new_money_item_type)
        amountMoneyItemView = findViewById(R.id.edit_text_new_money_item_amount)

        val buttonSave = findViewById<Button>(R.id.button_save)
        buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(nameMoneyItemView.text) or
                TextUtils.isEmpty(categoryMoneyItemView.text) or
                TextUtils.isEmpty(typeMoneyItemView.text) or
                TextUtils.isEmpty(amountMoneyItemView.text)
            )
            {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            else
            {
                val moneyItemName = nameMoneyItemView.text.toString()
                val moneyItemCategory = categoryMoneyItemView.text.toString()
                val moneyItemType = typeMoneyItemView.text.toString()
                val moneyItemAmount = amountMoneyItemView.text.toString().toInt()

                replyIntent.putExtra(EXTRA_REPLY_NAME, moneyItemName)
                replyIntent.putExtra(EXTRA_REPLY_CATEGORY, moneyItemCategory)
                replyIntent.putExtra(EXTRA_REPLY_TYPE, moneyItemType)
                replyIntent.putExtra(EXTRA_REPLY_AMOUNT, moneyItemAmount)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
