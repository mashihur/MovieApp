package com.miu.movieapp.ui.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.movieapp.databinding.ActivitySearchMovieBinding
import com.miu.movieapp.other.Graph
import com.miu.movieapp.other.viewModelProviderFactoryOf
import com.miu.movieapp.ui.adapter.SearchAdapter
import com.miu.movieapp.ui.viewmodel.SearchViewModel


class SearchMovieActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchMovieBinding

    private val viewModel: SearchViewModel by viewModels {
        viewModelProviderFactoryOf { SearchViewModel(Graph.movieRepository) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.movieVideos.observe(this, Observer {
            binding.listview.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            binding.listview.adapter = SearchAdapter(this, it) {

                var intent = Intent(this, MovieDetailActivity::class.java)
                intent.putExtra("movie", it)
                startActivity(intent)
            }
        })

        binding.search.requestFocus()
        viewModel.getMovies("batman")

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val text = (query ?: "").trim()
                if (text.isNotBlank()) {
                    viewModel.getMovies(text)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}