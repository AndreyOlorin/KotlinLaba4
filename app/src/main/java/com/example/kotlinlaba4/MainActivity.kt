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

    //val question1: Question = Question("Самая большая пустыня в мире — это Сахара", false)
    //val question2: Question = Question("Нил — самая длинная река в мире", false)
    //val question3: Question = Question("В Антарктиде нет постоянного населения", true)
    //val question4: Question = Question("В Европе нет ни одной страны, где официальным языком является арабский", false)
    //val question5: Question = Question("Индийский океан является третьим по величине океаном на Земле", true)
    //val question6: Question = Question("В Бразилии находится крупнейший тропический лес в мире — Амазонка", true)
    //val question7: Question = Question("Африка — это единственный континент, через который проходит экватор", false)
    //val question8: Question = Question("Вторая по площади страна - Канада", true)
    //val question9: Question = Question("Швейцария известна своими широкими пляжами и курортами на побережье моря", false)
    //val question10: Question = Question("Греция состоит из более чем 6000 островов", true)

    //val questionList = mutableListOf(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10)

    //var countOfQuestion = 0
    //var countRightAnswer = 0
    //private lateinit var trueButton: Button

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
        textOfQuestion.text = quizViewModel.questionList[quizViewModel.countOfQuestion - 1].question.toString()

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
            textOfQuestion.text = quizViewModel.questionList[quizViewModel.countOfQuestion - 1].question.toString()
        }
        else{
            textOfQuestion.text = "Верных ответов - " + quizViewModel.countRightAnswer.toString()
            textOfQuestion.setTextSize(COMPLEX_UNIT_SP, 26F)
        }
    }
}

