package com.example.starbuzz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.starbuzz.databinding.ActivityFoodBinding

class FoodActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FOODID = "foodId"
    }

    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodId = intent.extras?.get(EXTRA_FOODID) as Int
        val food = Food.foodItems[foodId]

        binding.name.text = food.name
        binding.description.text = "${food.description}\n\nPrice: $${food.price}"
        binding.photo.setImageResource(food.imageResourceId)
        binding.photo.contentDescription = food.name
    }
}