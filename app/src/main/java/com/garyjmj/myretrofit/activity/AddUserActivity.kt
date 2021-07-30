package com.garyjmj.myretrofit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.garyjmj.myretrofit.R
import com.garyjmj.myretrofit.RecyclerAdapter
import com.garyjmj.myretrofit.User
import com.garyjmj.myretrofit.services.ServiceBuilder
import com.garyjmj.myretrofit.services.UserService
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        add_btn.setOnClickListener {
            val userId = add_userId_et.text
            val id = add_id_et.text
            val title = add_title_et.text.toString()
            val body = add_body_et.text.toString()

            val newUser = User(Integer.parseInt(userId.toString()), Integer.parseInt(id.toString()), title, body)


            val userService = ServiceBuilder.buildService(UserService::class.java)

            val requestCall = userService.addUser(newUser)
            requestCall.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful){
                        finish()
                        val createNewUser = response.body()
                        Toast.makeText(this@AddUserActivity,"Success", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@AddUserActivity,"else fail", Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@AddUserActivity,"Failure", Toast.LENGTH_LONG).show()
                }


            })
        }
    }
}