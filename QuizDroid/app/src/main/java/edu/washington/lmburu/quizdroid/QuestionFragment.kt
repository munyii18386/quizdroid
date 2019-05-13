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
import javax.security.auth.Subject.getSubject

private val TAG = "QuestionFragment"
class QuestionFragment : Fragment(), TopicRepository {
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

        if(getUserCheckedAnswer().equals("")){
            rootView.submit.visibility = View.INVISIBLE
        }

            var subject = getSubject()
            var index = getIndex()

            //generate appropriate view
            rootView.question.text = getQuestion(subject, index)
            rootView.r_btn_a.text = getAnswer(subject, index)[0]
            rootView.r_btn_b.text = getAnswer(subject, index)[1]
            rootView.r_btn_c.text = getAnswer(subject, index)[2]
            rootView.r_btn_d.text = getAnswer(subject, index)[3]
            //submit button listener
            rootView.submit.setOnClickListener {
                Log.i(TAG, "$subject, $index ${getUserCheckedAnswer()}")
//                Log.i(TAG, "instance count is: ${getInstanceCount()}")
                callback!!.onSubmit()
            }

        // get the value selected by the user
        rootView.btn_ans.setOnCheckedChangeListener { _, i ->
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
