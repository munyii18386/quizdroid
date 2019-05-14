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
import javax.security.auth.Subject.getSubject

//private val TAG = "TopicOverviewFragment"
class TopicOverviewFragment : Fragment() {

    private var callback: OnBeginSelected? = null

    internal interface OnBeginSelected{
        fun onSelected()
    }

    companion object{
        private const val TOPIC = "Topic"
        private const val DESC = "summary"
        fun newInstance(topic:String, summary: String): TopicOverviewFragment{
            val args = Bundle().apply {
                putString(TOPIC, topic)
                putString(DESC, summary)
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
        arguments?.let{
            rootView.heading.text = it.getString(TOPIC) + " Quiz"
            rootView.topic_overview.text = it.getString(DESC)

            rootView.btn_begin.setOnClickListener {
//                Log.i(TAG, "subject: $subject, ${getIndex()}, ${getTotalTally()}")
                callback!!.onSelected()
            }
        }
        return rootView
    }

}
