package com.example.starbuzz

data class Store(
    val name: String,
    val address: String,
    val hours: String,
    val imageResourceId: Int
) {
    override fun toString() = name

    companion object {
        val stores = arrayOf(
            Store("Downtown", "123 Main St\nNew York, NY", "8:00 AM - 8:00 PM", R.drawable.store1),
            Store("Mall Branch", "456 Mall Ave\nNew York, NY", "9:00 AM - 9:00 PM", R.drawable.store2),
            Store("Airport", "789 Airport Rd\nNew York, NY", "5:00 AM - 11:00 PM", R.drawable.store3)
        )
    }
}