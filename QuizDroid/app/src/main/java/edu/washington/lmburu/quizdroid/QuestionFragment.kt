package edu.washington.lmburu.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_question.view.*
import kotlinx.android.synthetic.main.multi_use_activity.*
import javax.security.auth.Subject.getSubject

private val TAG = "QuestionFragment"
class QuestionFragment : Fragment(), TopicRepository {
    lateinit  private var repo: TopicRepository

    private var callback: OnSubmitSelectedListener? = null

    internal interface OnSubmitSelectedListener{
        fun onSubmit()
    }

    companion object {
        fun newInstance(): QuestionFragment {
            return QuestionFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as? OnSubmitSelectedListener
        if(callback == null){
            throw ClassCastException("$context must implement OnSubmitSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_question, container, false)
        repo = QuizApp.instance.getRepository()
        val topic = repo.getTopicInfo().getValue(repo.getCurrentTopicName())
        val question = topic.getQuestions()[repo.getIndex()].getQuestion()
        val answers = topic.getQuestions()[repo.getIndex()].getAnswers()

        var checkedAnswer = ""
        if(checkedAnswer.equals("")){
            rootView.submit.visibility = View.INVISIBLE
        }
        //generate appropriate view
        rootView.question.text = question
        rootView.r_btn_a.text = answers[0]
        rootView.r_btn_b.text = answers[1]
        rootView.r_btn_c.text = answers[2]
        rootView.r_btn_d.text = answers[3]
        //submit button listener
        rootView.submit.setOnClickListener {
            repo.setUserCheckedAns(checkedAnswer)
            callback!!.onSubmit()
        }
        // get the value selected by the user
        rootView.btn_ans.setOnCheckedChangeListener { _, i ->
            when (i) {
                rootView.r_btn_a.id -> checkedAnswer = rootView.r_btn_a.text as String
                rootView.r_btn_b.id -> checkedAnswer = rootView.r_btn_b.text as String
                rootView.r_btn_c.id -> checkedAnswer = rootView.r_btn_c.text as String
                rootView.r_btn_d.id -> checkedAnswer = rootView.r_btn_d.text as String
            }
            rootView.submit.visibility = View.VISIBLE
        }
        return rootView
    }
}