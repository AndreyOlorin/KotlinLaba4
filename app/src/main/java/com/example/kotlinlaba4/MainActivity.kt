package com.example.kotlinlaba4

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

class MainActivity : AppCompatActivity() {

    val question1: Question = Question("Самая большая пустыня в мире — это Сахара", false)
    val question2: Question = Question("Нил — самая длинная река в мире", false)
    val question3: Question = Question("В Антарктиде нет постоянного населения", true)
    val question4: Question = Question("В Европе нет ни одной страны, где официальным языком является арабский", false)
    val question5: Question = Question("Индийский океан является третьим по величине океаном на Земле", true)
    val question6: Question = Question("В Бразилии находится крупнейший тропический лес в мире — Амазонка", true)
    val question7: Question = Question("Африка — это единственный континент, через который проходит экватор", false)
    val question8: Question = Question("Вторая по площади страна - Канада", true)
    val question9: Question = Question("Швейцария известна своими широкими пляжами и курортами на побережье моря", false)
    val question10: Question = Question("Греция состоит из более чем 6000 островов", true)

    val questionList = mutableListOf(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10)

    var countOfQuestion = 0
    var countRightAnswer = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        val numberOfQuestion = findViewById<TextView>(R.id.numberOfQuestion)
        val textOfQuestion = findViewById<TextView>(R.id.textOfQuestion)
        val buttonFalse = findViewById<Button>(R.id.buttonFalse)
        val buttonTrue = findViewById<Button>(R.id.buttonTrue)
        val buttonNext = findViewById<Button>(R.id.buttonNext)

        GetNewQuestion(numberOfQuestion, textOfQuestion)

        buttonFalse.setBackgroundColor(getResources().getColor(R.color.neutralButton))
        buttonTrue.setBackgroundColor(getResources().getColor(R.color.neutralButton))
        buttonNext.setBackgroundColor(getResources().getColor(R.color.neutralButton))

        buttonNext.isClickable = false

        buttonFalse.setOnClickListener(){
            buttonTrue.isClickable = false
            buttonNext.isClickable = true
            SetAnswer(false, buttonFalse)
        }

        buttonTrue.setOnClickListener(){
            buttonFalse.isClickable = false
            buttonNext.isClickable = true
            SetAnswer(true, buttonTrue)
        }

        buttonNext.setOnClickListener(){

            buttonTrue.isClickable = true
            buttonFalse.isClickable = true
            buttonNext.isClickable = false

            if (countOfQuestion == questionList.count()){
                buttonTrue.isClickable = false
                buttonFalse.isClickable = false
                buttonNext.isVisible = false
            }

            buttonFalse.setBackgroundColor(getResources().getColor(R.color.neutralButton))
            buttonTrue.setBackgroundColor(getResources().getColor(R.color.neutralButton))
            GetNewQuestion(numberOfQuestion, textOfQuestion)
        }
    }

    fun SetAnswer(answer: Boolean, button: Button): Unit {

        if (questionList[countOfQuestion - 1].GetDecision(answer)){
            countRightAnswer++
            button.setBackgroundColor(getResources().getColor(R.color.rightButton))
        }
        else{
            button.setBackgroundColor(getResources().getColor(R.color.incorrectButton))
        }
    }

    fun GetNewQuestion(numberOfQuestion: TextView, textOfQuestion: TextView): Unit {
        if (countOfQuestion < questionList.count()){
            countOfQuestion++
            numberOfQuestion.text = countOfQuestion.toString() + " / " + questionList.count()
            textOfQuestion.text = questionList[countOfQuestion - 1].question.toString()
        }
        else{
            textOfQuestion.text = "Верных ответов - " + countRightAnswer.toString()
            textOfQuestion.setTextSize(COMPLEX_UNIT_SP, 26F)
        }
    }
}

