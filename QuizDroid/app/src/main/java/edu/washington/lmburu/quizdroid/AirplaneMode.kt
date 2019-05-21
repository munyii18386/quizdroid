package edu.washington.lmburu.quizdroid



import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class AirplaneMode : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder =  AlertDialog.Builder(QuizApp.instance)
        builder.setMessage("Airplane mode is on. Turn it off?")
            .setPositiveButton("Yes please.", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    startActivityForResult(Intent(android.provider.Settings.ACTION_SETTINGS), 0)
                }
            })
            .setNegativeButton("No, Thank you.") { _, _ -> }
        return builder.create()
//        super.onCreateDialog(savedInstanceState)
    }

}
