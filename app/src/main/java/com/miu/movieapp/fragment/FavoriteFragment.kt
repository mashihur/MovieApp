package com.miu.movieapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miu.movieapp.R
import com.miu.movieapp.databinding.FragmentFavoriteBinding
import com.miu.movieapp.databinding.FragmentMovieBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : BaseFragment() {
    lateinit var binding : FragmentFavoriteBinding

    override fun onCreateView(): View {
        binding = FragmentFavoriteBinding.bind(rootView)
        return binding.root
    }

    override fun getLayout(): Int {
        return R.layout.fragment_favorite
    }

}