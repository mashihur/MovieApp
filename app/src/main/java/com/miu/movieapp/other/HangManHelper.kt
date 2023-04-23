package com.miu.movieapp.other

import com.miu.movieapp.R
import kotlin.random.Random

class HangManHelper {

    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private val maxTries = Constants.imgHangMan.size
    private var currentTries = 0
    private var drawable: Int = Constants.imgHangMan[0]
    var drawableVideo: String = ""

    fun startNewGame(): GameState {
        currentTries = 0
        drawable = Constants.imgHangMan[0]
        val randomIndex = Random.nextInt(0, Constants.movieNames.size)
        wordToGuess = Constants.movieNames[randomIndex].replace(" ", "")
        drawableVideo = Constants.videoNames[randomIndex]
        generateUnderscores(wordToGuess)
        return getGameState()
    }

    fun startSCIGame(word: String): GameState {
        currentTries = 0
        drawable = Constants.imgHangMan[0]
        wordToGuess = word
        generateUnderscores(wordToGuess)
        return getGameState()
    }

    private fun generateUnderscores(word: String) {
        val sb = StringBuilder()
        word.forEach { char ->
            if (char == '/') {
                sb.append('/')
            } else {
                sb.append("_")
            }
        }
        underscoreWord = sb.toString()
    }

    fun play(letter: Char): GameState {
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed { index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord // _ _ _ _ _ _ _ -> E _ _ _ _ _ _
        indexes.forEach { index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }

        if (indexes.isEmpty()) {
            currentTries++
        }

        underscoreWord = finalUnderscoreWord
        return getGameState()
    }

    private fun getHangmanDrawable(): Int {
        return Constants.imgHangMan[currentTries]
    }

    private fun getGameState(): GameState {
        if (underscoreWord.equals(wordToGuess, true)) {
            return GameState.Won(wordToGuess)
        }

        if (currentTries == maxTries) {
            return GameState.Lost(wordToGuess)
        }

        drawable = getHangmanDrawable()
        return GameState.Running(underscoreWord, drawable)
    }
}