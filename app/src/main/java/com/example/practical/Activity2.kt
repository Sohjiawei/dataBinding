package com.example.practical

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_main.*

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val message=intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
       textViewReceived.text=String.format("%s : %s",getString(R.string.message),message)

        buttonDone.setOnClickListener{
            val intent = getIntent()
            if (!editTextReply.text.isEmpty()){

                val reply = editTextReply.text.toString()
                intent.putExtra(MainActivity.EXTRA_REPLY, reply)
                setResult(Activity.RESULT_OK, intent)
            }else{
                setResult(Activity.RESULT_CANCELED, intent)
            }
            //close the activity
            finish()
        }
    }
}