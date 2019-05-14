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
        val topic = repo.getTopicInfo().getValue(repo.getCurrentTopicName())
        val summary = topic.getDesc()

//      Topic Overview Fragment
        val topicOverviewFragment = TopicOverviewFragment.newInstance(topic.getTopic(), summary)
        val topicFt = supportFragmentManager.beginTransaction()
        topicFt.replace(id.container,topicOverviewFragment )
        topicFt.addToBackStack(null)
        topicFt.commit()
    }

//  get app instance and return access to repository
    fun initiateApp(): TopicRepository{
        val app = this.application as QuizApp
        return app.getRepository()
    }


    override fun onSelected() {
        val repo = initiateApp()
        val topic = repo.getTopicInfo().getValue(repo.getCurrentTopicName())
        val question = topic.getQuestions()[repo.getIndex()].getQuestion()
        val answers = topic.getQuestions()[repo.getIndex()].getAnswers()

//        Question Fragment
        val questionFragment = QuestionFragment.newInstance(question, answers)
        val questionFt = supportFragmentManager.beginTransaction()
        questionFt.replace(id.container, questionFragment)
        questionFt.addToBackStack(null)
        questionFt.commit()
    }


    override fun onSubmit() {
        val repo = initiateApp()
        val topic = repo.getTopicInfo().getValue(repo.getCurrentTopicName())
        val correctAnswer = topic.getQuestions()[repo.getIndex()].getCorrectAns()
        val tally = repo.getScore()
        val quizSize = repo.getQuizSize()
        val checkedAns = repo.getUserCheckedAns()
        val index = repo.getIndex()

//        Answer Fragment
        val answerFragment = AnswerFragment.newInstance(checkedAns, correctAnswer, tally, quizSize, index)
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

//    process repo values with return values from Answer Fragment
    override fun answerFragmentData(tally: Int,  index:Int){
        val repo = initiateApp()
        repo.updateScore(tally)
        repo.updateIndex(index)
    }

//    Process repo values with return values from Question Fragment
    override fun questionFragmentData(checkedAns: String){
        val repo = initiateApp()
        repo.setUserCheckedAns(checkedAns)
    }

}
