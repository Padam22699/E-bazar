package com.example.e_bazar.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_bazar.databinding.FragmentSupportFragmentBinding


class support_fragment : Fragment() {
      lateinit var binging:FragmentSupportFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View? {
        binging = FragmentSupportFragmentBinding.inflate(layoutInflater)

        binging.phone.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL , arrayOf("padam@wedigtech.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT , "Queies")
            intent.putExtra(Intent.EXTRA_TEXT , "Please solve this issue asap.")
            startActivity(Intent.createChooser(intent , "Email via"))
        }
             binging.whatsup.setOnClickListener{

                 var msg:Intent=Intent(Intent.ACTION_SENDTO)
                 msg.setData(Uri.parse("smsto:" + Uri.encode("+919057075389")))
                 msg.putExtra("sms_body","Please solve this issue asap.")
                 startActivity(msg)
             }



        return binging.root

    }
}