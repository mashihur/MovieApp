package com.miu.movieapp.fragment

import android.view.View
import com.miu.movieapp.R
import com.miu.movieapp.databinding.FragmentMovieBinding


class MovieFragment : BaseFragment() {
    lateinit var binding : FragmentMovieBinding

    override fun onCreateView(): View {
        binding = FragmentMovieBinding.bind(rootView)
        return binding.root
    }

    override fun getLayout(): Int {
        return R.layout.fragment_movie
    }


}