package edu.washington.lmburu.quizdroid

class Question {
    private var questionText: String
    private var correctAns: Int
    private var answers: Array<String>

    constructor(questionText: String, answers: Array<String>, correctAns: Int ){
        this.questionText = questionText
        this.answers = answers
        this.correctAns = correctAns
    }

    fun setCorrectAns(correctAns: Int) {
        this.correctAns = correctAns
    }

    fun setQuestionText(questionText: String) {
        this.questionText = questionText
    }

    fun setAnswers(answers: Array<String>) {
        this.answers = answers
    }

    fun getQuestion(): String {
        return questionText
    }

    fun getAnswers(): Array<String> {
        return answers
    }

    fun getCorrectAns(): Int {
        return correctAns
    }
}