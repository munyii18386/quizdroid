package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.question.*

class Question: AppCompatActivity() {
    private val TAG = "Question"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question)
        val extras = intent.extras ?: return
        val subject = extras.getString("subject")
        var count = extras.getString("count").toInt()
        var scoreCount = extras.getString("scoreCount")
        var questionCount = extras.getString("questionCount").toInt()
        var checkedAnswer = ""
        questionCount++
        val data = QuizClass()
        if(checkedAnswer.equals("")){
            submit.isClickable = false
        }

        question.text = data.getQuestion(subject, count)
        r_btn_a.text = data.getAnswer(subject, count)[0]
        r_btn_b.text = data.getAnswer(subject, count)[1]
        r_btn_c.text = data.getAnswer(subject, count)[2]
        r_btn_d.text = data.getAnswer(subject, count)[3]

        btn_ans.setOnCheckedChangeListener { btn_ans, i ->
            when(i){
                r_btn_a.id -> checkedAnswer = r_btn_a.text as String
                r_btn_b.id -> checkedAnswer = r_btn_b.text as String
                r_btn_c.id -> checkedAnswer = r_btn_c.text as String
                r_btn_d.id -> checkedAnswer = r_btn_d.text as String
            }
        }

        submit.setOnClickListener{
            val i = Intent(this, Answer::class.java)
            i.putExtra("subject", subject)
            i.putExtra("count", "$count")
            i.putExtra("checkedAnswer", "$checkedAnswer")
            i.putExtra("scoreCount", scoreCount)
            i.putExtra("questionCount", "$questionCount")
            startActivity(i)
        }
    }
}

