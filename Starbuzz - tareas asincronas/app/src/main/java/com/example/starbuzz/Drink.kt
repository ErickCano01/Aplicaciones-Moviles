package com.example.starbuzz

data class Drink(
    val name: String,
    val description: String,
    val imageResourceId: Int
){
    override fun toString(): String = name
    companion object {
        val drinks = arrayOf(
            Drink("Latte", "A couple of espresso shots with steamed milk", R.drawable.latte),
            Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam", R.drawable.cappuccino),
            Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
        )
    }

}