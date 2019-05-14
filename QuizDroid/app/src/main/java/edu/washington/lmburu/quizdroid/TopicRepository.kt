package edu.washington.lmburu.quizdroid

import android.util.Log



interface TopicRepository {





companion object : TopicRepository {
    private  var instance: TopicRepository? = null
    fun getInstance(): TopicRepository {
        if (instance == null) {
            instance = TopicRepository
        }
        return instance as TopicRepository
    }
        var currentTopic = ""
        var checkedAnswer = ""
        var tally = 0
        var currentPosition = 0
        var currentQuestionIndex = 0


        val quizList = listOf("Math", "Physics", "Marvel Super Heroes")
        val topicList = listOf(
            "This Math quiz contains 5 questions.",
            "This Physics quiz contains 3 questions. ",
            "This Marvel Super Heroes quiz contains 3 questions. "
        )
        val mQuestions = arrayListOf(
            "2 * 2 =", "4 * 2 =", "3 * 3 = ", "4 * 4 =", "5 * 5 ="
        )

        val pQuestions = arrayListOf(
            "A magnifying glass is what type of lens?",
            "Conductors have a high or low resistance?",
            "A person who studies physics is known as a?"
        )
        val mshQuestions = arrayListOf(
            "Who is Iron Man?", "Who is stronger Thor or Hulk?", "Can Batman join the team?"
        )
        val mAnswers = arrayListOf(
            arrayListOf("4", "3", "5", "6"),
            arrayListOf("4", "8", "5", "6"),
            arrayListOf("1", "10", "9", "5"),
            arrayListOf("16", "12", "14", "10"),
           arrayListOf("20", "22", "23", "25")
        )


            val pAnswers = arrayOf(
        arrayListOf("glass", "plastic", "clear", "convex"),
        arrayListOf("none", "small", "low", "high"),
        arrayListOf("scientist", "magician", "physicist", "teacher")
    )
    val mshAnswers = arrayListOf(
        arrayListOf(
            "Tony Stark", "Some dude", "Patrick Star", "Johhny Blaze"
        ), arrayListOf("Both", "Neither", "Thor", "Hulk"), arrayListOf("Yes", "No", "Maybe", "Only if Ben Affleck is fired")
    )

        val mathTopic = Topic(quizList[0], topicList[0], mQuestions)
        val physicsTopic = Topic(quizList[1], topicList[1], pQuestions)
        val mshTopics = Topic(quizList[2], topicList[2], mshQuestions)

        val mathQuiz = Question(mQuestions[currentQuestionIndex], mAnswers[currentQuestionIndex], 0)
        val physicQuiz = Question(pQuestions[currentQuestionIndex], pAnswers[currentQuestionIndex], 3)
        val mshQuiz = Question(mshQuestions[currentQuestionIndex], mshAnswers[currentQuestionIndex], 0)

}



    fun getTopics(): List<String>{
        return quizList
    }

    fun updateCurrentTopic(name: String){
        currentTopic = name
    }

    fun getCurrentTopicName(): String{
        var result = ""
        when(currentTopic){
            "Math" -> result = mathTopic.getTopic()
            "Physics" -> result = physicsTopic.getTopic()
            "Marvel Super Heroes" ->  result = mshTopics.getTopic()
        }
        return  result
    }

    fun topicDesc(currentTopic:String):String{
       var summary = ""
        when(currentTopic){
            "Math" -> summary = mathTopic.getDesc()
            "Physics" -> summary = physicsTopic.getDesc()
            "Marvel Super Heroes" -> summary = mshTopics.getDesc()
        }
        return  summary
    }

    fun getQuestion(name: String): String{
        var topic = ""
        when(currentTopic){
            "Math" -> topic = mathTopic.getQuestions()[currentQuestionIndex]
            "Physics" -> topic = physicsTopic.getQuestions()[currentQuestionIndex]
            "Marvel Super Heroes" -> topic = mshTopics.getQuestions()[currentQuestionIndex]
        }
        return topic
    }

    fun getQuestionIndex():Int{
        return currentQuestionIndex
    }

    fun updateQuestionIndex(num:Int){
        currentQuestionIndex = num
    }

    fun getAns(): ArrayList<String>{
        var result = arrayListOf("")
       when(currentTopic){
           "Math" -> result =  mathQuiz.getAnswers()
           "Physics" -> result = physicQuiz.getAnswers()
           "Marvel Super Heroes" -> mshQuiz.getAnswers()
       }
        return result
    }

    fun setUserCheckedAns(ans:String){
        checkedAnswer = ans
    }

    fun getUserCheckedAns(): String{
        return  checkedAnswer
    }

    fun getCorrectAns(): String{
        var result = ""
        when(currentTopic){
            "Math" -> result = mathQuiz.getCorrectAns()
            "Physics" -> result = physicQuiz.getCorrectAns()
            "Marvel Super Heroes" -> result = mshQuiz.getCorrectAns()

        }
        return result

    }

    fun getQuizSize():Int{
        var result = 0
        when(currentTopic){
            "Math" -> result = mathTopic.getQuestions().size
            "Physics" -> result = physicsTopic.getQuestions().size
            "Marvel Super Heroes" -> result = mshTopics.getQuestions().size

        }
        return result
    }

    fun getScore(): Int{
        return tally
    }

    fun updateScore(number:Int){
        tally = number
    }

    fun updatePosition(number: Int){
        currentPosition = number
    }

    fun getPosition():Int{
        return currentPosition
    }







//    fun setSubject(selectedSubject: String){
//        quizManager.setSubject(selectedSubject)
//    }
//
//    fun getSubject(): String{
//        return quizManager.sub
//    }
//
//    //get list of questions
//    fun getQuizOptions() = quizManager.quizList
//
//    //get desire topic summary
//    fun topicOverview(subject: String): String{
//        return quizManager.topicOverview(subject)
//    }
//
//    fun setIndex(index: Int){
//        quizManager.setIndex(index)
//    }
//    fun getIndex(): Int{
//        return quizManager.getIndex()
//    }
//
//    fun setTotalTally(num:Int){
//        quizManager.setTotalTally(num)
//    }
//
//    fun getTotalTally():Int{
//        return quizManager.getTotalTally()
//    }
//
//    fun getUserCheckedAnswer():String{
//        return quizManager.getUserCheckedAnswer()
//    }
//
//    fun setUserCheckedAnswer(ans: String){
//        quizManager.setUserCheckedAnswer(ans)
//    }
//
//    //get quiz list size
//    fun quizSize(subject: String): Int {
//        return quizManager.quizSize(subject)
//    }
//
//    //get list of questions per subject
//    fun getQuestion(subject: String, index: Int): String {
//        return quizManager.getQuestion(subject, index)
//    }
//    // get list of answers per question
//    fun getAnswer(subject: String, index: Int): List<String> {
//      return quizManager.getAnswer(subject, index)
//    }
//
//    //get the correct answer for corresponding question
//    fun correctAnswer(subject: String, index: Int): String {
//        return quizManager.correctAnswer(subject, index)
//    }


}