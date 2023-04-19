package com.miu.movieapp.fragment

import android.content.DialogInterface
import android.net.Uri
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.miu.movieapp.R
import com.miu.movieapp.databinding.FragmentGameBinding
import com.miu.movieapp.other.Constants
import com.miu.movieapp.other.GameState
import com.miu.movieapp.other.HangManHelper
import java.util.*

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
    private val gameHelper = HangManHelper()

    override fun onCreateView(): View {
        binding = FragmentGameBinding.bind(rootView)
        initCommon()
        return binding.root
    }

    fun initCommon() {
        binding.btnStartNew.setOnClickListener {
            startNewGame()
        }

        var gameState = gameHelper.startNewGame()
        updateUI(gameState)

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
                playVideo(gameHelper.drawableVideo)
            }
            is GameState.Won -> showGameWon(gameState.wordToGuess)
        }
    }

    private fun playVideo(res: Int) {
        if (binding.videoplayer.isPlaying.not()) {
            binding.videoplayer.setVideoURI(Uri.parse("android.resource://${context?.packageName}/${res}"))
            binding.videoplayer.start()
            timer()
        }
    }

    //below function to play trailer only 10 seconds
    private fun timer() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                try {
                    val seconds = (binding.videoplayer.currentPosition % (1000 * 60 * 60) % (1000 * 60) / 1000) as Int
                    if (seconds > Constants.trailerSeconds) {
                        //binding.videoplayer.stopPlayback()
                        binding.videoplayer.seekTo(0)
                        binding.videoplayer.start()
                    }

                } catch (e: Exception) {
                }
            }
        }, 0, 1000)
    }

    private fun showGameLost(wordToGuess: String) {
        showAlert("Unfortunately!!!" , "You Lost")
        binding.videoplayer.stopPlayback()
        binding.userinput.text = wordToGuess
        binding.lettersLayout.visibility = View.GONE
    }

    private fun showGameWon(wordToGuess: String) {
        showAlert("Hooray!!!" , "You won")
        binding.videoplayer.stopPlayback()
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
        binding.videoplayer.stopPlayback()
        val gameState = gameHelper.startNewGame()
        binding.lettersLayout.visibility = View.VISIBLE
        binding.lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        updateUI(gameState)
    }

    override fun onResume() {
        super.onResume()
        binding.videoplayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoplayer.stopPlayback()
    }
}