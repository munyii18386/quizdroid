//package edu.washington.lmburu.quizdroid
//
//import android.content.Context
//import android.net.Uri
//import android.os.Bundle
//import android.support.v4.app.Fragment
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import kotlinx.android.synthetic.main.fragment_answer.*
//import kotlinx.android.synthetic.main.fragment_answer.view.*
//
//private val TAG = "AnswerFragment"
//class AnswerFragment : Fragment(), TopicRepository {
//
//    private var callback: OnClickListener? = null
//
//    internal interface OnClickListener{
//        fun onSelected()
//        fun onFinish(){}
//    }
//    companion object{
//
//        fun newInstance(): AnswerFragment{
//            return AnswerFragment()
//        }
//    }
//
//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        callback = context as? OnClickListener
//        if(callback == null){
//            throw ClassCastException("$context must implement OnClickListener")
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        val rootView =inflater.inflate(R.layout.fragment_answer, container, false)
//
//            val subject = getSubject()
//            var index = getIndex()
//            val checkedAnswer = getUserCheckedAnswer()
//            var scoreCount = getTotalTally()
//            val correctAnswer = correctAnswer(subject, index)
//            val totalQuestions = quizSize(subject)
//            Log.i(TAG, "total questions: $totalQuestions")
//            //update user's score count
//            if(checkedAnswer.equals(correctAnswer)){
//                scoreCount++
//                setTotalTally(scoreCount)
//            }
//
//            //if this is the last question, do not show 'next' button
//            if(index == totalQuestions){
//                rootView.next.visibility = View.INVISIBLE
//            } else{
//                index++
//                setIndex(index)
//            }
//
//            //update the answer fragment with corresponding values
//            rootView.user_ans.text= checkedAnswer
//            rootView.correct_ans.text = correctAnswer
//            rootView.score.text = "You currently have $scoreCount out of $totalQuestions correct."
//
//            rootView.next.setOnClickListener {
////                Log.i(TAG, "instance count is: ${getInstanceCount()}")
//                callback!!.onSelected()
//            }
//
//
//        rootView.finish.setOnClickListener {
//            callback!!.onFinish()
//        }
//        return rootView
//    }
//}
