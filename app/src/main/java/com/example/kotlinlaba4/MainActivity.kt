package com.example.kotlinlaba4

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val numberOfQuestion = findViewById<TextView>(R.id.numberOfQuestion)
        val textOfQuestion = findViewById<TextView>(R.id.textOfQuestion)
        val buttonFalse = findViewById<Button>(R.id.buttonFalse)
        val buttonTrue = findViewById<Button>(R.id.buttonTrue)
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        val cheatButton = findViewById<Button>(R.id.buttonCheat)


        cheatButton.setOnClickListener { // Начало CheatActivity
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivity(intent)

        }

        numberOfQuestion.text = quizViewModel.countOfQuestion.toString() + " / " + quizViewModel.questionList.count()
        textOfQuestion.text = quizViewModel.currentQuestionText

        SetButtonsColor(buttonTrue, buttonFalse, buttonNext, quizViewModel.userAnswer)

        buttonFalse.setOnClickListener(){
            quizViewModel.userAnswer = "false"
            SetButtonsColor(buttonTrue, buttonFalse, buttonNext, quizViewModel.userAnswer)
            SetAnswer(false, buttonFalse)
        }

        buttonTrue.setOnClickListener(){
            quizViewModel.userAnswer = "true"
            SetButtonsColor(buttonTrue, buttonFalse, buttonNext, quizViewModel.userAnswer)
            SetAnswer(true, buttonTrue)
        }

        buttonNext.setOnClickListener(){

            quizViewModel.userAnswer = "nothing"
            SetButtonsColor(buttonTrue, buttonFalse, buttonNext, quizViewModel.userAnswer)

            GetNewQuestion(numberOfQuestion, textOfQuestion)
        }
    }

    fun SetButtonsColor(buttonTrue: Button, buttonFalse: Button, buttonNext: Button, userAnswer: String): Unit {


        buttonNext.setBackgroundColor(getResources().getColor(R.color.neutralButton))
        buttonTrue.setBackgroundColor(getResources().getColor(R.color.neutralButton))
        buttonFalse.setBackgroundColor(getResources().getColor(R.color.neutralButton))

        if (userAnswer == "nothing"){
            buttonTrue.isEnabled = true
            buttonFalse.isEnabled = true
            buttonNext.isEnabled = false

            if (quizViewModel.countOfQuestion == quizViewModel.questionList.count()){
                buttonTrue.isEnabled = false
                buttonFalse.isEnabled = false
                buttonNext.isVisible = false
            }
        }
        else if(userAnswer == "true"){
            if (quizViewModel.questionList[quizViewModel.countOfQuestion - 1].GetDecision(userAnswer.toBoolean())){
                buttonTrue.setBackgroundColor(getResources().getColor(R.color.rightButton))
            }
            else{
                buttonTrue.setBackgroundColor(getResources().getColor(R.color.incorrectButton))
            }
            buttonTrue.isEnabled = false
            buttonFalse.isEnabled = false
            buttonNext.isEnabled = true
        }
        else if(userAnswer == "false"){
            if (quizViewModel.questionList[quizViewModel.countOfQuestion - 1].GetDecision(userAnswer.toBoolean())){
                buttonFalse.setBackgroundColor(getResources().getColor(R.color.rightButton))
            }
            else{
                buttonFalse.setBackgroundColor(getResources().getColor(R.color.incorrectButton))
            }
            buttonFalse.isEnabled = false
            buttonTrue.isEnabled = false
            buttonNext.isEnabled = true
        }
    }

    fun SetAnswer(answer: Boolean, button: Button): Unit {

        if (quizViewModel.questionList[quizViewModel.countOfQuestion - 1].GetDecision(answer)){
            quizViewModel.countRightAnswer++
        }
    }

    fun GetNewQuestion(numberOfQuestion: TextView, textOfQuestion: TextView): Unit {
        if (quizViewModel.countOfQuestion < quizViewModel.questionList.count()){
            quizViewModel.countOfQuestion++
            numberOfQuestion.text = quizViewModel.countOfQuestion.toString() + " / " + quizViewModel.questionList.count()
            textOfQuestion.text = quizViewModel.currentQuestionText
        }
        else{
            textOfQuestion.text = "Верных ответов - " + quizViewModel.countRightAnswer.toString()
            textOfQuestion.setTextSize(COMPLEX_UNIT_SP, 26F)
        }
    }
}

