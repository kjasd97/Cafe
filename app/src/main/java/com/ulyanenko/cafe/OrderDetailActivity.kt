package com.ulyanenko.cafe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class OrderDetailActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewDrink: TextView
    private lateinit var textViewDrinkType: TextView
    private lateinit var textViewAdditives: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        val textViewName: TextView = findViewById(R.id.textViewName)
        textViewName.text = intent.getStringExtra("name")

        val textViewDrink: TextView = findViewById(R.id.textViewDrink)
        textViewDrink.text = intent.getStringExtra("drink")

        val textViewDrinkType: TextView = findViewById(R.id.textViewDrinkType)
        textViewDrinkType.text = intent.getStringExtra("drinkType")

        val textViewAdditives: TextView = findViewById(R.id.textViewAdditives)
        textViewAdditives.text = intent.getStringExtra("additives")

    }

    companion object {
        private const val NAME = "name"

        fun newIntent(
            context: Context,
            name: String,
            drink: String,
            drinkType: String,
            additives: String
        ): Intent {
            val intent = Intent(context, OrderDetailActivity::class.java)
            intent.putExtra(NAME, name)
            intent.putExtra("drink", drink)
            intent.putExtra("drinkType", drinkType)
            intent.putExtra("additives", additives)
            return intent
        }
    }

}