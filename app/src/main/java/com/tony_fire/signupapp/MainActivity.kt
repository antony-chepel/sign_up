package com.tony_fire.signupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ThanksActivity
import com.retrofit.RetrofitInstance
import com.retrofit.UserInfo
import com.tony_fire.signupapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userField()
    }

      private fun userField() {
        binding.signButton.setOnClickListener {
            val ed1stName = binding.ed1name.text.toString().trim()
            val ed2stName = binding.ed2name.text.toString().trim()
            val ed_email = binding.edEmail.text.toString().trim()
            val ed_tel = binding.edTel.text.toString().trim()
            val ed_country = binding.edCountry.text.toString().trim()



            if(ed1stName.isEmpty()){
                binding.ed1name.error = "First Name Required"
                binding.ed1name.requestFocus()
                return@setOnClickListener

            }

            if(ed2stName.isEmpty()){
                binding.ed2name.error = "Second Name Required"
                binding.ed2name.requestFocus()
                return@setOnClickListener

            }

            if(ed_email.isEmpty()){
                binding.edEmail.error = "Email Required"
                binding.edEmail.requestFocus()
                return@setOnClickListener

            }

            if(ed_tel.isEmpty()){
                binding.edTel.error = "Phone  Required"
                binding.edTel.requestFocus()
                return@setOnClickListener

            }

            if(ed_country.isEmpty()){
                binding.edCountry.error =   "Required"
                binding.edCountry.requestFocus()
                return@setOnClickListener

            }



            val userinfo = UserInfo(ed_country,ed_email,ed1stName,"5d1f566d48bc9d0b0d07bba4",ed2stName,ed_tel,"ergrege")
            val call : Call<UserInfo> = RetrofitInstance.api.signUpUser(userinfo)

            call.enqueue(object : Callback<UserInfo>{
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    if(response.isSuccessful){
                        Toast.makeText(applicationContext,response.errorBody().toString(),Toast.LENGTH_LONG).show()
                        val i = Intent(this@MainActivity,ThanksActivity::class.java)
                        startActivity(i)
                    }
                    else {
                        Toast.makeText(applicationContext,"Something wrong",Toast.LENGTH_SHORT).show()
                        return
                    }

                }

                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
                }


            })



        }

    }

}

