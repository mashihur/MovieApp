package com.miu.movieapp.ui.activity

import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<MovieEntity>("movie")
        println(movie)
        binding.videoView.setVideoPath("https://www.demonuts.com/Demonuts/smallvideo.mp4")
        var mediaController = MediaController(this)
        binding.videoView.setMediaController(mediaController)
    }
}