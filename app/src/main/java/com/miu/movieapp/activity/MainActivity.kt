package com.miu.movieapp.activity

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.movieapp.R
import com.miu.movieapp.adapter.MyPageAdapter
import com.miu.movieapp.databinding.ActivityMainBinding
import com.miu.movieapp.other.toastLong
import com.miu.movieapp.other.toastShort

class MainActivity : BaseActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreateActivity() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toastShort("hello")
        toastLong("Bye")

        val adapter = MyPageAdapter(this)
        binding.viewPager.adapter = adapter
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab,position ->
            when(position){
                0->{
                    tab.text = resources.getString(R.string.movie)
                    tab.setIcon(R.drawable.baseline_movie_24)
                }
                1->{
                    tab.text = resources.getString(R.string.favorite)
                    tab.setIcon(R.drawable.baseline_favorite_24)
                }
                2->{
                    tab.text = resources.getString(R.string.game)
                    tab.setIcon(R.drawable.baseline_game_24)
                }
            }
        }.attach()
    }

}


