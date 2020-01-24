package com.example.kotlin_server1.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_server1.*
import kotlinx.android.synthetic.main.activity_edit_money_item.*

class EditMoneyItemActivity : AppCompatActivity()
{
    var index = -1
    private lateinit var nameMoneyItemView: EditText
    private lateinit var categoryMoneyItemView: EditText
    private lateinit var typeMoneyItemView: EditText
    private lateinit var amountMoneyItemView: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_money_item)

        nameMoneyItemView = findViewById(R.id.edit_text_edit_money_item_name)
        categoryMoneyItemView = findViewById(R.id.edit_text_edit_money_item_category)
        typeMoneyItemView = findViewById(R.id.edit_text_edit_money_item_type)
        amountMoneyItemView = findViewById(R.id.edit_text_edit_money_item_amount)

        setupActivity()
    }

    private fun setupActivity()
    {
        Log.d("editActivity", "setup")

        val intent = intent

        index = intent.getIntExtra(MONEY_ITEM_INTENT_INDEX, -1)
        val moneyItemName = intent.getStringExtra(EXTRA_REPLY_NAME)
        val moneyItemCategory = intent.getStringExtra(EXTRA_REPLY_CATEGORY)
        val moneyItemType = intent.getStringExtra(EXTRA_REPLY_TYPE)
        val moneyItemAmount = intent.getIntExtra(EXTRA_REPLY_AMOUNT, 0)

        edit_text_edit_money_item_name.setText(moneyItemName)
        edit_text_edit_money_item_category.setText(moneyItemCategory)
        edit_text_edit_money_item_type.setText(moneyItemType)
        edit_text_edit_money_item_amount.setText(moneyItemAmount.toString())

        setButtonEditListener()
        setButtonCancelListener()
        setButtonDeleteListener()
    }

    private fun setButtonEditListener()
    {
        Log.d("editActivity", "editListener")
        val buttonEdit = findViewById<Button>(R.id.button_edit)
        buttonEdit.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(nameMoneyItemView.text) or
                TextUtils.isEmpty(categoryMoneyItemView.text) or
                TextUtils.isEmpty(typeMoneyItemView.text) or
                TextUtils.isEmpty(amountMoneyItemView.text)
            )
            {
                replyIntent.putExtra(EXTRA_REPLY_ACTION_TYPE, "empty")
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            else
            {
                val moneyItemName = nameMoneyItemView.text.toString()
                val moneyItemCategory = categoryMoneyItemView.text.toString()
                val moneyItemType = typeMoneyItemView.text.toString()
                val moneyItemAmount = amountMoneyItemView.text.toString().toInt()

                replyIntent.putExtra(EXTRA_REPLY_ACTION_TYPE, "edited")
                replyIntent.putExtra(EXTRA_REPLY_NAME, moneyItemName)
                replyIntent.putExtra(EXTRA_REPLY_CATEGORY, moneyItemCategory)
                replyIntent.putExtra(EXTRA_REPLY_TYPE, moneyItemType)
                replyIntent.putExtra(EXTRA_REPLY_AMOUNT, moneyItemAmount)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    private fun setButtonCancelListener()
    {
        Log.d("editActivity", "cancelListener")

        val buttonCancel = findViewById<Button>(R.id.button_cancel)
        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }

    private fun setButtonDeleteListener()
    {
        Log.d("editActivity", "deleteListener")
        val buttonDelete = findViewById<Button>(R.id.button_delete)
        buttonDelete.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}