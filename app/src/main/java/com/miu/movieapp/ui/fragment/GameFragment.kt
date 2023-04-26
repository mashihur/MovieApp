package com.miu.movieapp.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.miu.movieapp.R
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.databinding.FragmentGameBinding
import com.miu.movieapp.other.GameState
import com.miu.movieapp.other.Graph
import com.miu.movieapp.ui.viewmodel.HangManHelper
import com.miu.movieapp.other.viewModelProviderFactoryOf
import com.miu.movieapp.ui.viewmodel.MovieViewModel


class GameFragment : BaseFragment() {
    lateinit var binding: FragmentGameBinding
    var movieList: ArrayList<MovieEntity>? = null

    private val viewModel: HangManHelper by viewModels {
        viewModelProviderFactoryOf { HangManHelper() }
    }

    private val movieViewModel by activityViewModels<MovieViewModel> {
        viewModelProviderFactoryOf { MovieViewModel(Graph.movieRepository) }
    }

    override fun onCreateView(): View {
        binding = FragmentGameBinding.bind(rootView)
        initCommon()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.movieEntities.observe(viewLifecycleOwner) {
            // TODO
            if (movieList == null) {
                movieList = ArrayList(it)
                loadNewMovie()
            }
        }
    }

    fun initCommon() {
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = WebViewClient()
        binding.webview.settings.mediaPlaybackRequiresUserGesture = false

        binding.btnStartNew.setOnClickListener {
            startNewGame()
        }

        startNewGame()
        binding.lettersLayout.children.forEach { letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener {
                    val gameState = viewModel.play((letterView).text[0])
                    updateUI(gameState)
                    letterView.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_game
    }

    private fun updateUI(gameState: GameState) {
        when (gameState) {
            is GameState.Lost -> showGameLost(gameState.wordToGuess)
            is GameState.Running -> {
                binding.userinput.text = gameState.underscoreWord
                binding.imageview.setImageResource(gameState.drawable)
            }
            is GameState.Won -> showGameWon(gameState.wordToGuess)
        }
    }

    private fun showGameLost(wordToGuess: String) {
        showAlert("Unfortunately!!!" , "You Lost")
        binding.webview.loadUrl("javascript:endVideo();")
        binding.userinput.text = wordToGuess
        binding.lettersLayout.visibility = View.GONE
    }

    private fun showGameWon(wordToGuess: String) {
        showAlert("Hooray!!!" , "You won")
        viewModel.removeCurrentVideo()
        binding.webview.loadUrl("javascript:endVideo();")
        binding.userinput.text = wordToGuess
        binding.lettersLayout.visibility = View.GONE
    }

    private fun showAlert(title: String, msg: String) {
        context?.let {
            val alert = AlertDialog.Builder(it)
            alert.setTitle(title)
            alert.setMessage(msg)
            alert.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                println("ok clicked")
            })
            alert.show()
        }
    }

    fun loadNewMovie() {
        if (movieList != null) {
            movieList?.first().let {
                viewModel.getTrailerVideos(it?.originalTitle ?: "null", it?.id ?: 0)
                movieList?.remove(it)
            }
        }
    }

    private fun startNewGame() {
        //binding.videoplayer.stopPlayback()
        loadNewMovie()


        val gameState = viewModel.startNewGame()
        binding.lettersLayout.visibility = View.VISIBLE
        binding.lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        binding.webview.loadUrl("about:blank")
        binding.webview.loadUrl(viewModel.getWebViewLink())
        updateUI(gameState)
    }

    override fun onResume() {
        super.onResume()
        binding.webview.onResume()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) binding.webview.onResume()
        else binding.webview.onPause()

    }

    override fun onPause() {
        super.onPause()
        binding.webview.onPause()
    }
}