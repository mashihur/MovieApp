package com.miu.movieapp.other

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.movieapp.ui.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.random.Random

class HangManHelper: ViewModel() {

    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private val maxTries = Constants.imgHangMan.size
    private var currentTries = 0
    private var drawable: Int = Constants.imgHangMan[0]
    private var selectedIndex = 0
    var drawableVideo: String = ""


    fun getWebViewLink(): String {
        val uri = Uri.parse(drawableVideo)
        val youtubeid = uri.getQueryParameter("v") ?: "-"
        return "file:///android_asset/index.html?v=$youtubeid"
    }

    fun startNewGame(): GameState {
        currentTries = 0
        drawable = Constants.imgHangMan[0]
        selectedIndex = Random.nextInt(0, Constants.movieNames.size)
        val nonAlphabet = "[^a-zA-Z]".toRegex()
        wordToGuess = Constants.movieNames[selectedIndex].replace(nonAlphabet, "")
        drawableVideo = Constants.videoNames[selectedIndex]
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

    fun removeCurrentVideo() {
        Constants.movieNames.removeAt(selectedIndex)
        Constants.videoNames.removeAt(selectedIndex)
    }

    fun getTrailerVideos(movieTitle: String, movieid: Int) {
        val repository = Graph.movieRepository
        var isFoundLink = false

        repository.getTrailerVideos(movieid)
            .onEach {
                for (item in it) {
                    if (isFoundLink.not() && item.site == "YouTube") {
                        Constants.movieNames += movieTitle
                        Constants.videoNames += "https://www.youtube.com/watch?v=${item.key}"
                        isFoundLink = true
                        break
                    }
                }
                println(it)
            }
            .catch {
                // TODO: handle error
                println(it)
            }
            .launchIn(viewModelScope)
    }
}