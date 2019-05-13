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

//    companion object{
//        private var instance: Topic? = null
//            fun getInstance():Topic {
//                if (instance == null) {
//                    instance = Topic(title: String, shortDescription: String, questions: MutableList<String>)
//                }
//                return instance as Topic(title: String, shortDescription: String, questions: MutableList<String>)
//            }
//
//    }


    fun addQuestions(question:String) {
        questions?.add(question)
    }

    fun setTopic(title: String) {
        this.topic = title
    }

    fun setShortDescription(shortDescription: String) {
        this.desc = shortDescription
    }

    fun setQuestions(questions: MutableList<String>){
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