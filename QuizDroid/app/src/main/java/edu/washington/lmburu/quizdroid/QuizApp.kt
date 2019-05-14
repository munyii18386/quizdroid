package edu.washington.lmburu.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log

private val TAG = "QuizApp"

class QuizApp: Application() {


    companion object{
       lateinit var instance: QuizApp
        private set

    }
    override fun onCreate(){
        super.onCreate()
        instance = this
        Log.i(TAG, "Quiz App initiated")
    }
    fun getRepository(): TopicRepository {
        return TopicRepository.getInstance()
    }


}

