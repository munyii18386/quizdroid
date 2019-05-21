package edu.washington.lmburu.quizdroid
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

private const val TAG = "Repo"
private const val FILE_NAME = "question.json"
var  allTopics: HashMap<String, Topic> = hashMapOf()

interface TopicRepository {

companion object : TopicRepository {

    private var instance: TopicRepository? = null
    lateinit var DATA:JSONArray
    var currentTopic = ""
    var checkedAnswer = ""
    var tally = 0
    var currentIndex = 0
    val FILE_NAME = "quiz"


    fun getInstance(): TopicRepository {
        // Instantiate the RequestQueue.


        val item = DataFetch()
        val result = item.setUpVolleyFetching()

        val info = QuizApp.instance.openFileInput(FILE_NAME).bufferedReader().use { it.readText() }
        Log.i(TAG, "$info")

        DATA = JSONArray(info)

//        Log.i(TAG, "data accessed from Repo is: $dataString")


//        readFile()
//        Log.i(TAG, "this is my data: $DATA")
        if (instance == null) {
            instance = TopicRepository
        }
        for (i in 0 until DATA.length()) {
            // get the object
            var item = DATA.getJSONObject(i)
            // get title
            var title = item.get("title") as String
//            Log.i(TAG, "title: $title")
            // get desc
            var desc = item.get("desc") as String
//            Log.i(TAG, "desc: $desc")
            //get question objects
            var questionsString = item.get("questions").toString()
            var questionList = generateQuestionObj(questionsString)
            // create Topic Object
            generateTopicObject(title, desc, questionList)
        }

        return instance as TopicRepository
    }


    fun generateQuestionObj(name:String): ArrayList<Question> {
            var questionObjectList: ArrayList<Question> = ArrayList()
            var subData = JSONArray(name)
//            Log.i(TAG, "questions String: $questionsString")
            //convert to Question Object. Need to transform it to JSON Obj first.
            for (x in 0 until subData.length()) {
                var subItem = subData.getJSONObject(x)
                var subQuestion = subItem.get("text").toString()
                var subIndex = subItem.get("answer").toString().toInt() - 1
                var subAnswers = subItem.get("answers").toString() //make this a string
                    .removePrefix("[") //remove unwanted char
                    .removeSuffix("]") // remove unwanted char
                    .replace("\"", "") // remove "" from each ans
                    .split(",") // make this a List
                // Transform ans List to ans ArrayList.. easier than changing all prior parameters
                var ansList: ArrayList<String> = ArrayList()
                for (subAnswer in subAnswers) {
                    ansList.add(subAnswer)
                }
                // create a Question Object
                var question = Question(subQuestion, ansList, subIndex)
                questionObjectList.add(question)
            }
            return  questionObjectList
        }

    fun generateTopicObject(title:String, desc:String, questionObjectList:ArrayList<Question>){
        var topic = Topic(title, desc, questionObjectList)
        // add to hash - list of all topics - makes navigation easier
        allTopics.put("$title", topic)
    }
}

//    fun readFile() {
//
//
//        val myString: String? = try {
////            grab file from assets folder & read it to a String
//            val inputStream = QuizApp.instance.assets.open(FILE_NAME)
//            val size = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            inputStream.close()
//            String(buffer, Charsets.UTF_8)
//        } catch (e: IOException) {
//            null
//        }
//        myString?.let {
//            //create json
//            DATA = JSONArray(myString)
//        }
//
//
//}

    fun getTopicInfo(): HashMap<String, Topic>{
        Log.i(TAG, "${allTopics.size}")
        for(key in allTopics.keys){
            val item = allTopics.get(key)!!.getQuestions()[0].getQuestion()
//            Log.i(TAG, "title is: $item")
        }

        val math = allTopics.get("Marvel Super Heroes")!!.getQuestions().size
            Log.i(TAG, "$math")
        return allTopics
    }

    fun getTopics(): List<String>{
        var listOfTopics: ArrayList<String> = ArrayList()
        for(key in allTopics.keys){
            listOfTopics.add(key)
        }
        return listOfTopics
    }


    fun getQuizSize():Int{
        return allTopics.get(getCurrentTopicNow())!!.getQuestions().size
    }


    fun getCurrentTopicNow() = currentTopic

//    update the current topic choice
    fun updateCurrentTopic(name: String){
        currentTopic = name
    }


//    track and update the current question index
    fun getIndex()= currentIndex

    fun updateIndex(num:Int){
        currentIndex = num
    }

//    track and  update score values
    fun getScore()= tally

    fun updateScore(number:Int){
        tally = number
    }

//    track and update a user's selected answer
    fun setUserCheckedAns(ans:String){
        checkedAnswer = ans
    }

    fun getUserCheckedAns()= checkedAnswer

}