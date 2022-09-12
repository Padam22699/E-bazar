package com.example.e_bazar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.e_bazar.Fragments.Home_fragment
import com.example.e_bazar.Fragments.Orderd_Fragment
import com.example.e_bazar.Fragments.Profile_fragment
import com.example.e_bazar.Fragments.support_fragment
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_desh_board.*
import org.json.JSONException
import org.json.JSONObject


class DeshBoardActivity : AppCompatActivity() {
     lateinit var analytics: FirebaseAnalytics
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drow: DrawerLayout
    lateinit var gso: GoogleSignInOptions
    lateinit var gsc: GoogleSignInClient
    lateinit var DB: Dbhelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desh_board)
        setSupportActionBar(toolbar)
        analytics=FirebaseAnalytics.getInstance(this)



        val intent:Intent=intent
      if(intent.hasExtra("Fkey")){
          getnamefromfacebook()
      } else if(intent.hasExtra("Gkey")){
          GoogleData()
      }

        DB = Dbhelper(this)
        fragment()
        drawernavigation()


       // var username: String? = intent.getStringExtra("username")
        //     name.setText(username)


       var ref=getSharedPreferences("login", Context.MODE_PRIVATE)

//          SignOutD.setOnClickListener {
//              LoginManager.getInstance().logOut() //for facebook Logout
//              ref.edit().putBoolean("login" , false).apply()
//            startActivity(Intent(this , MainActivity::class.java))
//             finish()
//           // signout()
//        }

    }



    //=====================================================================================================================
    fun GoogleData() {
        var acct: GoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(applicationContext)!!
        if (acct != null) {
            var persionName = acct.displayName
            var persionEmail = acct.email
//            name.setText(persionName)
//            ContactsContract.CommonDataKinds.Email.setText(persionEmail)
        }
    }

    private fun signout() {
        gsc.signOut().addOnCompleteListener {
            finish()
            startActivity(Intent(this , MainActivity::class.java))
        }

    }
    //---------------------------------Facebook UserInformation Set--------------------------------------------------
    private fun getnamefromfacebook() {
        var accessToken: AccessToken? = AccessToken.getCurrentAccessToken()
        val request = GraphRequest.newMeRequest(
            accessToken ,
            object : GraphRequest.GraphJSONObjectCallback {
                override fun onCompleted(
                    `object`: JSONObject? ,
                    response: GraphResponse? ,
                ) {
                    try {
                        var fullname = `object`?.getString("name")
                        //      name.setText(fullname)

//                        var url:String = `object`?.getJSONObject("picture")?.getJSONObject("data")!!.getString("url")
//                        Picasso.get().load(url).into(imageView)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })
        val parameters = Bundle()
        parameters.putString("fields" , "id,name,link,picture.type(large)")
        request.parameters = parameters
        request.executeAsync()
    }

    //==================================================================================================================================
    private fun fragment() {
        val bundle=Bundle()
        bundle.putString("Deshboard","Deshboard Activity")
        analytics.logEvent("deshboard",bundle)
        fragmentreplace(Home_fragment())

        bottomNavigation.setSelectedItemId(R.id.navigation_home);

        bottomNavigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_home -> {
                    val bundle=Bundle()
                    bundle.putString("Deshboard","Deshboard Activity")
                    analytics.logEvent("deshboard",bundle)

                    fragmentreplace(Home_fragment())
                }
                R.id.navigation_Orders -> {
                    val bundle=Bundle()
                    bundle.putString("Order","Order Activity")
                    analytics.logEvent("Order",bundle)
                    fragmentreplace(Orderd_Fragment())
                }
                R.id.navigation_Profiles -> {
                    val bundle=Bundle()
                    bundle.putString("Profile","Profile Activity")
                    analytics.logEvent("Profile",bundle)
                    fragmentreplace(Profile_fragment())
                }
                R.id.navigation_Support -> {
                    val bundle=Bundle()
                    bundle.putString("suport","suport Activity")
                    analytics.logEvent("suport",bundle)
                    fragmentreplace(support_fragment())
                }
            }
            true
        }
    }

    private fun drawernavigation() {
        navbtn.setOnClickListener {
            if (!drawer.isDrawerOpen(GravityCompat.START)) drawer.openDrawer(GravityCompat.START);
            else drawer.closeDrawer(GravityCompat.END);
        }


        navigationdDrawer.setNavigationItemSelectedListener {
            closeDrawer()
            when (it.itemId) {
                R.id.home -> fragmentreplace(Home_fragment())
            }
            true
        }
    }


    private fun fragmentreplace(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framlayout , fragment)
            transaction.commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
 private fun closeDrawer(){
     if(drawer.isDrawerOpen(GravityCompat.START)){
         drawer.closeDrawer(GravityCompat.START)
     }
 }
}
