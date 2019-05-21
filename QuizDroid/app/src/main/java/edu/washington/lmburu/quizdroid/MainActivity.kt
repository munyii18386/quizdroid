package edu.washington.lmburu.quizdroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.settings.*
import org.json.JSONObject
import java.io.IOException

private val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.url -> {
                val i = Intent(this, PreferenceSettings::class.java)
                startActivity(i)
                return true
            }
            R.id.update -> {
                val sharedPreferences = getSharedPreferences(PreferenceSettings.PREF, Context.MODE_PRIVATE)
                val ALARM = "alarm"
                val alarm = sharedPreferences.getString(ALARM, "")
                if(alarm == "scheduled"){
                    Toast.makeText(this, "cancel existing schedule in order to proceed", Toast.LENGTH_LONG).show()
                } else{
                    val fetch = DataFetch()
                    fetch.setUpVolleyFetching()
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

}
