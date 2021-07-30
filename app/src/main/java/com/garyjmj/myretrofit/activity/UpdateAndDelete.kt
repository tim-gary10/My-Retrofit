package com.garyjmj.myretrofit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.garyjmj.myretrofit.R
import com.garyjmj.myretrofit.User
import com.garyjmj.myretrofit.services.ServiceBuilder
import com.garyjmj.myretrofit.services.UserService
import kotlinx.android.synthetic.main.activity_update_and_delete.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateAndDelete : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_and_delete)

        val users = intent.getParcelableExtra<User>(MainActivity.USERS)

        val userId = userId_et.setText(users?.userId.toString())
        val id = id_et.setText(users?.id.toString())
        val title = title_et.setText(users?.title)
        val body = body_et.setText(users?.body)

        update_btn.setOnClickListener {
//            val userService = ServiceBuilder.buildService(UserService::class.java)
//            val requestCall = userService.updateUser(Integer.parseInt(userId.toString()), Integer.parseInt(id.toString()), title.toString(), body.toString())

            val destinationService = ServiceBuilder.buildService(UserService::class.java)
            val requestCall = destinationService.updateUser(Integer.parseInt(userId.toString()), Integer.parseInt(id.toString()), title.toString(), body.toString())

            requestCall.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful){
                        finish()
                        val updateUser = response.body()
                        Toast.makeText(this@UpdateAndDelete,"Success", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@UpdateAndDelete,"else fail", Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@UpdateAndDelete,"Failure", Toast.LENGTH_LONG).show()
                }


            })
        }

        delete_btn.setOnClickListener {
            val destinationService = ServiceBuilder.buildService(UserService::class.java)
            val requestCall = destinationService.deleteUser(Integer.parseInt(id.toString()))

            requestCall.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful){
                        finish()
                        Toast.makeText(this@UpdateAndDelete,"Success", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@UpdateAndDelete,"else fail", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(this@UpdateAndDelete,"Failure", Toast.LENGTH_LONG).show()
                }


            })

        }


    }
}