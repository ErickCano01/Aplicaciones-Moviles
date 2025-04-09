package com.example.starbuzz

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.starbuzz.databinding.ActivityTopLevelBinding

class TopLevelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopLevelBinding
    private lateinit var db: SQLiteDatabase
    private lateinit var drinksCursor: Cursor
    private lateinit var foodsCursor: Cursor
    private lateinit var storesCursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupOptionsListView()
        setupFavoritesLists()
    }

    private fun setupOptionsListView() {
        binding.listOptions.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this, DrinkCategoryActivity::class.java))
                1 -> startActivity(Intent(this, FoodCategoryActivity::class.java))
                2 -> startActivity(Intent(this, StoreCategoryActivity::class.java))
            }
        }
    }

    private fun setupFavoritesLists() {
        try {
            val dbHelper = StarbuzzDatabaseHelper(this)
            db = dbHelper.readableDatabase

            // Configurar lista de bebidas favoritas
            drinksCursor = getFavoritesCursor(StarbuzzDatabaseHelper.TABLE_DRINK)
            setupFavoriteListView(
                binding.listFavoriteDrinks,
                drinksCursor,
                DrinkActivity.EXTRA_DRINKID,
                DrinkActivity::class.java
            )

            // Configurar lista de comidas favoritas
            foodsCursor = getFavoritesCursor(StarbuzzDatabaseHelper.TABLE_FOOD)
            setupFavoriteListView(
                binding.listFavoriteFoods,
                foodsCursor,
                FoodActivity.EXTRA_FOODID,
                FoodActivity::class.java
            )

            // Configurar lista de tiendas favoritas
            storesCursor = getFavoritesCursor(StarbuzzDatabaseHelper.TABLE_STORE)
            setupFavoriteListView(
                binding.listFavoriteStores,
                storesCursor,
                StoreActivity.EXTRA_STOREID,
                StoreActivity::class.java
            )

        } catch (e: Exception) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFavoritesCursor(tableName: String): Cursor {
        return db.query(
            tableName,
            arrayOf(StarbuzzDatabaseHelper.COLUMN_ID, StarbuzzDatabaseHelper.COLUMN_NAME),
            "${StarbuzzDatabaseHelper.COLUMN_FAVORITE} = 1",
            null, null, null, null
        )
    }

    private fun setupFavoriteListView(
        listView: ListView,
        cursor: Cursor,
        extraName: String,
        activityClass: Class<*>
    ) {
        val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cursor,
            arrayOf(StarbuzzDatabaseHelper.COLUMN_NAME),
            intArrayOf(android.R.id.text1),
            0
        )

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, _, id ->
            val intent = Intent(this, activityClass)
            intent.putExtra(extraName, id.toInt())
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        refreshFavoritesLists()
    }

    private fun refreshFavoritesLists() {
        drinksCursor = getFavoritesCursor(StarbuzzDatabaseHelper.TABLE_DRINK)
        (binding.listFavoriteDrinks.adapter as SimpleCursorAdapter).changeCursor(drinksCursor)

        foodsCursor = getFavoritesCursor(StarbuzzDatabaseHelper.TABLE_FOOD)
        (binding.listFavoriteFoods.adapter as SimpleCursorAdapter).changeCursor(foodsCursor)

        storesCursor = getFavoritesCursor(StarbuzzDatabaseHelper.TABLE_STORE)
        (binding.listFavoriteStores.adapter as SimpleCursorAdapter).changeCursor(storesCursor)
    }

    override fun onDestroy() {
        super.onDestroy()
        drinksCursor.close()
        foodsCursor.close()
        storesCursor.close()
        db.close()
    }
}