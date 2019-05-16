package edu.washington.lmburu.quizdroid



class Topic(topic:String, description: String, listOfQuestions: ArrayList<Question>) {



    private var subject  = topic
    private var desc = description
    private var questions = listOfQuestions


    fun getQuestions(): ArrayList<Question> {
        return questions
    }

    fun getDesc(): String {
        return desc!!
    }

    fun getTopic(): String {
        return subject
    }

}