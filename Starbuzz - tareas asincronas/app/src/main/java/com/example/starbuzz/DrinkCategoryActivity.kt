package com.example.starbuzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class DrinkCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        val listDrinks: ListView = findViewById(R.id.list_drinks)
        val listAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Drink.drinks
        )
        listDrinks.adapter = listAdapter

        listDrinks.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DrinkActivity::class.java)
            intent.putExtra(DrinkActivity.EXTRA_DRINKID, position)
            startActivity(intent)
        }
    }
}