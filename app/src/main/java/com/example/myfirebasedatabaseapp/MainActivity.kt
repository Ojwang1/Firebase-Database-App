package com.example.myfirebasedatabaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    var mEdtName:EditText ? =null
    var mEdtEmail:EditText ? =null
    var mEdtNumber:EditText ? =null
    var mBtnSave:Button ? =null
    var mBtnView:Button ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mEdtName=findViewById(R.id.mEdtName)
        mEdtEmail=findViewById(R.id.mEdtEmail)
        mEdtNumber=findViewById(R.id.mEdtNumber)
        mBtnSave=findViewById(R.id.mBtnSave)
        mBtnView=findViewById(R.id.mBtnView)

        mBtnSave!!.setOnClickListener {
            var name=mEdtName!!.text.toString().trim()
            var email=mEdtEmail!!.text.toString().trim()
            var idNumber=mEdtNumber!!.text.toString().trim()
            if (name.isEmpty()){
                mEdtName!!.setError("Please fill this input")
                mEdtName!!.requestFocus()
            }else if (email.isEmpty()){
                mEdtEmail!!.setError("pleae fill this input")
                mEdtEmail!!.requestFocus()
            }else if (idNumber.isEmpty()){
                mEdtNumber!!.setError("Please fill this input")
                mEdtNumber!!.requestFocus()
            }else{
                var time=System.currentTimeMillis().toString()
                var userData=User(name,email,idNumber,time)
                var ref = FirebaseDatabase.getInstance().getReference().child("User/$time")
                ref.setValue(userData).addOnCompleteListener {
                        task->
                    if(task.isSuccessful){
                        Toast.makeText(applicationContext,"User save successfully",
                            Toast.LENGTH_LONG).show()
                    }else{ Toast.makeText(applicationContext,"Saving user failed",
                            Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

        mBtnView!!.setOnClickListener {
            startActivity(Intent( this,UsersActivity::class.java))
        }



    }
}