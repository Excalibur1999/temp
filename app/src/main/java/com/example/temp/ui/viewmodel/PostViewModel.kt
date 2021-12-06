package com.example.temp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.temp.network.dto.Post
import com.example.temp.network.repository.PostRepository
import com.example.temp.network.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(val repo: PostRepository) : ViewModel() {
     val state:MutableState<Resource<List<Post>>> = mutableStateOf(Resource.Loading())

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        repo.getPosts().collect {
          state.value = it
        }
    }
}