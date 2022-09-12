package com.example.e_bazar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_bazar.databinding.ActivityMainBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var gso: GoogleSignInOptions
    lateinit var gsc: GoogleSignInClient
    lateinit var callbackManager: CallbackManager
    private lateinit var binding: ActivityMainBinding
    lateinit var DB: Dbhelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        val ref = getSharedPreferences("login" , Context.MODE_PRIVATE)
        val check: Boolean = ref.getBoolean("login" , false)
        if (check) {
            startActivity(Intent(this , DeshBoardActivity::class.java))
            finish()
        }

        DB = Dbhelper(this)

        SinginM.setOnClickListener {
            val user = UserNameM.text.toString()
            val pass = passM.text.toString()
            if (user.equals("") || pass.equals("")) {
                Toast.makeText(this , "Pelas fill field" , Toast.LENGTH_SHORT).show()
            } else {
                var checkuserpass = DB.checkusernamepassword(user , pass)

                if (checkuserpass == true) {
                    ref.edit().putBoolean("login" , true).apply()
                    Toast.makeText(this , "Well-Come back" , Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this , DeshBoardActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(this , "Account not exit! plz sign up " , Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

        //===========================================================================================================
        var acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            startActivity(Intent(this , DeshBoardActivity::class.java))
            finish()
        }
        signIn()
        facebooklogin()
        googleLogin()

    }

    //-------------------------GoogleLogin-----------------------------
    private fun googleLogin() {
        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this , gso)
        googlelogin.setOnClickListener {
            var acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
            if (acct != null) {
                var ref = getSharedPreferences("login" , Context.MODE_PRIVATE)
                ref.edit().putBoolean("login" , true).apply()
                var googleintent = Intent(this , DeshBoardActivity::class.java)
                googleintent.putExtra("Gkey" , "google")
                startActivity(googleintent)
            }
            signin()
        } }

    private fun signin() {
        var signIntent: Intent = gsc.signInIntent
        startActivityForResult(signIntent , 1000)
    }

    //-----------------------------------------------------------------
    private fun signIn() {
        SinginR.setOnClickListener {
            var intent = Intent(this , Registration::class.java)
            startActivity(intent)
            finish()
        }
    }


    //-----------------FaceBookLogin-----------------------------------------------------
    private fun facebooklogin() {
        callbackManager = CallbackManager.Factory.create()
        var accessToken: AccessToken? = AccessToken.getCurrentAccessToken()
        if (accessToken != null && accessToken.isExpired == false) {
            var fbintent = Intent(this , DeshBoardActivity::class.java)
            fbintent.putExtra("Fkey" , "Facebook")
            startActivity(fbintent)
            finish()
        }
        LoginManager.getInstance()
            .registerCallback(callbackManager , object : FacebookCallback<LoginResult> {

                override fun onSuccess(result: LoginResult) {
                    startActivity(Intent(applicationContext , DeshBoardActivity::class.java))
                    finish()
                }

                override fun onCancel() {
                    startActivity(Intent(applicationContext , DeshBoardActivity::class.java))

                    Toast.makeText(applicationContext , "Login Cancelled" , Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(applicationContext , error.message , Toast.LENGTH_SHORT).show()
                }
            })

        facebook.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this , Arrays.asList("public_profile"));
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int , resultCode: Int , data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode , resultCode , data)
        if (requestCode == 1000) {
            var task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)

                startActivity(Intent(baseContext , DeshBoardActivity::class.java))
                finish()
            } catch (e: ApiException) {
                e.printStackTrace()
                Toast.makeText(applicationContext , "Somthing went Wrong" , Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }
    //=======================================================================================================================
}

