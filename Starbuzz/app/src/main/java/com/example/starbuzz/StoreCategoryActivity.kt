package com.example.starbuzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class StoreCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_category)

        val listStores: ListView = findViewById(R.id.list_stores)
        val listAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Store.stores
        )
        listStores.adapter = listAdapter

        listStores.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, StoreActivity::class.java)
            intent.putExtra(StoreActivity.EXTRA_STOREID, position)
            startActivity(intent)
        }
    }
}