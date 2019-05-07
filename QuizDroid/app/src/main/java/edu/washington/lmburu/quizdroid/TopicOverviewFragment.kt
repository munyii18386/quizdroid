package edu.washington.lmburu.quizdroid


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_topic_overview.view.*


class TopicOverviewFragment : Fragment() {

    private var callback: OnBeginSelected? = null

    internal interface OnBeginSelected{
        fun onSelected(subject: String, index: String, scoreCount: String)
    }

    companion object{
        private const val HEADING = "heading"
        private const val SUMMARY = "summary"
        //new instance of topic overview fragment
        fun newInstance(myHeading: String?, mySummary: String?): TopicOverviewFragment{
            val args = Bundle().apply {
                putString(HEADING, myHeading)
                putString(SUMMARY, mySummary)
            }
            return TopicOverviewFragment().apply {
                arguments = args
            }
        }
    }
    //attach context
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as? OnBeginSelected
        if(callback == null){
            throw ClassCastException("$context must implement OnBeginSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_topic_overview, container, false)
        arguments?.let {
            rootView.heading.text = it.getString(HEADING) + "Quiz"
            rootView.topic_overview.text = it.getString(SUMMARY)
            val subject = it.getString(HEADING)
            val index = "1"
            val scoreCount = "0"
            rootView.btn_begin.setOnClickListener {
                callback!!.onSelected(subject, index, scoreCount)
            }
        }
        return rootView
    }

}
