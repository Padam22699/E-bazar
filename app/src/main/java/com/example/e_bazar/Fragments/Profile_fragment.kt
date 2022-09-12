package com.example.e_bazar.Fragments

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.e_bazar.MainActivity
import com.example.e_bazar.R
import com.example.e_bazar.databinding.FragmentProfileFragmentBinding
import kotlinx.android.synthetic.main.activity_slash_screen.*
import kotlinx.android.synthetic.main.fragment_profile_fragment.*


class Profile_fragment : Fragment() {
    lateinit var Logout: CardView
    private val pickImage = 100
    private var imageUri: Uri? = null
    lateinit var binding: FragmentProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View? {
        binding = FragmentProfileFragmentBinding.inflate(layoutInflater)

        var ref = activity?.getSharedPreferences("login" , Context.MODE_PRIVATE)
        var name1 = ref?.getString("username" , "User")
        binding.textView11.setText(name1)


        binding.Logout.setOnClickListener {
            ref?.edit()?.putBoolean("login" , false)?.apply()
            startActivity(Intent(this.activity , MainActivity::class.java))
            activity?.finish()
        }
        binding.profileImage.setOnClickListener {
            val galler = Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(galler , pickImage)
        }


        return binding.root
    }

    override fun onActivityResult(requestCode: Int , resultCode: Int , data: Intent?) {
        super.onActivityResult(requestCode , resultCode , data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            binding.profileImage.setImageURI(imageUri)
        }
    }
}
