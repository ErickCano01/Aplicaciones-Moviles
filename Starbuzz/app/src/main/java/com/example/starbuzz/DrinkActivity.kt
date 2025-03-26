package com.example.starbuzz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.starbuzz.databinding.ActivityDrinkBinding

class DrinkActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DRINKID = "drinkId"
    }

    private lateinit var binding: ActivityDrinkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drinkId = intent.extras?.get(EXTRA_DRINKID) as Int
        val drink = Drink.drinks[drinkId]

        binding.name.text = drink.name
        binding.description.text = drink.description
        binding.photo.setImageResource(drink.imageResourceId)
        binding.photo.contentDescription = drink.name
    }
}