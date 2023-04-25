package com.miu.movieapp.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.MediaController
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.databinding.ActivityMovieDetailBinding
import com.miu.movieapp.other.Graph
import com.miu.movieapp.other.viewModelProviderFactoryOf
import com.miu.movieapp.ui.viewmodel.MovieDetailViewModel
import com.miu.movieapp.ui.viewmodel.MovieViewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels {
        viewModelProviderFactoryOf { MovieDetailViewModel(Graph.movieRepository) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<MovieEntity>("movie")
        viewModel.updateMovie(movie!!)


        viewModel.getTrailerVideos(movie?.id ?: 0)

        binding.videoView.setVideoPath("https://www.demonuts.com/Demonuts/smallvideo.mp4")
        var mediaController = MediaController(this)
        binding.videoView.setMediaController(mediaController)
        binding.title.text = viewModel.getTitle()
        binding.description.text = viewModel.getDesc()

        binding.listview.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayOf("1", "2"))


    }
}