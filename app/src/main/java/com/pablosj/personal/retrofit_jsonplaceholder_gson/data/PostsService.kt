package com.pablosj.personal.retrofit_jsonplaceholder_gson.data

import com.pablosj.personal.retrofit_jsonplaceholder_gson.model.Posts
import retrofit2.Call
import retrofit2.http.GET

interface PostsService {

    @GET("posts")
    fun getPost(): Call<List<Posts>>
}