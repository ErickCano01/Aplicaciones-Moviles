package com.example.starbuzz

import android.os.AsyncTask
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.starbuzz.databinding.ActivityDrinkBinding

class DrinkActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DRINKID = "drinkId"
    }

    private lateinit var binding: ActivityDrinkBinding
    private var currentDrinkId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el ID de la bebida actual
        currentDrinkId = intent.getIntExtra(EXTRA_DRINKID, 0)
        loadDrinkDetails(currentDrinkId)
    }

    private fun loadDrinkDetails(drinkId: Int) {
        try {
            val dbHelper = StarbuzzDatabaseHelper(this)
            val db = dbHelper.readableDatabase

            val cursor = db.query(
                StarbuzzDatabaseHelper.TABLE_DRINK,
                arrayOf(
                    StarbuzzDatabaseHelper.COLUMN_NAME,
                    StarbuzzDatabaseHelper.COLUMN_DESCRIPTION,
                    StarbuzzDatabaseHelper.COLUMN_IMAGE_RESOURCE_ID,
                    StarbuzzDatabaseHelper.COLUMN_FAVORITE
                ),
                "${StarbuzzDatabaseHelper.COLUMN_ID} = ?",
                arrayOf(drinkId.toString()),
                null, null, null
            )

            if (cursor.moveToFirst()) {
                val name = cursor.getString(0)
                val description = cursor.getString(1)
                val photoId = cursor.getInt(2)
                val isFavorite = cursor.getInt(3) == 1

                binding.name.text = name
                binding.description.text = description
                binding.photo.setImageResource(photoId)
                binding.photo.contentDescription = name
                binding.favorite.isChecked = isFavorite
            } else {
                Toast.makeText(this, "Bebida no encontrada", Toast.LENGTH_SHORT).show()
            }

            cursor.close()
            db.close()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al acceder a la base de datos", Toast.LENGTH_SHORT).show()
        }
    }

    fun onFavoriteClicked(view: android.view.View) {
        val isFavorite = (view as CheckBox).isChecked
        UpdateDrinkTask().execute(currentDrinkId to isFavorite)
    }

    private inner class UpdateDrinkTask : AsyncTask<Pair<Int, Boolean>, Void, Boolean>() {
        override fun doInBackground(vararg params: Pair<Int, Boolean>): Boolean {
            val (drinkId, isFavorite) = params[0]

            return try {
                val dbHelper = StarbuzzDatabaseHelper(this@DrinkActivity)
                val db = dbHelper.writableDatabase

                val values = android.content.ContentValues().apply {
                    put(StarbuzzDatabaseHelper.COLUMN_FAVORITE, if (isFavorite) 1 else 0)
                }

                db.update(
                    StarbuzzDatabaseHelper.TABLE_DRINK,
                    values,
                    "${StarbuzzDatabaseHelper.COLUMN_ID} = ?",
                    arrayOf(drinkId.toString())
                )

                db.close()
                true
            } catch (e: Exception) {
                false
            }
        }

        override fun onPostExecute(success: Boolean) {
            if (!success) {
                Toast.makeText(
                    this@DrinkActivity,
                    "Error al actualizar favoritos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_DRINKID, currentDrinkId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentDrinkId = savedInstanceState.getInt(EXTRA_DRINKID)
        loadDrinkDetails(currentDrinkId)
    }
}