package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*




private val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = this.application as QuizApp
        val topics= app.getRepository().getTopics()


        val adapter = QuizRecyclerAdapter(topics)
        myRecyclerView.adapter = adapter
        myRecyclerView.setHasFixedSize(true)
        adapter.onQuizClickedListener = { _, name ->
            app.currentTopic(name)
            val i = Intent(this, MultiUseActivity::class.java)
            startActivity(i)
        }
    }
}
