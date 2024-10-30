package com.example.kotlinlaba4

import android.util.Log
import androidx.lifecycle.ViewModel

//private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {

    var countOfQuestion = 1
    var countRightAnswer = 0
    var answer = "nothing"
}