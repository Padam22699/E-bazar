package com.example.e_bazar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.SinginR
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var userEmail: EditText
    lateinit var userPhone: EditText
    lateinit var userPass: EditText
    lateinit var Repass: EditText
    lateinit var DB: Dbhelper
    lateinit var ref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        this.supportActionBar?.hide()



        username = findViewById(R.id.UserNameM)
        userEmail = findViewById(R.id.EmailR)
        userPhone = findViewById(R.id.PhoneR)
        userPass = findViewById(R.id.passM)
        Repass = findViewById(R.id.confirmPassR)






        DB = Dbhelper(this)
        var db = DB.writableDatabase
        SignupR.setOnClickListener {

            if (TextUtils.isEmpty(userEmail.text.toString()) || !userEmail.text.contains("@gmail.com")) {
                Toast.makeText(
                    this ,
                    "plz Enter correct email in format @gmail.com" ,
                    Toast.LENGTH_SHORT
                ).show()
            } else if (userPhone.length() != 10) {
                Toast.makeText(this , "Phone number should be 10 Digit" , Toast.LENGTH_SHORT).show()
            } else if (userPass.length() != 8) {
                Toast.makeText(this , "Password should be 8 Digit" , Toast.LENGTH_SHORT).show()
            } else if (username.text.toString() != "" && userPass.text.toString() != "") {
                db()

            } else {
                Toast.makeText(this , "Emter Name And Password" , Toast.LENGTH_LONG).show()
            }

            //================================================================================================


        }

        SinginR.setOnClickListener {
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }
    }

    fun db() {



        var user = username.text.toString()
        var pass = userPass.text.toString()
        var Cpass = Repass.text.toString()
        var email=userEmail.text.toString()

        ref = getSharedPreferences("login" , Context.MODE_PRIVATE)
        var editor = ref.edit()
        editor.putString("username", user)
        editor.apply()


        if (user.equals("") || pass.equals("") || Cpass.equals("")) {
            Toast.makeText(this , "Pelase Enter all field" , Toast.LENGTH_SHORT).show()
        } else {
            if (pass.equals(Cpass)) {
                var checkuers: Boolean = DB.checkUsername(user)
                if (checkuers == false) {
                    var insert: Boolean = DB.insertData(user , pass)
                    if (insert == true) {

                        Toast.makeText(this , "Register Succssefuly $user" , Toast.LENGTH_SHORT)
                            .show()
                        ref.edit().putBoolean("login" , true).apply()

                        var intent = Intent(this , DeshBoardActivity::class.java)
                        intent.putExtra("username" , user)
                        startActivity(intent)
                        finish()


                    } else {
                        Toast.makeText(this , "Registration Failed" , Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this ,
                        "User is Already exist! please Signin" ,
                        Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this , "Password not matching" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}