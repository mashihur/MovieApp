package com.miu.movieapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import com.miu.movieapp.R
import com.miu.movieapp.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.videoView.setVideoPath("https://www.demonuts.com/Demonuts/smallvideo.mp4")
        var mediaController = MediaController(this)
        binding.videoView.setMediaController(mediaController)
    }
}