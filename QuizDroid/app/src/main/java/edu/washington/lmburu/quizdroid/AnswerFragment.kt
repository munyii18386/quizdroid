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


class AnswerFragment : Fragment(), TopicRepository {

    private var callback: OnClickListener? = null

    internal interface OnClickListener{
        fun onSelected(subject: String, index: Int, scoreCount: Int)
        fun onFinish(){}
    }
    companion object{
        private const val SUBJECT = "subject"
        private const val INDEX = "index"
        private const val CHECKED_ANSWER = "checkedAnswer"
        private const val SCORE_COUNT = "scoreCount"

        fun newInstance(subject: String, index: Int, checkedAnswer: String, scoreCount: Int): AnswerFragment{
            var args = Bundle().apply{
                putString(SUBJECT, subject)
                putInt(INDEX, index)
                putString(CHECKED_ANSWER, checkedAnswer)
                putInt(SCORE_COUNT, scoreCount)
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
//        val data = QuizClass()
        arguments?.let {
            val subject = it.getString(SUBJECT)
            var index = it.getInt(INDEX)
            val checkedAnswer = it.getString(CHECKED_ANSWER)
            var scoreCount = it.getInt(SCORE_COUNT)
            val correctAnswer = correctAnswer(subject, index)
            val totalQuestions = quizSize(subject)

            //update user's score count
            if(checkedAnswer.equals(correctAnswer)){
                scoreCount++
                setTotalTally(scoreCount)
            }

            //if this is the last question, do not show 'next' button
            if(index == totalQuestions){
                rootView.next.visibility = View.INVISIBLE
            } else{
                index++
                setIndex(index)
            }

            //update the answer fragment with corresponding values
            rootView.user_ans.text= getUserCheckedAnswer()
            rootView.correct_ans.text = correctAnswer(subject, index)
            rootView.score.text = "You currently have ${getTotalTally()} out of $totalQuestions correct."

            rootView.next.setOnClickListener {
                callback!!.onSelected(subject, index, scoreCount)
            }
        }

        rootView.finish.setOnClickListener {
            callback!!.onFinish()
        }
        return rootView
    }
}
