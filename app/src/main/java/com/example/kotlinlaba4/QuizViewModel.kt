package com.example.kotlinlaba4

import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {

    val numberOfQuestion = R.id.numberOfQuestion
    val textOfQuestion = R.id.textOfQuestion
    val buttonFalse = R.id.buttonFalse
    val buttonTrue = R.id.buttonTrue
    val buttonNext = R.id.buttonNext
}