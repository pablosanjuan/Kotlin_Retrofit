package com.pablosj.personal.retrofit_jsonplaceholder_gson

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pablosj.personal.retrofit_jsonplaceholder_gson.data.PostsService
import com.pablosj.personal.retrofit_jsonplaceholder_gson.model.Posts
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
    }

    private fun getPosts() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PostsService::class.java)
        val call = service.getPost()
        call.enqueue(object : Callback<List<Posts>> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (!response.isSuccessful) {
                    jsonText.text = "error: ${response.code()}"
                    return
                }
                val postList = response.body()
                for (post in postList!!) {
                    var contenido = ""
                    contenido += "UserId:" + post.userId + "\n"
                    contenido += "Id:" + post.id + "\n"
                    contenido += "title:" + post.title + "\n"
                    contenido += "body:" + post.body + "\n\n"
                    jsonText.append(contenido)
                }
            }
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
            }
        })
    }
}