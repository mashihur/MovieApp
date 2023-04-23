package com.miu.movieapp.fragment

import android.content.DialogInterface
import android.net.Uri
import android.view.View
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.miu.movieapp.R
import com.miu.movieapp.databinding.FragmentGameBinding
import com.miu.movieapp.other.GameState
import com.miu.movieapp.other.HangManHelper


class GameFragment : BaseFragment() {
    lateinit var binding : FragmentGameBinding
    private val gameHelper = HangManHelper()

    override fun onCreateView(): View {
        binding = FragmentGameBinding.bind(rootView)
        initCommon()
        return binding.root
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
                    val gameState = gameHelper.play((letterView).text[0])
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
        binding.webview.loadUrl("javascript:endVideo();");
        binding.userinput.text = wordToGuess
        binding.lettersLayout.visibility = View.GONE
    }

    private fun showGameWon(wordToGuess: String) {
        showAlert("Hooray!!!" , "You won")
        binding.webview.loadUrl("javascript:endVideo();");
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

    private fun startNewGame() {
        //binding.videoplayer.stopPlayback()
        val gameState = gameHelper.startNewGame()
        binding.lettersLayout.visibility = View.VISIBLE
        binding.lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        val uri = Uri.parse(gameHelper.drawableVideo)
        val youtubeid = uri.getQueryParameter("v") ?: "-"
        binding.webview.loadUrl("about:blank")
        binding.webview.loadUrl("file:///android_asset/index.html?v=$youtubeid")
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