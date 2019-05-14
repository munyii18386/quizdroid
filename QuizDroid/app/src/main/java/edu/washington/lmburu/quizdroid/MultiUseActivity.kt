package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import edu.washington.lmburu.quizdroid.R.*
import kotlinx.android.synthetic.main.fragment_question.*

private val TAG = "MultiUse"

class MultiUseActivity : AppCompatActivity(),  TopicOverviewFragment.OnBeginSelected,
        QuestionFragment.OnSubmitSelectedListener, AnswerFragment.OnClickListener,
            AnswerFragment.DataManager, QuestionFragment.QuestionDataManager {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.multi_use_activity)
        val repo = initiateApp()
        val topic = repo.getCurrentTopicName()
        val summary = repo.topicDesc(topic)
        Log.i(TAG, "topic: $topic, summary: $summary")

//        Log.i(TAG, "Topic instance count is: ${getInstanceCount()}")
        val topicOverviewFragment = TopicOverviewFragment.newInstance(topic, summary)
        val topicFt = supportFragmentManager.beginTransaction()
        topicFt.replace(id.container,topicOverviewFragment )
        topicFt.addToBackStack(null)
        topicFt.commit()
    }

    fun initiateApp(): TopicRepository{
        val app = this.application as QuizApp
        return app.getRepository()
    }


    override fun onSelected() {
//        Log.i(TAG, "Question instance count is: ${getInstanceCount()}")
        val repo = initiateApp()
        val currentQuestion = repo.getQuestion(repo.getCurrentTopicName())
        val answers = repo.getAns()
//        val checkedAns = repo.getUserCheckedAns()
        val index = repo.getQuestionIndex()
        Log.i(TAG, "$answers")
        val questionFragment = QuestionFragment.newInstance(currentQuestion, answers, index)
        val questionFt = supportFragmentManager.beginTransaction()
        questionFt.replace(id.container, questionFragment)
        questionFt.addToBackStack(null)
        questionFt.commit()
    }


    override fun onSubmit() {
        val repo = initiateApp()
        val correctAnswer = repo.getCorrectAns()
        val tally = repo.getScore()
        val quizSize = repo.getQuizSize()
        val checkedAns = repo.getUserCheckedAns()
//        Log.i(TAG, "Answer instance count is: ${getInstanceCount()}")
        val answerFragment = AnswerFragment.newInstance(checkedAns, correctAnswer, tally, quizSize)
        val ansFt = supportFragmentManager.beginTransaction()
        ansFt.replace(id.container, answerFragment)
        ansFt.addToBackStack(null)
        ansFt.commit()
    }


    override fun onFinish() {
//        Log.i(TAG, "Quiz done instance count is: ${getInstanceCount()}")
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    override fun answerFragmentData(tally: Int, count: Int){
        val repo = initiateApp()
        repo.updateScore(tally)
        repo.updatePosition(count)
    }

    override fun questionFragmentData(checkedAns: String, index: Int){
        val repo = initiateApp()
        repo.updateQuestionIndex(index)
        repo.setUserCheckedAns(checkedAns)

    }

}
