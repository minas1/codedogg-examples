package com.minasmina.daggerplayground.userdetails

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.minasmina.daggerplayground.DaggerApp
import com.minasmina.daggerplayground.R
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MovieViewModel.Factory

    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (application as DaggerApp).appComponent.inject(this)
        val id = intent.getIntExtra("id", 0)

        viewModel = ViewModelProvider(this, MovieViewModel.factory(viewModelFactory, id)).get(
            MovieViewModel::class.java
        )

        val userInfoView = findViewById<TextView>(R.id.user_info)
        viewModel.movie.observe(this) { title ->
            userInfoView.text = "Movie $id\'s title is $title"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}