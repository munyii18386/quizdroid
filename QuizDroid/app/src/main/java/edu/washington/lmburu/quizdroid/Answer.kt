package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.answer.*

class Answer : AppCompatActivity() {
    private val TAG = "Answer"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.answer)
        val data = QuizClass()
        val extras = intent.extras ?: return
        val subject = extras.getString("subject")
        var count = extras.getString("count").toInt()
        var scoreCount = extras.getString("scoreCount").toInt()
        var questionCount = extras.getString("questionCount").toInt()
        val checkedAnswer = extras.getString("checkedAnswer")
        val correctAnswer = data.correctAnswer(subject, count)
        val totalQuestions = data.quizSize(subject)

        if(checkedAnswer.equals(correctAnswer)){
            scoreCount++
        }

        if(questionCount == totalQuestions){
            next.visibility = View.INVISIBLE
        }

        user_ans.text = checkedAnswer
        correct_ans.text = correctAnswer
        score.text = "You currently have $scoreCount out of $totalQuestions correct."

        next.setOnClickListener {
            count++
            val i = Intent(this, Question::class.java)
            i.putExtra("subject", subject)
            i.putExtra("count", "$count")
            i.putExtra("scoreCount", "$scoreCount")
            i.putExtra("questionCount", "$questionCount")
            startActivity(i)
        }

        finish.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}
