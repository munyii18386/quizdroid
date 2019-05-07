package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import edu.washington.lmburu.quizdroid.R.*


private val TAG= "MultiUseActivity"
class MultiUseActivity : AppCompatActivity(), TopicOverviewFragment.OnBeginSelected, QuestionFragment.OnSubmitSelectedListener, AnswerFragment.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.multi_use_activity)
        val data = QuizClass()
        val extras = intent.extras ?: return
        val subject = extras.getString("heading")
        val summary = data.topicOverview(subject)

        val topicOverviewFragment = TopicOverviewFragment.newInstance(subject, summary)
        val topicFt = supportFragmentManager.beginTransaction()
        topicFt.replace(id.container,topicOverviewFragment )
        topicFt.commit()
    }

    override fun onSelected(subject: String, index: String, scoreCount: String) {
        val questionFragment = QuestionFragment.newInstance(subject, index, scoreCount)
        val questionFt = supportFragmentManager.beginTransaction()
        questionFt.replace(id.container, questionFragment)
        questionFt.commit()
    }

    override fun onSubmit(subject: String, index: String, checkedAnswer: String, scoreCount: String) {
        val answerFragment = AnswerFragment.newInstance(subject, index, checkedAnswer, scoreCount)
        val ansFt = supportFragmentManager.beginTransaction()
        ansFt.replace(id.container, answerFragment)
        ansFt.commit()
    }

    override fun onFinish() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}
