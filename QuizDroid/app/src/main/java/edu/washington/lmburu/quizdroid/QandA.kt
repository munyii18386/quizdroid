package edu.washington.lmburu.quizdroid



class QandA {


    private val mQuesitons = listOf("2 * 2 =", "4 * 2 =", "3 * 3 = ", "4 * 4 =", "5 * 5 =")

    private val mAnswers: HashMap<Int, List<String>> = hashMapOf(1 to listOf("4", "3", "5", "6"), 2 to listOf("4", "8", "5", "6"), 3 to listOf("1", "10", "9", "5"), 4 to listOf("16", "12", "14", "10"), 5 to listOf("20", "22", "23", "25"))

    private val pQuestions = listOf("A magnifying glass is what type of lens?", "Conductors have a high or low resistance?", "A person who studies physics is known as a?")

    private val pAnswers: HashMap<Int, List<String>> = hashMapOf( 1 to listOf("glass", "plastic", "clear", "convex"), 2 to listOf("none", "small", "low", "high"), 3 to listOf("scientist", "magician", "physicist", "teacher"))

    private val mshQuestions = listOf("Who is Iron Man?", "Who is stronger Thor or Hulk?", "Can Batman join the team?")

    private val mshAnswers: HashMap<Int, List<String>> = hashMapOf(1 to listOf("Tony Stark", "Some dude", "Patrick Star", "Johhny Blaze"), 2 to listOf("Both", "Neither", "Thor", "Hulk"), 3 to listOf("Yes", "No", "Maybe", "Only if Ben Affleck is fired"))

    fun getQuestion(subject: String,index: Int): String{

        var question = ""

        when(subject){

            "Math" -> question = mQuesitons[index]

            "Physics" -> question = pQuestions[index]

            "Marvel Super Heroes" -> question = mshQuestions[index]

        }

        return question

    }



    fun getAnswer(subject:String, index:Int): List<String>{

        var answer = listOf("")

        when(subject){

            "Math" -> answer = mAnswers.getValue(index)

            "Physics" -> answer = pAnswers.getValue(index)

            "Marvel Super Heroes" -> answer = mshAnswers.getValue(index)

        }

        return answer
}