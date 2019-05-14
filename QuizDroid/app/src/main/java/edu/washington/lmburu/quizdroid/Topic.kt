package edu.washington.lmburu.quizdroid



class Topic() {



    private var topic: String? = null
    private var desc: String? = null
    private var questions: MutableList<String>? = null

    constructor(title: String, shortDescription: String, questions: MutableList<String>) : this() {
        this.topic = title
        this.desc = shortDescription
        this.questions = questions
    }



    fun getQuestions(): MutableList<String> {
        return questions!!
    }

    fun getDesc(): String {
        return desc!!
    }


    fun getTopic(): String {
        return topic!!
    }
}