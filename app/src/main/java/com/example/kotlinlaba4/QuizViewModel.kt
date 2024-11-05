package com.example.kotlinlaba4

import android.util.Log
import androidx.lifecycle.ViewModel

//private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {

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

    var countOfQuestion = 1
    var countRightAnswer = 0
    var userAnswer = "nothing"
    var isCheater = false

    val currentQuestionAnswer: Boolean get() = questionList[countOfQuestion - 1].decision
    val currentQuestionText: String get() = questionList[countOfQuestion - 1].question


}