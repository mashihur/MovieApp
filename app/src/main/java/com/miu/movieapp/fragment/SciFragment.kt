package com.miu.movieapp.fragment


import android.content.DialogInterface
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.miu.movieapp.R
import com.miu.movieapp.databinding.FragmentSciBinding
import com.miu.movieapp.other.Constants
import com.miu.movieapp.other.GameState
import com.miu.movieapp.other.HangManHelper
import java.util.*
import kotlin.random.Random


/**
 * A simple [Fragment] subclass.
 * Use the [SciFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SciFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding : FragmentSciBinding
    private val gameHelper = HangManHelper()
    var principleNames = mutableListOf("")
    var principleAns = mutableListOf("")
    var currentIndex = 0


    override fun onCreateView(): View {
        binding = FragmentSciBinding.bind(rootView)
        initCommon()
        return binding.root
    }

    override fun getLayout(): Int {
        return R.layout.fragment_sci
    }

    fun initCommon() {

        principleNames = requireContext().resources.getStringArray(R.array.principles).toMutableList()
        principleAns = requireContext().resources.getStringArray(R.array.principlesAnswers).toMutableList()

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
        principleAns.removeAt(currentIndex)
        binding.userinput.text = wordToGuess
        binding.lettersLayout.visibility = View.GONE
    }

    private fun showGameWon(wordToGuess: String) {
        showAlert("Hooray!!!" , "You won")
        principleAns.removeAt(currentIndex)
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
        currentIndex = Random.nextInt(0, principleAns.size)
        val gameState = gameHelper.startSCIGame(principleAns[currentIndex])
        binding.hintWord.text = principleNames[currentIndex]
        binding.lettersLayout.visibility = View.VISIBLE
        binding.lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        updateUI(gameState)
    }
}