package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
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

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, PreferenceFragment())
            .commit()

        adapter.onQuizClickedListener = { _, name ->
            repo.updateCurrentTopic(name)
            val i = Intent(this, MultiUseActivity::class.java)
            startActivity(i)
        }
    }


}
