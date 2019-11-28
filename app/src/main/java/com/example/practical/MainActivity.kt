package com.example.practical

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.practical.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val myContact = Contact(name = "Soh",phone = "0123456789")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //display UI
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //setContentView(R.layout.activity_main)

        //assign attribute of local variable to UI variable
        binding.contact = myContact

        binding.buttonUpdate.setOnClickListener{
            binding.apply {
                contact?.name="my new name"
                contact?.phone="1111111"
                invalidateAll()//refresh the UI
            }
        }
        //create an event handler for buttonSend
        buttonSend.setOnClickListener{
            sendMessage()
        }
    }

    private fun sendMessage(){
        //create an explicit intent for the SecondActivity
        val intent = Intent(this, Activity2::class.java)
        startActivity(intent)

        //prepare extra
        val message = editTextMessage.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)

        //start an activity with no return value
        //startActivity(intent)

        //start an activity with return value(s)/result(s)
        startActivityForResult(intent, REQUEST_REPLY)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_REPLY){
            if(resultCode == Activity.RESULT_OK){
                val reply= data?.getStringExtra(MainActivity.EXTRA_REPLY)
                textViewReply.text = String.format("%s:%s", getString(R.string.reply),reply)
            }else{
                textViewReply.text = String.format("%s:%s", getString(R.string.reply),"no reply")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        const val EXTRA_MESSAGE= "com.example.practical.MESSAGE"
        const val EXTRA_REPLY= "com.example.practical.reply"
        const val REQUEST_REPLY= 1
    }
}//End of the class
