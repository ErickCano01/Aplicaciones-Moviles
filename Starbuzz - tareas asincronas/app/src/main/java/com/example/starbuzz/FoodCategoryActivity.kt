package com.example.starbuzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class FoodCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_category)

        val listFood: ListView = findViewById(R.id.list_food)
        val listAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Food.foodItems.map { "${it.name} - $${it.price}" }
        )
        listFood.adapter = listAdapter

        listFood.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, FoodActivity::class.java)
            intent.putExtra(FoodActivity.EXTRA_FOODID, position)
            startActivity(intent)
        }
    }
}