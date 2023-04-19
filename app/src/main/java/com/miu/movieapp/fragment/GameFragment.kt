package com.miu.movieapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miu.movieapp.R
import com.miu.movieapp.databinding.FragmentGameBinding
import com.miu.movieapp.databinding.FragmentMovieBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : BaseFragment() {
    lateinit var binding : FragmentGameBinding

    override fun onCreateView(): View {
        binding = FragmentGameBinding.bind(rootView)
        return binding.root
    }

    override fun getLayout(): Int {
        return R.layout.fragment_game
    }
}