package edu.washington.lmburu.quizdroid

class QuizClass {
    private val quizList = listOf("Math", "Physics", "Marvel Super Heroes")


    fun getQuizOptions(): List<String> {
        return quizList
    }

   fun topicOverview(subject: String): String{
        var topic = ""
        when(subject){
            "Math" -> topic = "This Math quiz contains 5 questions. "
            "Physics" -> topic = "This Physics quiz contains 3 questions. "
            "Marvel Super Heroes" -> topic = "This Marvel Super Heroes quiz contains 3 questions. "
        }
        return  topic + "Please Begin to start this quiz"
    }

    fun quizSize(subject: String): Int {
        var size = 0
        when(subject){
            "Math" -> size = 5
            "Physics" -> size = 3
            "Marvel Super Heroes" -> size = 3
        }
        return size
    }

    fun getQuestion(subject: String, index: Int): String {
        val mQuestions: HashMap<Int, String> = hashMapOf( 1 to "2 * 2 =", 2 to "4 * 2 =", 3 to "3 * 3 = ", 4 to "4 * 4 =", 5 to "5 * 5 =")
        val pQuestions: HashMap<Int, String> = hashMapOf(
            1 to "A magnifying glass is what type of lens?", 2 to "Conductors have a high or low resistance?",
            3 to "A person who studies physics is known as a?"
        )
        val mshQuestions : HashMap<Int, String> = hashMapOf( 1 to "Who is Iron Man?", 2 to "Who is stronger Thor or Hulk?", 3 to "Can Batman join the team?")

        var question = ""

        when (subject) {
            "Math" -> question = mQuestions.getValue(index)
            "Physics" -> question = pQuestions.getValue(index)
            "Marvel Super Heroes" -> question = mshQuestions.getValue(index)
        }
        return question
    }

    fun getAnswer(subject: String, index: Int): List<String> {
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

        var answer = listOf("")

        when (subject) {
            "Math" -> answer = mAnswers.getValue(index)
            "Physics" -> answer = pAnswers.getValue(index)
            "Marvel Super Heroes" -> answer = mshAnswers.getValue(index)
        }
        return answer
    }

    fun correctAnswer(subject: String, index: Int): String {
        val mAnswers: HashMap<Int, String> = hashMapOf(1 to "4", 2 to "8", 3 to "9", 4 to "16", 5 to "25")
        val pAnswers: HashMap<Int, String> = hashMapOf(1 to "convex", 2 to "low", 3 to "physicist")
        val mshAnswers: HashMap<Int, String> = hashMapOf(1 to "Tony Stark", 2 to "Hulk", 3 to "No")
        var answer = ""
        when (subject) {
            "Math" -> answer = mAnswers.getValue(index)
            "Physics" -> answer = pAnswers.getValue(index)
            "Marvel Super Heroes" -> answer = mshAnswers.getValue(index)
        }
        return answer
    }
}