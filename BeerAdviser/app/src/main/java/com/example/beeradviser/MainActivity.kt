package com.example.beeradviser

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var colorSpinner: Spinner
    private lateinit var findBeerButton: Button
    private lateinit var beerRecommendationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vistas
        colorSpinner = findViewById(R.id.colorSpinner)
        findBeerButton = findViewById(R.id.findBeerButton)
        beerRecommendationTextView = findViewById(R.id.beerRecommendationTextView)

        // spinner
        val colors = arrayOf("Light", "Amber", "Brown", "Dark")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSpinner.adapter = adapter

        // Boton
        findBeerButton.setOnClickListener {
            val selectedColor = colorSpinner.selectedItem as String
            val beerList = getBeers(selectedColor)
            beerRecommendationTextView.text = beerList.joinToString("\n")
        }
    }

    // Cervezas
    private fun getBeers(color: String): List<String> {
        return when (color) {
            "Light" -> listOf("Jail Pale Ale", "Lager Lite")
            "Amber" -> listOf("Jack Amber", "Red Moose")
            "Brown" -> listOf("Brown Bear Beer", "Bock Brownie")
            "Dark" -> listOf("Dark Lager", "Dark Ale")
            else -> listOf("No beer found")
        }
    }
}