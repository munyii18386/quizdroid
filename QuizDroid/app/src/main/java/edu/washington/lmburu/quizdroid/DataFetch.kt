package edu.washington.lmburu.quizdroid


import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.app.PendingIntent.getBroadcast
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.File
import java.net.URL


private const val TAG = "fetch"

class DataFetch: BroadcastReceiver() {
    companion object{
        val FILE_NAME = "questions.json" //internal file name
        const val PREF = "pref"
        val instance = QuizApp.instance
        const val URL = "url"
        const val INTERVAL = "interval"
        const val ALARM = "alarm"
        const val DEFAULT_URL = "default"
        val defaultUrl = "http://tednewardsandbox.site44.com/questions.json"
    }


    override fun onReceive(context: Context?, intent: Intent?) {

        val sharedPreferences = instance.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val queue = Volley.newRequestQueue(instance)
//        val url = "http://tednewardsandbox.site44.com/questions.json"
        var givenUrl = sharedPreferences!!.getString(URL,"")
        var givenInterval = sharedPreferences.getString(INTERVAL, "")
        var longInterval = givenInterval.toLong()
        var url = ""
        if(givenUrl == ""){
            givenUrl = sharedPreferences!!.getString(DEFAULT_URL, defaultUrl)
            url = "http://tednewardsandbox.site44.com/questions.json"
        }

         if(!File(FILE_NAME).exists()){
            File(context?.filesDir, FILE_NAME)
        }

//        test url first. If download fails, revert to default url
        val result = testGivenUrl(url)


            Log.i(TAG, "$url")
            // Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                var data = response // test
                instance.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
                    it.write(data.toByteArray())
                    it.close()
                }
            }, Response.ErrorListener {
                Toast.makeText(context, "download failed.", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "download failed: ${it.message}")
            })
            queue.add(stringRequest)




        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
            // Set the alarm here.
            val data = context?.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            val storedInterval = data!!.getString(INTERVAL, "").toLong()
            val alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val pendingIntent = getBroadcast(context, 0, intent, 0)
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ storedInterval, storedInterval, pendingIntent)
        }

    }

    fun getData(){

        val queue = Volley.newRequestQueue(instance)
        val url = "http://tednewardsandbox.site44.com/questions.json"

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                var data = response // test
                instance.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
                    it.write(data.toByteArray())
                    it.close()
                }
            }, Response.ErrorListener {
                Toast.makeText(QuizApp.instance, "download failed.", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "download failed: ${it.message}")
            })
            queue.add(stringRequest)


//        QuizApp.instance.getRepository().getData()
        }




    fun testGivenUrl(url:String): String{
        var result = ""
        StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var data = response // test
                result = "successful"
                instance.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
                    it.write(data.toByteArray())
                    it.close()
                }
            },
            Response.ErrorListener {
                result = "failed"
                Log.i(TAG, "$result: ${it.message}")
            }
        )
        return result
    }







}