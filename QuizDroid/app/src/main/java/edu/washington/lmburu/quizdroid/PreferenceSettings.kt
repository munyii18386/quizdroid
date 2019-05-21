package edu.washington.lmburu.quizdroid

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.settings.*

class PreferenceSettings : AppCompatActivity() {
    companion object{
        private lateinit var sharedPreferences: SharedPreferences
        const val PREF = "pref"
        const val URL = "url"
        const val INTERVAL = "interval"
        const val ALARM = "alarm"
        const val DEFAULT_URL = "default"
        const val STATUS = "status"
        var myUrl = "Enter data source url"
        var myInterval = "Refresh Minutes?"
        var status = "STOP"
        val defaultUrl = "http://tednewardsandbox.site44.com/questions.json"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        sharedPreferences = getSharedPreferences(PREF, Context.MODE_PRIVATE)

        sharedPreferences
            .edit()
            .putString(DEFAULT_URL, defaultUrl)
            .putString(URL, myUrl)
            .putString(INTERVAL, myInterval)
            .putString(STATUS, status)
            .apply()

        if(sharedPreferences.getString(ALARM,"scheduled") == "scheduled"){
            tv_url.setText(sharedPreferences.getString(URL, ""))
            tv_refresh_count.setText(sharedPreferences.getString(INTERVAL, ""))
            tv_refresh_button.setText(sharedPreferences.getString(STATUS,""))
            tv_refresh_count.isEnabled = true
            tv_refresh_button.isEnabled = true
        }


        tv_url.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                tv_refresh_count.isEnabled = !tv_url.text.isBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        tv_refresh_count.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(!tv_refresh_count.text.toString().startsWith("0")){
                    tv_refresh_button.isEnabled = !tv_refresh_count.text.isBlank()
                }else{
                    Toast.makeText(this@PreferenceSettings, "Interval has to be greater than zero", Toast.LENGTH_LONG).show()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        val alarmIntent = Intent(this, DataFetch::class.java)
            .let { intent ->
                PendingIntent.getBroadcast(this, 0, intent, 0)
            }

        tv_refresh_button.setOnClickListener {
            myUrl = tv_url.text.toString()
            myInterval = tv_refresh_count.text.toString()
            val longInterval = myInterval.toLong() * 60 * 1000
            if(tv_refresh_button.text == "RETRIEVE DATA"){
                sharedPreferences.edit()
                    .putString(URL, myUrl)
                    .putString(INTERVAL, myInterval)
                    .putString(STATUS, status)
                    .putString(ALARM, "scheduled")
                    .apply()
                tv_refresh_button.text = "STOP"

                alarmMgr?.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ longInterval, longInterval, alarmIntent)
                Toast.makeText(this, "Refresh has been scheduled", Toast.LENGTH_SHORT).show()
            } else{
                alarmMgr?.cancel(alarmIntent)
                sharedPreferences.edit()
                    .remove(ALARM)
                    .putString(ALARM, "stopped")
                    .apply()
                Toast.makeText(this, "Refresh has been stopped", Toast.LENGTH_SHORT).show()
                tv_refresh_button.text = "RETRIEVE DATA"
            }

        }




    }
}
