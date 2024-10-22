package com.example.kotlinlaba4

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //button.setOnClickListener(){}

        //val editString = findViewById<TextView>(R.id.editString)

        val numberOfQuestion = findViewById<TextView>(R.id.numberOfQuestion)
        val textOfQuestion = findViewById<TextView>(R.id.textOfQuestion)
        val buttonFalse = findViewById<Button>(R.id.buttonFalse)
        val buttonTrue = findViewById<Button>(R.id.buttonTrue)
        val buttonNext = findViewById<Button>(R.id.buttonNext)

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

        //buttonFalse.setBackgroundColor(Color.BLUE)
        buttonFalse.setBackgroundColor(getResources().getColor(R.color.neutralButton))
        buttonTrue.setBackgroundColor(getResources().getColor(R.color.neutralButton))
        buttonNext.setBackgroundColor(getResources().getColor(R.color.neutralButton))

        buttonFalse.setOnClickListener(){
            buttonFalse.setBackgroundColor(getResources().getColor(R.color.rightButton))
            buttonTrue.setBackgroundColor(getResources().getColor(R.color.neutralButton))
        }

        buttonTrue.setOnClickListener(){
            buttonFalse.setBackgroundColor(getResources().getColor(R.color.neutralButton))
            buttonTrue.setBackgroundColor(getResources().getColor(R.color.rightButton))
        }

        buttonNext.setOnClickListener(){
            buttonFalse.setBackgroundColor(getResources().getColor(R.color.neutralButton))
            buttonTrue.setBackgroundColor(getResources().getColor(R.color.neutralButton))
        }
    }
}

class Question(val question: String, val decision: Boolean) {

    fun GetDecision(answer: Boolean): Boolean {
        if (answer == decision){
            return true
        }
        return false
    }

}