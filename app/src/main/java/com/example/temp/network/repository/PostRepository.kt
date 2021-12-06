package com.example.temp.network.repository

import com.example.temp.network.ApiResponse
import com.example.temp.network.dto.Post
import com.example.temp.network.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostRepository @Inject constructor(private val api: ApiResponse) {
    fun getPosts(): Flow<Resource<List<Post>>> = flow {

        try {
            emit(Resource.Loading())
            val response = api.getPosts()
            emit(Resource.Success<List<Post>>(response))

        } catch (e: HttpException) {
            emit(Resource.Error<List<Post>>(e.localizedMessage ?: "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Post>>(message = "couldn't reach to the server!!. Please check your internet connection"))
        }
    }.flowOn(Dispatchers.IO)

}
