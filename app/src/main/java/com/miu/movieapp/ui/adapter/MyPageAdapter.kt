package com.miu.movieapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miu.movieapp.ui.fragment.FavoriteFragment
import com.miu.movieapp.ui.fragment.GameFragment
import com.miu.movieapp.ui.fragment.MovieFragment
import com.miu.movieapp.ui.fragment.SciFragment

class MyPageAdapter(fActivity : FragmentActivity) : FragmentStateAdapter(fActivity) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MovieFragment()
            1 -> FavoriteFragment()
            2 -> GameFragment()
            3 -> SciFragment()
//            3 -> HelpFragment()
            else -> Fragment()

        }
    }

}