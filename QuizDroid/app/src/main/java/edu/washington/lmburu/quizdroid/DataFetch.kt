package edu.washington.lmburu.quizdroid


import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.File
import java.net.URL


private const val TAG = "fetch"

class DataFetch: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val FILE_NAME = "quiz" //internal file name

        fun setUpVolleyFetching() {
            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(QuizApp.instance)
            val url = "http://tednewardsandbox.site44.com/questions.json"


            // Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    var data = response // test
//                    var file = File(QuizApp.instance.filesDir, FILE_NAME)
                    QuizApp.instance.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
                        it.write(data.toByteArray())
                    }
                    // Display the first 500 characters of the response string.
//                    Log.i(TAG, "Response is: ${response.substring(0, 500)}")
                },
                Response.ErrorListener {
                    Log.i(TAG, "download failed: ${it.message}")
                }
            )
            queue.add(stringRequest)

        }







}