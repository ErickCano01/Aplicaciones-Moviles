package com.example.starbuzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView

class TopLevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)

        val listView: ListView = findViewById(R.id.list_options)
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this, DrinkCategoryActivity::class.java))
                1 -> startActivity(Intent(this, FoodCategoryActivity::class.java))
                2 -> startActivity(Intent(this, StoreCategoryActivity::class.java))
            }
        }
    }
}