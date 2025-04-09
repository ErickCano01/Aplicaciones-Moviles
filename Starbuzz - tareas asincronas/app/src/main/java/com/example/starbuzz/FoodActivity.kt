package com.example.starbuzz

import android.os.AsyncTask
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.starbuzz.databinding.ActivityFoodBinding

class FoodActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FOODID = "foodId"
    }

    private lateinit var binding: ActivityFoodBinding
    private var foodId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodId = intent.getIntExtra(EXTRA_FOODID, 0)
        loadFoodDetails()
    }

    private fun loadFoodDetails() {
        try {
            val dbHelper = StarbuzzDatabaseHelper(this)
            val db = dbHelper.readableDatabase

            val cursor = db.query(
                StarbuzzDatabaseHelper.TABLE_FOOD,
                arrayOf(
                    StarbuzzDatabaseHelper.COLUMN_NAME,
                    StarbuzzDatabaseHelper.COLUMN_DESCRIPTION,
                    StarbuzzDatabaseHelper.COLUMN_IMAGE_RESOURCE_ID,
                    StarbuzzDatabaseHelper.COLUMN_FAVORITE,
                    "PRICE"
                ),
                "${StarbuzzDatabaseHelper.COLUMN_ID} = ?",
                arrayOf(foodId.toString()),
                null, null, null
            )

            if (cursor.moveToFirst()) {
                binding.name.text = cursor.getString(0)
                binding.description.text = "${cursor.getString(1)}\n\nPrice: $${cursor.getDouble(4)}"
                binding.photo.setImageResource(cursor.getInt(2))
                binding.favorite.isChecked = cursor.getInt(3) == 1
            }

            cursor.close()
            db.close()
        } catch (e: Exception) {
            Toast.makeText(this, "Error loading food details", Toast.LENGTH_SHORT).show()
        }
    }

    fun onFavoriteClicked(view: android.view.View) {
        val isFavorite = (view as CheckBox).isChecked
        UpdateFoodTask().execute(foodId to isFavorite)
    }

    private inner class UpdateFoodTask : AsyncTask<Pair<Int, Boolean>, Void, Boolean>() {
        override fun doInBackground(vararg params: Pair<Int, Boolean>): Boolean {
            val (id, isFavorite) = params[0]
            return try {
                val dbHelper = StarbuzzDatabaseHelper(this@FoodActivity)
                val db = dbHelper.writableDatabase

                val values = android.content.ContentValues().apply {
                    put(StarbuzzDatabaseHelper.COLUMN_FAVORITE, if (isFavorite) 1 else 0)
                }

                db.update(
                    StarbuzzDatabaseHelper.TABLE_FOOD,
                    values,
                    "${StarbuzzDatabaseHelper.COLUMN_ID} = ?",
                    arrayOf(id.toString())
                )

                db.close()
                true
            } catch (e: Exception) {
                false
            }
        }

        override fun onPostExecute(success: Boolean) {
            if (!success) {
                Toast.makeText(this@FoodActivity, "Error updating favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }
}