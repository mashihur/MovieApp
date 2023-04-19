package com.miu.movieapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miu.movieapp.fragment.FavoriteFragment
import com.miu.movieapp.fragment.GameFragment
import com.miu.movieapp.fragment.MovieFragment

class MyPageAdapter(fActivity : FragmentActivity) : FragmentStateAdapter(fActivity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MovieFragment()
            1 -> FavoriteFragment()
            2 -> GameFragment()
//            3 -> HelpFragment()
            else -> Fragment()

        }
    }

}