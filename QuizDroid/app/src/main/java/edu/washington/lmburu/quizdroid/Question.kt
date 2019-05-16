package edu.washington.lmburu.quizdroid

import kotlin.system.measureTimeMillis

class Question(questionText: String, answers: ArrayList<String>, correctAns: Int  ) {


    private var text = questionText
    private var ans = answers
    private var index = correctAns



        fun getQuestion(): String {
            return text
        }

        fun getAnswers(): ArrayList<String> {
            return ans
        }

        fun getCorrectAns(): String {
            return ans[index ]
        }
}