package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException


private val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repo = QuizApp.instance.getRepository()
        val topics= repo.getTopics()
        val adapter = QuizRecyclerAdapter(topics)
        myRecyclerView.adapter = adapter
        myRecyclerView.setHasFixedSize(true)

        adapter.onQuizClickedListener = { _, name ->
            repo.updateCurrentTopic(name)
            val i = Intent(this, MultiUseActivity::class.java)
            startActivity(i)
        }
    }


}
