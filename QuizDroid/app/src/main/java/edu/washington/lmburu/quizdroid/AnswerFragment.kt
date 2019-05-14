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
class AnswerFragment : Fragment(), TopicRepository {

    private var callback: OnClickListener? = null

    private var updatedData: DataManager? = null

    internal interface OnClickListener{
        fun onSelected()
        fun onFinish(){}
    }
    internal interface DataManager{
        fun answerFragmentData(tally: Int, count: Int)
    }
    companion object{
        private const val CHECKED_ANS = "checked ans"
        private const val CORRECT_ANS = "correct ans"
        private const val TALLY ="tally"
        private const val QUIZ_SIZE = "quiz size"
        fun newInstance(checkedAns:String, correctAns: String, tally: Int, quizSize: Int): AnswerFragment{
            val args = Bundle().apply {
                putString(CHECKED_ANS, checkedAns)
                putString(CORRECT_ANS, correctAns)
                putInt(TALLY, tally)
                putInt(QUIZ_SIZE, quizSize)

            }
            return AnswerFragment().apply {
                arguments = args
            }
            return AnswerFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as? OnClickListener
        if(callback == null){
            throw ClassCastException("$context must implement OnClickListener")
        }

        updatedData = context as DataManager
        if(updatedData == null){
            throw ClassCastException("$context must implement DataManager")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =inflater.inflate(R.layout.fragment_answer, container, false)

            arguments?.let {
                var checkedAns = it.getString(CHECKED_ANS)
                var correctAns = it.getString(CORRECT_ANS)
                var tally = it.getInt(TALLY)
                var quizSize = it.getInt(QUIZ_SIZE)
                var count = 0
                rootView.user_ans.text= checkedAns
                rootView.correct_ans.text = correctAns
                if(checkedAns.equals(correctAns)){
                    tally++

                }
                if(count == quizSize){
                    rootView.next.visibility = View.INVISIBLE
                }else{
                    count++
                }
                rootView.score.text = "You currently have $tally out of  $quizSize correct."

                updatedData!!.answerFragmentData(tally, count)
            }



            rootView.next.setOnClickListener {
//                Log.i(TAG, "instance count is: ${getInstanceCount()}")
                callback!!.onSelected()
            }


        rootView.finish.setOnClickListener {
            callback!!.onFinish()
        }
        return rootView
    }
}
