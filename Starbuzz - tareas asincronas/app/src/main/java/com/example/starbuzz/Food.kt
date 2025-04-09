package com.example.starbuzz

data class Food(
    val name: String,
    val description: String,
    val imageResourceId: Int,
    val price: Double
) {
    override fun toString() = "$name - $${price}"

    companion object {
        val foodItems = arrayOf(
            Food("Croissant", "Buttery flaky pastry", R.drawable.croissant, 2.50),
            Food("Sandwich", "Fresh bread with fillings", R.drawable.sandwich, 4.00),
            Food("Muffin", "Sweet baked good", R.drawable.muffin, 3.00)
        )
    }
}