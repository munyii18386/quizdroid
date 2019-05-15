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
        QuestionFragment.OnSubmitSelectedListener, AnswerFragment.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.multi_use_activity)

//      Topic Overview Fragment
        val topicOverviewFragment = TopicOverviewFragment.newInstance()
        val topicFt = supportFragmentManager.beginTransaction()
        topicFt.replace(id.container,topicOverviewFragment )
        topicFt.addToBackStack(null)
        topicFt.commit()
    }


    override fun onSelected() {


//        Question Fragment
        val questionFragment = QuestionFragment.newInstance()
        val questionFt = supportFragmentManager.beginTransaction()
        questionFt.replace(id.container, questionFragment)
        questionFt.addToBackStack(null)
        questionFt.commit()
    }


    override fun onSubmit() {
//        Answer Fragment
        val answerFragment = AnswerFragment.newInstance()
        val ansFt = supportFragmentManager.beginTransaction()
        ansFt.replace(id.container, answerFragment)
        ansFt.addToBackStack(null)
        ansFt.commit()
    }

//    Return to main activity
    override fun onFinish() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }


}
