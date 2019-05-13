package edu.washington.lmburu.quizdroid

import android.app.Application
import android.util.Log

private val TAG = "QuizApp"

class QuizApp: Application(), TopicRepository {
    companion object{
        fun newInstance(): QuizApp{
            return QuizApp()
        }
    }
    val quizList = listOf("Math", "Physics", "Marvel Super Heroes")
    val topicList = listOf(
        "This Math quiz contains 5 questions.",
        "This Physics quiz contains 3 questions. ",
        "This Marvel Super Heroes quiz contains 3 questions. "
    )
    val mQuestions: HashMap<Int, String> = hashMapOf(
        1 to "2 * 2 =", 2 to "4 * 2 =",
        3 to "3 * 3 = ", 4 to "4 * 4 =", 5 to "5 * 5 ="
    )
    val pQuestions: HashMap<Int, String> = hashMapOf(
        1 to "A magnifying glass is what type of lens?",
        2 to "Conductors have a high or low resistance?",
        3 to "A person who studies physics is known as a?"
    )
    val mshQuestions: HashMap<Int, String> = hashMapOf(
        1 to "Who is Iron Man?",
        2 to "Who is stronger Thor or Hulk?", 3 to "Can Batman join the team?"
    )

    val mAnswers: HashMap<Int, List<String>> = hashMapOf(
        1 to listOf("4", "3", "5", "6"),
        2 to listOf("4", "8", "5", "6"), 3 to listOf("1", "10", "9", "5"), 4 to listOf("16", "12", "14", "10"),
        5 to listOf("20", "22", "23", "25")
    )

    val pAnswers: HashMap<Int, List<String>> = hashMapOf(
        1 to listOf("glass", "plastic", "clear", "convex"),
        2 to listOf("none", "small", "low", "high"), 3 to listOf("scientist", "magician", "physicist", "teacher")
    )
    val mshAnswers: HashMap<Int, List<String>> = hashMapOf(
        1 to listOf(
            "Tony Stark", "Some dude", "Patrick Star",
            "Johhny Blaze"
        ),
        2 to listOf("Both", "Neither", "Thor", "Hulk"),
        3 to listOf("Yes", "No", "Maybe", "Only if Ben Affleck is fired")
    )
    val correctMathAnswers: HashMap<Int, String> = hashMapOf(1 to "4", 2 to "8", 3 to "9", 4 to "16", 5 to "25")
    val correctPhysicsAnswers: HashMap<Int, String> = hashMapOf(1 to "convex", 2 to "low", 3 to "physicist")

    val correctMSHAnswers: HashMap<Int, String> = hashMapOf(1 to "Tony Stark", 2 to "Hulk", 3 to "No")

    var sub = ""

    var position = 1

    var tally = 0

    var checkedAnswer = ""

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "QuizApp has been created")


    }
    override fun getUserCheckedAnswer():String{
        return  checkedAnswer
    }

    override fun setUserCheckedAnswer(ans: String){
        checkedAnswer = ans
    }



    override fun setSubject(selectedSubject: String){
        sub = selectedSubject
    }

    override fun getSubject(): String {
        return sub
    }

    //get list of questions
    override fun getQuizOptions(): List<String> {
        return quizList
    }

    override fun topicOverview(subject: String): String{
        var topic = ""
        when(subject){
            "Math" -> topic = topicList[0]
            "Physics" -> topic = topicList[1]
            "Marvel Super Heroes" -> topic = topicList[2]
        }
        return  topic + "Please Begin to start this quiz"
    }
    override fun setIndex(num: Int){
        position = num
    }

    override fun getIndex():Int{
        return position
    }

   override fun setTotalTally(count:Int){
        tally = count
    }

    override  fun getTotalTally(): Int{
        return tally
    }

    override fun getQuestion(subject: String, index: Int): String {
        var question = ""
        when (subject) {
            "Math" -> question = mQuestions.getValue(index)
            "Physics" -> question = pQuestions.getValue(index)
            "Marvel Super Heroes" -> question = mshQuestions.getValue(index)
        }
        return question
    }

    override  fun getAnswer(subject: String, index: Int): List<String> {
        var answer = listOf("")
        when (subject) {
            "Math" -> answer = mAnswers.getValue(index)
            "Physics" -> answer = pAnswers.getValue(index)
            "Marvel Super Heroes" -> answer = mshAnswers.getValue(index)
        }
        return answer
    }

    override fun correctAnswer(subject: String, index: Int): String {
        var answer = ""
        when (subject) {
            "Math" -> answer = correctMathAnswers.getValue(index)
            "Physics" -> answer = correctPhysicsAnswers.getValue(index)
            "Marvel Super Heroes" -> answer = correctMSHAnswers.getValue(index)
        }
        return answer
    }

    override fun quizSize(subject: String): Int {
        var size = 0
        when(subject){
            "Math" -> size = topicList[0].length
            "Physics" -> size = topicList[1].length
            "Marvel Super Heroes" -> size = topicList[2].length
        }
        return size
    }

}

