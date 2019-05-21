package edu.washington.lmburu.quizdroid

import android.app.AlarmManager
import android.content.Context
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
        var myUrl = ""
        var myInterval = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        sharedPreferences = getSharedPreferences(PREF, Context.MODE_PRIVATE)

        tv_refresh_count.isEnabled = false
        tv_refresh_button.isEnabled = false

        tv_url.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                tv_refresh_count.isEnabled = !tv_url.text.isBlank()


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        tv_refresh_count.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(!tv_refresh_count.text.toString().startsWith("0")){
                    tv_refresh_button.isEnabled = !tv_refresh_count.text.isBlank()
                }else{
                    Toast.makeText(this@PreferenceSettings, "Interval has to be greater than zero", Toast.LENGTH_LONG).show()
                }

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        tv_refresh_button.setOnClickListener {
            myUrl = tv_url.text.toString()
            myInterval = tv_refresh_count.text.toString().toInt()
            if(tv_refresh_button.text == "RETRIEVE DATA"){
                sharedPreferences.edit()
                    .putString(URL, myUrl)
                    .putInt(INTERVAL, myInterval)
                    .putString(ALARM, "scheduled")
                    .apply()
                tv_refresh_button.text = "STOP"
            } else{
                sharedPreferences.edit()
                    .remove(ALARM)
                    .putString(ALARM, "stopped")
                    .apply()
                tv_refresh_button.text = "RETRIEVE DATA"
            }

        }




    }
}
