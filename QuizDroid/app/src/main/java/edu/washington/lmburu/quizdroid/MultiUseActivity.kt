package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import edu.washington.lmburu.quizdroid.R.*

private val TAG = "MultiUse"

class MultiUseActivity : AppCompatActivity(),  TopicOverviewFragment.OnBeginSelected{//, QuestionFragment.OnSubmitSelectedListener, AnswerFragment.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.multi_use_activity)
        val app = this.application as QuizApp
        val topic = app.getRepository().getCurrentTopicName()
        val summary = app.getRepository().topicDesc(topic)
        Log.i(TAG, "topic: $topic, summary: $summary")


//        Log.i(TAG, "Topic instance count is: ${getInstanceCount()}")
        val topicOverviewFragment = TopicOverviewFragment.newInstance(topic, summary)
        val topicFt = supportFragmentManager.beginTransaction()
        topicFt.replace(id.container,topicOverviewFragment )
        topicFt.addToBackStack(null)
        topicFt.commit()
    }


    override fun onSelected() {
//        Log.i(TAG, "Question instance count is: ${getInstanceCount()}")
        val questionFragment = QuestionFragment.newInstance()
        val questionFt = supportFragmentManager.beginTransaction()
        questionFt.replace(id.container, questionFragment)
        questionFt.addToBackStack(null)
        questionFt.commit()
    }
//
//    override fun onSubmit() {
////        Log.i(TAG, "Answer instance count is: ${getInstanceCount()}")
//        val answerFragment = AnswerFragment.newInstance()
//        val ansFt = supportFragmentManager.beginTransaction()
//        ansFt.replace(id.container, answerFragment)
//        ansFt.addToBackStack(null)
//        ansFt.commit()
//    }
//
//    override fun onFinish() {
////        Log.i(TAG, "Quiz done instance count is: ${getInstanceCount()}")
//        val i = Intent(this, MainActivity::class.java)
//        startActivity(i)
//    }
}
