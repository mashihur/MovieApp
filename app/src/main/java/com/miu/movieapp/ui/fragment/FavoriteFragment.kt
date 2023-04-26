package com.miu.movieapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.movieapp.R
import com.miu.movieapp.data.LocalMovieRepository
import com.miu.movieapp.data.local.MovieDatabase
import com.miu.movieapp.data.local.MovieItem
import com.miu.movieapp.databinding.FragmentFavoriteBinding
import com.miu.movieapp.other.viewModelProviderFactoryOf
import com.miu.movieapp.ui.adapter.FavoriteAdapter
import com.miu.movieapp.ui.viewmodel.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : BaseFragment() {
    lateinit var binding : FragmentFavoriteBinding
//    var array = arrayOf("").toList()

    private val viewModel: FavoriteViewModel by viewModels {
        viewModelProviderFactoryOf { FavoriteViewModel(LocalMovieRepository(MovieDatabase.invoke(this.rootView.context).getMovieDao())) }
    }

    override fun onCreateView(): View {
        binding = FragmentFavoriteBinding.bind(rootView)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onResume() {
        super.onResume()
        reloadData()
    }
////
    fun reloadData() {
        launch {
            context?.let{
                val movies = MovieDatabase(it).getMovieDao().getAllMovieItems() as MutableList<MovieItem>
//                val movies = viewModel.getAllFavoriteMovies() as MutableList<MovieItem>
                binding.rcvFav.adapter =
                    activity?.applicationContext?.let { it1 -> FavoriteAdapter({
                        viewModel.deleteMovieItem(it)
                    },it1, movies) }
                binding.rcvFav.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_favorite
    }

}