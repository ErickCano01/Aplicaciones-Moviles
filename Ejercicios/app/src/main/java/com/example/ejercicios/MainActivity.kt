package com.example.ejercicios

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
//Si se quiere alternar entre ejercicios se tiene que quitar los // de los /* */ para que se puedan alternar entre unos
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Ejercicio 1
        /* Si quiere poner estos solo agregar //
        setContentView(R.layout.activity_main)
        val textViewMessage: TextView = findViewById(R.id.textViewMessage)
        val textViewTemperature: TextView = findViewById(R.id.textViewTemperature)
        val toggleButtonTemperature: ToggleButton = findViewById(R.id.toggleButtonTemperature)
        val checkboxMilk: CheckBox = findViewById(R.id.checkboxMilk)
        val checkboxSugar: CheckBox = findViewById(R.id.checkboxSugar)
        val checkboxLemon: CheckBox = findViewById(R.id.checkboxLemon)
        */ //agregar // si se quiere alternar

        // /* se puede remover para cambiar al ejercio 1
        //Ejercicio 2
        setContentView(R.layout.ejercicio2)
        val textViewQuestion: TextView = findViewById(R.id.textViewQuestion)
        val toggleButtonTemperature: ToggleButton = findViewById(R.id.toggleButtonTemperature)
        val checkboxMilk: CheckBox = findViewById(R.id.checkboxMilk)
        val checkboxSugar: CheckBox = findViewById(R.id.checkboxSugar)
        val checkboxLemon: CheckBox = findViewById(R.id.checkboxLemon)
        // */
    }
}