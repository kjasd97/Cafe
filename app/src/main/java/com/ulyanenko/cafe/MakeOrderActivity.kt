package com.ulyanenko.cafe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.*
import android.widget.Spinner
import android.widget.TextView

class MakeOrderActivity : AppCompatActivity() {

    private lateinit var textViewGreetings: TextView
    private lateinit var textViewAdditives: TextView

    private lateinit var radioGroupDrinks: RadioGroup
    private lateinit var radioButtonTea: RadioButton
    private lateinit var radioButtonCoffee: RadioButton


    private lateinit var checkboxSugar: CheckBox
    private lateinit var checkboxMilk: CheckBox
    private lateinit var checkboxLemon: CheckBox

    private lateinit var spinnerTea: Spinner
    private lateinit var spinnerCoffee: Spinner

    private lateinit var buttonMakeOrder: Button

    private lateinit var drink: String
    private lateinit var name: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)
        initViews()

        setupUserName()

        radioGroupDrinks.setOnCheckedChangeListener(object : OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                if (p1 == radioButtonTea.id) {
                    onUserChooseTea()
                } else if (p1 == radioButtonCoffee.id) {
                    onUserChooseCoffee()
                }
            }
        })
        radioButtonTea.isChecked = true


        buttonMakeOrder.setOnClickListener(object:OnClickListener{
            override fun onClick(p0: View?) {
            userMakeOrder()
            }

        })

    }

    companion object {
        fun newIntent(context: Context, name: String): Intent {
            val intent: Intent = Intent(context, MakeOrderActivity::class.java)
            intent.putExtra("name", name)
            return intent
        }
    }

    private fun initViews() {
        textViewGreetings = findViewById(R.id.textViewGreetings)
        textViewAdditives = findViewById(R.id.textViewAdditives)
        radioGroupDrinks = findViewById(R.id.radioGroupDrinks)
        radioButtonTea = findViewById(R.id.radioButtonTea)
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee)
        checkboxSugar = findViewById(R.id.checkboxSugar)
        checkboxMilk = findViewById(R.id.checkboxMilk)
        checkboxLemon = findViewById(R.id.checkboxLemon)
        spinnerTea = findViewById(R.id.spinnerTea)
        spinnerCoffee = findViewById(R.id.spinnerCoffee)
        buttonMakeOrder = findViewById(R.id.buttonMakeOrder)
    }

    private fun setupUserName() {
        name = intent.getStringExtra("name").toString()
        val greetings = getString(R.string.greetings)
        val result = String.format(greetings, name)
        textViewGreetings.text = result
    }

    private fun onUserChooseTea() {
        drink = getString(R.string.tea)
        val additives = getString(R.string.additives, drink)
        textViewAdditives.text = additives
        checkboxLemon.visibility = VISIBLE
        spinnerCoffee.visibility = INVISIBLE
        spinnerTea.visibility = VISIBLE

    }

    private fun onUserChooseCoffee() {
        drink = getString(R.string.coffee)
        val additives = getString(R.string.additives, drink)
        textViewAdditives.text = additives
        checkboxLemon.visibility = INVISIBLE
        spinnerCoffee.visibility = VISIBLE
        spinnerTea.visibility = INVISIBLE
    }

    private fun userMakeOrder(){
    val additives = mutableListOf<String>()
        if(checkboxSugar.isChecked){
            additives.add(checkboxSugar.text.toString())
        }
        if(checkboxMilk.isChecked){
            additives.add(checkboxMilk.text.toString())
        }
        if(radioButtonTea.isChecked && checkboxLemon.isChecked){
            additives.add(checkboxLemon.text.toString())
        }

        var drinkType:String = ""
        if(radioButtonTea.isChecked){
            drinkType = spinnerTea.selectedItem.toString()
        }else if(radioButtonCoffee.isChecked){
            drinkType = spinnerCoffee.selectedItem.toString()
        }

        val intent = OrderDetailActivity.newIntent(this, name, drink,
            drinkType, additives.toString())
        startActivity(intent)
    }

}