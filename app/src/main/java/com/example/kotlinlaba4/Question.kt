package com.example.kotlinlaba4

class Question(val question: String, val decision: Boolean) {

    fun GetDecision(answer: Boolean): Boolean {
        if (answer == decision){
            return true
        }
        return false
    }

}