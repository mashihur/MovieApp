package com.miu.movieapp.ui.activity

import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.MediaController
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.databinding.ActivityMovieDetailBinding
import com.miu.movieapp.other.Graph
import com.miu.movieapp.other.viewModelProviderFactoryOf
import com.miu.movieapp.ui.adapter.VideoAdapter
import com.miu.movieapp.ui.viewmodel.MovieDetailViewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels {
        viewModelProviderFactoryOf { MovieDetailViewModel(Graph.movieRepository) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.videoView.settings.javaScriptEnabled = true
        binding.videoView.webViewClient = WebViewClient()
        binding.videoView.settings.mediaPlaybackRequiresUserGesture = false

        val movie = intent.getParcelableExtra<MovieEntity>("movie")
        viewModel.updateMovie(movie!!)


        viewModel.getTrailers(movie?.id ?: 0)

        viewModel.movieEntity.observe(this, Observer {
            binding.title.text = it?.title
            binding.description.text = it?.overview
            binding.ratingStar.numStars = 5
            binding.ratingStar.stepSize = 0.5f
            binding.ratingStar.rating = (movie.voteAverage * 0.5).toFloat()
        })

        viewModel.movieVideos.observe(this, Observer {
            it.first().let {
                binding.videoView.loadUrl("file:///android_asset/index.html?v=${it.key}")
            }


            binding.listview.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            binding.listview.adapter = VideoAdapter(this, it) {
                binding.videoView.loadUrl("file:///android_asset/index.html?v=${it.key}")
            }
        })
    }
}