package edu.washington.lmburu.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_answer.*
import kotlinx.android.synthetic.main.fragment_answer.view.*

private val TAG = "AnswerFragment"


class AnswerFragment : Fragment() {

    private var callback: OnClickListener? = null

    internal interface OnClickListener{
        fun onSelected(subject: String, index: String, scoreCount: String)
        fun onFinish(){}
    }
    companion object{
        private const val SUBJECT = "subject"
        private const val INDEX = "index"
        private const val CHECKED_ANSWER = "checkedAnswer"
        private const val SCORE_COUNT = "scoreCount"

        fun newInstance(subject: String, index: String, checkedAnswer: String, scoreCount: String): AnswerFragment{
            var args = Bundle().apply{
                putString(SUBJECT, subject)
                putString(INDEX, index)
                putString(CHECKED_ANSWER, checkedAnswer)
                putString(SCORE_COUNT, scoreCount)
            }
            return AnswerFragment().apply {
                arguments = args
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as? OnClickListener
        if(callback == null){
            throw ClassCastException("$context must implement OnClickListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =inflater.inflate(R.layout.fragment_answer, container, false)
        val data = QuizClass()
        arguments?.let {
            val subject = it.getString(SUBJECT)
            var index = it.getString(INDEX).toInt()
            val checkedAnswer = it.getString(CHECKED_ANSWER)
            var scoreCount = it.getString(SCORE_COUNT).toInt()
            val correctAnswer = data.correctAnswer(subject, index)
            val totalQuestions = data.quizSize(subject)
//            Log.i(TAG, "Fragment values: $subject, $index, $checkedAnswer, $scoreCount,  $checkedAnswer, $totalQuestions")

            //update user's score count
            if(checkedAnswer.equals(correctAnswer)){
                scoreCount++
            }

            //if this is the last question, do not show 'next' button
            if(index == totalQuestions){
                rootView.next.visibility = View.INVISIBLE
            } else{
                index++
            }

            //update the answer fragment with corresponding values
            rootView.user_ans.text= checkedAnswer
            rootView.correct_ans.text = correctAnswer
            rootView.score.text = "You currently have $scoreCount out of $totalQuestions correct."

            rootView.next.setOnClickListener {
                callback!!.onSelected(subject, "$index", "$scoreCount")
            }
        }

        rootView.finish.setOnClickListener {
            callback!!.onFinish()
        }

        return rootView
    }
}
