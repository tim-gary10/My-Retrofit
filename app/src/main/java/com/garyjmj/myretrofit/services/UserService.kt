package com.garyjmj.myretrofit.services

import com.garyjmj.myretrofit.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    // https://jsonplaceholder.typicode.com/posts
    @GET("/posts")
    fun getUserList(): Call<List<User>>

    // https://jsonplaceholder.typicode.com/posts/3
    // @Patch Parameter
    @GET("/posts/{id}")
    fun getUser(@Path("id") id:Int): Call<List<User>>

    // https://jsonplaceholder.typicode.com/posts?userId=2
    // @Query Parameter
    @GET("/posts")
    fun getUserId(@Query("userId") userId:Int): Call<List<User>>

    @GET("/posts")
    fun getUserQmap(@QueryMap filter: HashMap<String, String>): Call<List<User>>

    @POST("/posts")
    fun addUser(@Body newUser: User): Call<User>

    @FormUrlEncoded
    @PUT("/posts/{userId}")
    fun updateUser(
            @Path("userId") userId: Int,
            @Field("id") id: Int,
            @Field("title") title: String,
            @Field("body") body: String
    ): Call<User>

    @DELETE("/posts/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Unit>
}