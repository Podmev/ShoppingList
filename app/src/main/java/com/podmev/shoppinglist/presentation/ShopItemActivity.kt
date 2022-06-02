package com.podmev.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.podmev.shoppinglist.R

class ShopItemActivity : AppCompatActivity() {
    private lateinit var viewModel: ShopItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)

        val mode = intent.getStringExtra("extra_mode")
        Log.d("ShopItemActivity", mode.toString())
    }
}
