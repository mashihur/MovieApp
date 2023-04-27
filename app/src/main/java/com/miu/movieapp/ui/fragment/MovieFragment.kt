package com.miu.movieapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.movieapp.R
import com.miu.movieapp.databinding.FragmentMovieBinding
import com.miu.movieapp.other.Graph
import com.miu.movieapp.other.toastShort
import com.miu.movieapp.other.viewModelProviderFactoryOf
import com.miu.movieapp.ui.activity.MovieDetailActivity
import com.miu.movieapp.ui.adapter.CategoryMovieAdapter
import com.miu.movieapp.ui.viewmodel.MovieViewModel

class MovieFragment : BaseFragment() {
    private lateinit var binding: FragmentMovieBinding

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
                val intent = Intent(activity, MovieDetailActivity::class.java)
                intent.putExtra("movie", it)
                startActivity(intent)
            }
        )
        binding.rvMovie.adapter = movieCategoryAdapter
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        viewModel.categoryMovies.observe(viewLifecycleOwner, movieCategoryAdapter::submitList)
        viewModel.error.observe(viewLifecycleOwner) {
            requireContext().toastShort(getString(R.string.load_movies_error))
        }
    }
}