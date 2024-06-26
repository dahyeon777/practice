package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){

    private var _score=0
    private var currentWordCount = 0
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    private lateinit var _currentScrambledWord: String
    val currentScrambledWord : String
        get() = _currentScrambledWord

    val score: Int
        get() = _score

    private fun getNextWord(){
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)){
            tempWord.shuffle()
        }

        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++currentWordCount
            wordsList.add(currentWord)
        }
    }

    init{
        Log.d("GameFragment","GameViewModel created!")
        getNextWord()
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }



    override fun onCleared(){
        super.onCleared()
        Log.d("GameFragment","GameViewModel destroyed!")
    }

    fun nextWord():Boolean{
        return if (currentWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            true
        }else false
    }

}