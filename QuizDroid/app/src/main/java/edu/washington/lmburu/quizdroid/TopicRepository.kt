package edu.washington.lmburu.quizdroid
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import kotlinx.android.synthetic.main.*

private const val TAG = "Repo"
private const val FILE_NAME = "question.json"
var  allTopics: HashMap<String, Topic> = hashMapOf()


interface TopicRepository {


companion object : TopicRepository {

    private var instance: TopicRepository? = null
    lateinit var  DATA: JSONArray

    fun getInstance(): TopicRepository {
        readFile()
        Log.i(TAG, "this is my data: $DATA")
        if (instance == null) {
            instance = TopicRepository
        }

        for(i in 0 until DATA.length()){
            // get the object
            var item = DATA.getJSONObject(i)
            // get title
            var title = item.get("title") as String
            Log.i(TAG, "title: $title")
            // get desc
            var desc = item.get("desc") as String
            Log.i(TAG, "desc: $desc")
            //get question objects
            var questionsString = item.get("questions").toString()
            var subData = JSONArray(questionsString)
            Log.i(TAG, "questions String: $questionsString")
            //convert to Question Object. Need to transform it to JSON Obj first.
            var questionObjectList: ArrayList<Question> = ArrayList()
            for (x in 0 until subData.length()){
                var subItem = subData.getJSONObject(x)
                var subQuestion = subItem.get("text").toString()
                var subIndex = subItem.get("answer").toString().toInt()
                var subAnswers = subItem.get("answers").toString() //make this a string
                    .removePrefix("[") //remove unwanted char
                    .removeSuffix("]") // remove unwanted char
                    .split(",") // make this a List
                // Transform ans List to ans ArrayList.. easier than changing all prior parameters
                var ansList: ArrayList<String> = ArrayList()
                for (subAnswer in subAnswers) {
                    ansList.add(subAnswer)
                }
                // create a Question Object
                var question = Question(subQuestion, ansList, subIndex)
                questionObjectList.add(question) // add each Question Object to ArrayList of Question Objects
            }
            Log.i(TAG, "list of question objects: $questionObjectList" )
            // create Topic Object
            var topicObject = Topic(title, desc, questionObjectList)
            // add to hash - list of all topics - makes navigation easier
            allTopics.put("$title", topicObject)
        }

        Log.i(TAG, "${allTopics.get("Mathematics")}")


        return instance as TopicRepository

    }

    //        variable that track the current position through out a quiz
    var currentTopic = ""
    var checkedAnswer = ""
    var tally = 0
    var currentIndex = 0




    //        hard coded data
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
        ),
        arrayListOf("Both", "Neither", "Thor", "Hulk"),
        arrayListOf("Yes", "No", "Maybe", "Only if Ben Affleck is fired")
    )

    val m1 = Question(mQuestions[0], mAnswers[0], 0)
    val m2 = Question(mQuestions[1], mAnswers[1], 1)
    val m3 = Question(mQuestions[2], mAnswers[2], 2)
    val m4 = Question(mQuestions[3], mAnswers[3], 0)
    val m5 = Question(mQuestions[4], mAnswers[4], 3)

    val listOfMathQuestions = arrayListOf(m1, m2, m3, m4, m5) // list of math question obj

    val p1 = Question(pQuestions[0], pAnswers[0], 3)
    val p2 = Question(pQuestions[1], pAnswers[1], 2)
    val p3 = Question(pQuestions[2], pAnswers[2], 2)

    val listOfPhysicsQuestions = arrayListOf(p1, p2, p3) // list of physics question obj

    val msh1 = Question(mshQuestions[0], mshAnswers[0], 0)
    val msh2 = Question(mshQuestions[1], mshAnswers[1], 2)
    val msh3 = Question(mshQuestions[2], mshAnswers[2], 1)

    val listofMarvelQuestions = arrayListOf(msh1, msh2, msh3) // list of marvel question obj

    val mathTopic = Topic(quizList[0], topicList[0], listOfMathQuestions)
    val physicsTopic = Topic(quizList[1], topicList[1], listOfPhysicsQuestions)
    val mshTopics = Topic(quizList[2], topicList[2], listofMarvelQuestions)

    //        bucket of topic and question objects
    val listOfTopics = hashMapOf(
        "Math" to mathTopic, "Physics" to physicsTopic, "Marvel Super Heroes" to mshTopics
    )


}


    fun readFile(){
        val myString: String? = try {
//            grab file from assets folder & read it to a String
            val inputStream = QuizApp.instance.assets.open(FILE_NAME)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        }catch (e: IOException) {
            null
        }

        myString?.let {
            //create json
            DATA = JSONArray(myString)
            val quizObject = JSONArray(myString)
//            Log.i(TAG, "Object generated: $quizObject")


            lateinit var topic: JSONObject
            var topicList: ArrayList<String> = ArrayList()
            ////             Read JSON array
            for (i in 0 until quizObject.length()) {
                // get data of array value at index
                topic = quizObject.getJSONObject(i)
//                Log.i(TAG, "Topic Object: $topic ")
                val title = topic.get("title")
//                Log.i(TAG, "Title: $title ")
                topicList.add("$title")

                val question: JSONArray = topic.get("questions") as JSONArray
//                Log.i(TAG, "Question Object: $question ")

                for (a in 0 until question.length()) {
                    val q = question.getJSONObject(a).get("text")
                    val d = question.getJSONObject(a).get("answer")
                    val x = question.getJSONObject(a).get("answers")

//                    Log.i(TAG, "question: $q")
//                    Log.i(TAG, "correct ans index: $d")
//                    Log.i(TAG, "list of answers $x")

                }

            }

//            Log.i(TAG, "list of topics: $topicList")

     }
}


    fun getTopicInfo(): HashMap<String, Topic>{
        return listOfTopics
    }


    fun getTopics(): ArrayList<String>{
        var listOfTopics: ArrayList<String> = ArrayList()
        for(key in allTopics.keys){
            listOfTopics.add(key)
        }
        return listOfTopics
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

    fun getQuizSize():Int{
        var result = 0
        when(currentTopic){
            "Math" -> result = mathTopic.getQuestions().size
            "Physics" -> result = physicsTopic.getQuestions().size
            "Marvel Super Heroes" -> result = mshTopics.getQuestions().size

        }
        return result
    }

//    update the current topic choice
    fun updateCurrentTopic(name: String){
        currentTopic = name
    }

//    track and update the current question index
    fun getIndex():Int{
        return currentIndex
    }

    fun updateIndex(num:Int){
        currentIndex = num
    }

//    track and  update score values
    fun getScore(): Int{
        return tally
    }

    fun updateScore(number:Int){
        tally = number
    }

//    track and update a user's selected answer
    fun setUserCheckedAns(ans:String){
        checkedAnswer = ans
    }

    fun getUserCheckedAns(): String{
        return  checkedAnswer
    }

}