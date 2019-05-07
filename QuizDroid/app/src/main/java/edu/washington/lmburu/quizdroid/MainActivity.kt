package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = QuizClass()
        val adapter = QuizRecyclerAdapter(data.getQuizOptions())
        myRecyclerView.adapter = adapter
        myRecyclerView.setHasFixedSize(true)
        adapter.onQuizClickedListener = { _, name ->
            val i = Intent(this, MultiUseActivity::class.java)
            i.putExtra("heading", "$name")
            startActivity(i)
        }
    }
}
