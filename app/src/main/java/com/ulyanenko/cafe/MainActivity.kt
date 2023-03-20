package com.ulyanenko.cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        buttonSignIn.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.error_fields_empty),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                nextScreen(name)
            }
        }


    }

    private fun initViews (){
        editTextName= findViewById(R.id.editTextName)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonSignIn = findViewById(R.id.buttonSignIn)
    }

    private fun nextScreen(name: String) {
        val intent: Intent = MakeOrderActivity.newIntent(this, name)
        startActivity(intent)
    }
}