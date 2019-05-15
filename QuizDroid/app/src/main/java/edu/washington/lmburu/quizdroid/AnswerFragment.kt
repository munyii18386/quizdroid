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

lateinit  private var repo: TopicRepository

class AnswerFragment : Fragment(), TopicRepository {

    private var callback: OnClickListener? = null

    internal interface OnClickListener{
        fun onSelected()
        fun onFinish(){}
    }

    companion object{

        fun newInstance(): AnswerFragment{
            return AnswerFragment()
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
        repo = QuizApp.instance.getRepository()

                var checkedAns = repo.getUserCheckedAns()
                val topic = repo.getTopicInfo().getValue(repo.getCurrentTopicName())
                var correctAns  = topic.getQuestions()[repo.getIndex()].getCorrectAns()
                var tally = repo.getScore()
                val quizSize = repo.getQuizSize()
                var index = repo.getIndex()

                if(checkedAns.equals(correctAns)){
                    tally++
                }
                if(index == quizSize - 1){
                    rootView.next.visibility = View.INVISIBLE
                }else{
                    index++
                }

                rootView.user_ans.text= checkedAns
                rootView.correct_ans.text = correctAns
                rootView.score.text = "You currently have $tally out of  $quizSize correct."
                repo.updateScore(tally)
                repo.updateIndex(index)
                rootView.next.setOnClickListener {
                    callback!!.onSelected()
                }

                rootView.finish.setOnClickListener {
                    index = 0
                    tally = 0
                    repo.updateScore(tally)
                    repo.updateIndex(index)
                    callback!!.onFinish()
                }
        return rootView
    }
}
