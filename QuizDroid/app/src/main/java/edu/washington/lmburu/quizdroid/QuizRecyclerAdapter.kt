package edu.washington.lmburu.quizdroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.quiz_recycler_adapter.view.*


class QuizRecyclerAdapter (var quizList: List<String>): RecyclerView.Adapter<QuizRecyclerAdapter.QuizViewHolder>(){

    var onQuizClickedListener: ((position: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): QuizViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quiz_recycler_adapter, parent, false)
        return QuizViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    override fun onBindViewHolder(quizView: QuizViewHolder, position: Int) {
        quizView.bindView(quizList[position], position)
    }

    inner class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(quizName: String,  position: Int){
            itemView.subject.text = quizName
            itemView.setOnClickListener{
                onQuizClickedListener?.invoke(position, quizName)
            }
        }

    }
}