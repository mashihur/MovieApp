package com.miu.movieapp.ui.fragment


import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.fragment.app.viewModels
import com.miu.movieapp.R
import com.miu.movieapp.databinding.FragmentSciBinding
import com.miu.movieapp.other.GameState
import com.miu.movieapp.other.HangManHelper
import com.miu.movieapp.other.viewModelProviderFactoryOf
import kotlin.random.Random


class SciFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding : FragmentSciBinding

    private val viewModel: HangManHelper by viewModels {
        viewModelProviderFactoryOf { HangManHelper() }
    }

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
                    val gameState = viewModel.play((letterView).text[0])
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
        val gameState = viewModel.startSCIGame(principleAns[currentIndex])
        binding.hintWord.text = principleNames[currentIndex]
        binding.lettersLayout.visibility = View.VISIBLE
        binding.lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        updateUI(gameState)
    }
}