package com.example.starbuzz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.starbuzz.databinding.ActivityStoreBinding

class StoreActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_STOREID = "storeId"
    }

    private lateinit var binding: ActivityStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storeId = intent.extras?.get(EXTRA_STOREID) as Int
        val store = Store.stores[storeId]

        binding.name.text = store.name
        binding.address.text = store.address
        binding.hours.text = "Hours: ${store.hours}"
        binding.photo.setImageResource(store.imageResourceId)
        binding.photo.contentDescription = store.name
    }
}