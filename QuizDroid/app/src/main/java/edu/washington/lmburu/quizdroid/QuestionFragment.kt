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

private val TAG = "QuestionFragment"
class QuestionFragment : Fragment(), TopicRepository {
    private var callback: OnSubmitSelectedListener? = null

    internal interface OnSubmitSelectedListener{
        fun onSubmit(subject: String, index:Int, checkedAnswer:String, scoreCount: Int)
    }

    companion object {
        private const val SUBJECT  = "subject"
        private const val INDEX = "index"
        private const val SCORE_COUNT ="score_count"
        // new instance of question fragment
        fun newInstance(subject: String, index: Int, scoreCount: Int): QuestionFragment {
            val args = Bundle().apply {
                putString(SUBJECT, subject)
                putInt(INDEX, index)
                putInt(SCORE_COUNT, scoreCount)
            }
            return QuestionFragment().apply {
                arguments = args
            }
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
//        val data = QuizClass()

        if(getUserCheckedAnswer().equals("")){
            rootView.submit.visibility = View.INVISIBLE
        }
        arguments?.let {
            var subject = it.getString(SUBJECT)
            var index = it.getInt(INDEX)
            var scoreCount = it.getInt(SCORE_COUNT)
            //generate appropriate view
            rootView.question.text = getQuestion(subject, index)
            rootView.r_btn_a.text = getAnswer(subject, index)[0]
            rootView.r_btn_b.text = getAnswer(subject, index)[1]
            rootView.r_btn_c.text = getAnswer(subject, index)[2]
            rootView.r_btn_d.text = getAnswer(subject, index)[3]
            //submit button listener
            rootView.submit.setOnClickListener {
                callback!!.onSubmit(subject, index, getUserCheckedAnswer(), scoreCount)
            }
        }
        // get the value selected by the user
        rootView.btn_ans.setOnCheckedChangeListener { btn_ans, i ->
            when(i){
                rootView.r_btn_a.id -> setUserCheckedAnswer(rootView.r_btn_a.text as String)
                rootView.r_btn_b.id -> setUserCheckedAnswer(rootView.r_btn_b.text as String)
                rootView.r_btn_c.id -> setUserCheckedAnswer(rootView.r_btn_c.text as String)
                rootView.r_btn_d.id -> setUserCheckedAnswer(rootView.r_btn_d.text as String)
            }
            rootView.submit.visibility = View.VISIBLE
        }
        return rootView
    }
}
