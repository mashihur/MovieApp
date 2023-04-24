package com.miu.movieapp.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.movieapp.Graph
import com.miu.movieapp.R
import com.miu.movieapp.adapter.CategoryMovieAdapter
import com.miu.movieapp.databinding.FragmentMovieBinding
import com.miu.movieapp.other.viewModelProviderFactoryOf
import com.miu.movieapp.viewmodel.MovieViewModel

class MovieFragment : BaseFragment() {
    lateinit var binding: FragmentMovieBinding

    private lateinit var movieCategoryAdapter: CategoryMovieAdapter

    private val viewModel: MovieViewModel by viewModels {
        viewModelProviderFactoryOf { MovieViewModel(Graph.movieRepository) }
    }

    override fun onCreateView(): View {
        binding = FragmentMovieBinding.bind(rootView)
        return binding.root
    }

    override fun getLayout(): Int {
        return R.layout.fragment_movie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieCategoryAdapter = CategoryMovieAdapter(
            onMovieClick = {
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvMovie.adapter = movieCategoryAdapter
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        viewModel.categoryMovies.observe(viewLifecycleOwner) {
            movieCategoryAdapter.submitList(it)
        }
    }
}