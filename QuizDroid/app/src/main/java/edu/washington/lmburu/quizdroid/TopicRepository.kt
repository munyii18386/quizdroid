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
            listOf("4", "3", "5", "6"),
            listOf("4", "8", "5", "6"),
            listOf("1", "10", "9", "5"),
            listOf("16", "12", "14", "10"),
            listOf("20", "22", "23", "25")
        )


        //    val pAnswers = arrayListOf(
//        listOf("glass", "plastic", "clear", "convex"),
//        listOf("none", "small", "low", "high"),
//        listOf("scientist", "magician", "physicist", "teacher")
//    )
//    val mshAnswers = arrayListOf(
//        listOf(
//            "Tony Stark", "Some dude", "Patrick Star", "Johhny Blaze"
//        ), listOf("Both", "Neither", "Thor", "Hulk"), listOf("Yes", "No", "Maybe", "Only if Ben Affleck is fired")
//    )
        val mshAnswers = arrayOf(
            "Tony Stark", "Some dude", "Patrick Star", "Johhny Blaze"
        )
        val mathTopic = Topic(quizList[0], topicList[0], mQuestions)
        val physicsTopic = Topic(quizList[1], topicList[1], pQuestions)
        val mshTopics = Topic(quizList[2], topicList[2], mshQuestions)
        //    val mathQuiz = Question(mQuestions[0], mAnswers[0], 1)
//    val physicQuiz = Question(pQuestions[0], pAnswers[0], 4)
        val mshQuiz = Question(mshQuestions[0], mshAnswers, 1)

}



    fun getTopics(): List<String>{
        return quizList
    }

    fun currentTopic(name: String){
        currentTopic = name

    }
    fun getCurrentTopicName(): String{
        return currentTopic
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