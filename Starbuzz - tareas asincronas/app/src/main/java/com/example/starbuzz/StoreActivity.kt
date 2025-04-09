package com.example.starbuzz

import android.os.AsyncTask
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.starbuzz.databinding.ActivityStoreBinding

class StoreActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_STOREID = "storeId"
    }

    private lateinit var binding: ActivityStoreBinding
    private var storeId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storeId = intent.getIntExtra(EXTRA_STOREID, 0)
        loadStoreDetails()
    }

    private fun loadStoreDetails() {
        try {
            val dbHelper = StarbuzzDatabaseHelper(this)
            val db = dbHelper.readableDatabase

            val cursor = db.query(
                StarbuzzDatabaseHelper.TABLE_STORE,
                arrayOf(
                    StarbuzzDatabaseHelper.COLUMN_NAME,
                    StarbuzzDatabaseHelper.COLUMN_ADDRESS,
                    StarbuzzDatabaseHelper.COLUMN_HOURS,
                    StarbuzzDatabaseHelper.COLUMN_IMAGE_RESOURCE_ID,
                    StarbuzzDatabaseHelper.COLUMN_FAVORITE
                ),
                "${StarbuzzDatabaseHelper.COLUMN_ID} = ?",
                arrayOf(storeId.toString()),
                null, null, null
            )

            if (cursor.moveToFirst()) {
                binding.name.text = cursor.getString(0)
                binding.address.text = cursor.getString(1)
                binding.hours.text = "Hours: ${cursor.getString(2)}"
                binding.photo.setImageResource(cursor.getInt(3))
                binding.favorite.isChecked = cursor.getInt(4) == 1
            }

            cursor.close()
            db.close()
        } catch (e: Exception) {
            Toast.makeText(this, "Error loading store details", Toast.LENGTH_SHORT).show()
        }
    }

    fun onFavoriteClicked(view: android.view.View) {
        val isFavorite = (view as CheckBox).isChecked
        UpdateStoreTask().execute(storeId to isFavorite)
    }

    private inner class UpdateStoreTask : AsyncTask<Pair<Int, Boolean>, Void, Boolean>() {
        override fun doInBackground(vararg params: Pair<Int, Boolean>): Boolean {
            val (id, isFavorite) = params[0]
            return try {
                val dbHelper = StarbuzzDatabaseHelper(this@StoreActivity)
                val db = dbHelper.writableDatabase

                val values = android.content.ContentValues().apply {
                    put(StarbuzzDatabaseHelper.COLUMN_FAVORITE, if (isFavorite) 1 else 0)
                }

                db.update(
                    StarbuzzDatabaseHelper.TABLE_STORE,
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
                Toast.makeText(this@StoreActivity, "Error updating favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }
}