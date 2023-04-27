package com.miu.movieapp.ui.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import com.google.android.gms.ads.AdRequest
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.movieapp.R
import com.miu.movieapp.databinding.ActivityMainBinding
import com.miu.movieapp.ui.adapter.MyPageAdapter


class MainActivity : BaseActivity(), SearchView.OnQueryTextListener {
    lateinit var binding: ActivityMainBinding



    override fun onCreateActivity() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyPageAdapter(this)
        binding.viewPager.adapter = adapter
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
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

                3 -> {
                    tab.text = resources.getString(R.string.sci)
                    tab.setIcon(R.drawable.baseline_game_24)
                }
            }
        }.attach()


        val adRequest: AdRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

//        val searchView = menu.findItem(R.id.menu_item_search).actionView as SearchView
//        searchView.queryHint = getString(R.string.search_movie)
//        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_search -> {
                val intent = Intent(this, SearchMovieActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // TODO
        return true
    }
}


