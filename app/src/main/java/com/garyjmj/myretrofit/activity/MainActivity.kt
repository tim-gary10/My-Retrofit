package com.garyjmj.myretrofit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.garyjmj.myretrofit.OnUserItemClickListener
import com.garyjmj.myretrofit.R
import com.garyjmj.myretrofit.RecyclerAdapter
import com.garyjmj.myretrofit.User
import com.garyjmj.myretrofit.services.ServiceBuilder
import com.garyjmj.myretrofit.services.UserService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnUserItemClickListener {

//    lateinit var recyclerViewAdapter: RecyclerAdapter

    companion object{
        val USERS = "USERS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.setHasFixedSize(true)
        getRecyclerView()

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getRecyclerView(){

        // QueryMap
//        val filter = HashMap<String, String>()
//        filter["userId"] = "2"
//        filter["count"] = "3"

        val destinationService = ServiceBuilder.buildService(UserService::class.java)

        val requestCall = destinationService.getUserList()

        requestCall.enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful){
                    val destinationList = response.body()!!

//                    recyclerViewAdapter = RecyclerAdapter(destinationList, this@MainActivity)
//                    adapter = recyclerViewAdapter
                    myRecyclerView.adapter = RecyclerAdapter(destinationList, this@MainActivity)

                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failure", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()

        getRecyclerView()
    }


    override fun onItemClick(item: User, position: Int) {

        val users = User(item.userId, item.id, item.title, item.body)

        val intent = Intent(this, UpdateAndDelete::class.java)

        intent.putExtra(USERS, users)
        startActivity(intent)
    }



}