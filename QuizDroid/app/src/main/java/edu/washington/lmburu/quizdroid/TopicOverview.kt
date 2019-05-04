package edu.washington.lmburu.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.washington.lmburu.quizdroid.R.*
import kotlinx.android.synthetic.main.topic_overview.*


class TopicOverview: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.topic_overview)

        val extras = intent.extras ?: return
        val subject = extras.getString("heading")

        heading.text = "$subject Quiz"
        val text: String = getString(string.about_math)
        topic_overview?.setText(text)

        btn_begin.setOnClickListener{

            val i = Intent(this, Question::class.java)
            i.putExtra("subject", "$subject")
            startActivity(i)
        }


    }
}