package edu.washington.lmburu.quizdroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class PreferenceFragment : PreferenceFragmentCompact() {

    override fun onCreatePreferences(savedInstance: Bundle, rootKey: String){
        setPreferencesFromResource(R.xml.preference, rootKey)
    }






}
