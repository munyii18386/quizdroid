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
class QuestionFragment : Fragment() {
    private var callback: OnSubmitSelectedListener? = null

    internal interface OnSubmitSelectedListener{
        fun onSubmit(subject: String, count:String, checkedAnswer:String, scoreCount: String)
    }

    companion object {
        private const val SUBJECT  = "subject"
        private const val INDEX: String = "index"
        private const val SCORE_COUNT: String ="score_count"
        // new instance of question fragment
        fun newInstance(subject: String?, index: String?, scoreCount: String?): QuestionFragment {
            val args = Bundle().apply {
                putString(SUBJECT, subject)
                putString(INDEX, index)
                putString(SCORE_COUNT, scoreCount)
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
        val data = QuizClass()
        var checkedAnswer = ""
        if(checkedAnswer.equals("")){
            rootView.submit.visibility = View.INVISIBLE
        }
        arguments?.let {
            var subject = it.getString(SUBJECT)
            var index = it.getString(INDEX)
            var scoreCount = it.getString(SCORE_COUNT)
            //generate appropriate view
            rootView.question.text = data.getQuestion(subject, index.toInt())
            rootView.r_btn_a.text = data.getAnswer(subject, index.toInt())[0]
            rootView.r_btn_b.text = data.getAnswer(subject, index.toInt())[1]
            rootView.r_btn_c.text = data.getAnswer(subject, index.toInt())[2]
            rootView.r_btn_d.text = data.getAnswer(subject, index.toInt())[3]
            //submit button listener
            rootView.submit.setOnClickListener {
                callback!!.onSubmit(subject, index, checkedAnswer, scoreCount)
            }
        }
        // get the value selected by the user
        rootView.btn_ans.setOnCheckedChangeListener { btn_ans, i ->
            when(i){
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
