package com.minasmina.daggerplayground

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.minasmina.daggerplayground.userdetails.MovieActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieIdField = findViewById<EditText>(R.id.movie_id)

        val viewUserButton = findViewById<Button>(R.id.view_movie)
        viewUserButton.setOnClickListener {
            try {
                val id = Integer.parseInt(movieIdField.text.toString())
                val intent = Intent(this, MovieActivity::class.java)
                    .putExtra("id", id)
                startActivity(intent)
            } catch (e: NumberFormatException) {
                // Do nothing
            }
        }
    }
}