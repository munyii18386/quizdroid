package edu.washington.lmburu.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log

private val TAG = "QuizApp"

class QuizApp: Application(), TopicRepository {
    init {
        /*
        *  every time init is called increment instance count
        *  just in case somehow we break singleton rule, this will be
        *  called more than once and myInstancesCount > 1 == true
        */
        ++myInstancesCount
    }

    companion object{
        var myInstancesCount = 0
//        private var instance: QuizApp? = null
//        @Synchronized
//        fun getInstance(): QuizApp {
//            if(instance == null){
//                instance = QuizApp()
//            }
//         return  instance as QuizApp
//        }


    }


    fun getRepository(): TopicRepository {
        return TopicRepository.getInstance()
    }


}

