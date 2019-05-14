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
    private var callback: OnSubmitSelectedListener? = null

    private var updatedData: QuestionDataManager? = null

    internal interface OnSubmitSelectedListener{
        fun onSubmit()
    }

    internal interface QuestionDataManager{
        fun questionFragmentData(checkedAns: String, index: Int)
    }

    companion object {
        private const val QUESTION = "question"
        private const val ANS = "ans"
        private const val INDEX = "user answer"
        fun newInstance(question:String, answers: ArrayList<String>, index:Int): QuestionFragment {
            val args = Bundle().apply {
                putString(QUESTION, question)
                putStringArrayList(ANS, answers)
                putInt(INDEX, index)
            }
            return QuestionFragment().apply {
                arguments = args
            }
            return QuestionFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as? OnSubmitSelectedListener
        if(callback == null){
            throw ClassCastException("$context must implement OnSubmitSelectedListener")
        }
        updatedData = context as? QuestionDataManager
        if(updatedData == null){
            throw  ClassCastException("$context must implement QuestionDataManager")
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_question, container, false)


            arguments?.let {

                var question = it.getString(QUESTION)
                var answers:ArrayList<String> = it.getStringArrayList(ANS)
                var checkedAnswer = ""
                var index = it.getInt(INDEX)
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
                    //                Log.i(TAG, "$subject, $index ${getUserCheckedAnswer()}")
//                Log.i(TAG, "instance count is: ${getInstanceCount()}")

                    index++
                    updatedData?.questionFragmentData(checkedAnswer,index )

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

            }
        return rootView
    }
}
