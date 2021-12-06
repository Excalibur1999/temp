package com.example.temp.network

import com.example.temp.network.dto.Post
import retrofit2.http.GET

interface ApiResponse {

   @GET("/posts")
   suspend fun getPosts(): List<Post>
}