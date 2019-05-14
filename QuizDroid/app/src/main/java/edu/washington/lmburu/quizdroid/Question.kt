package edu.washington.lmburu.quizdroid

class Question {
    private var questionText: String
    private var correctAns: Int
    private var answers:  ArrayList<String>

    constructor(questionText: String, answers: ArrayList<String>, correctAns: Int ){
        this.questionText = questionText
        this.answers = answers
        this.correctAns = correctAns
    }

//    fun setCorrectAns(correctAns: Int) {
//        this.correctAns = correctAns
//    }
//
//    fun setQuestionText(questionText: String) {
//        this.questionText = questionText
//    }
//
//    fun setAnswers(answers: ArrayList<String>) {
//        this.answers = answers.add(answers)
//    }

    fun getQuestion(): String {
        return questionText
    }

    fun getAnswers():  ArrayList<String> {
        return answers
    }

    fun getCorrectAns(): String {
        return answers[correctAns]
    }
}