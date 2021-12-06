package com.example.temp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.temp.network.dto.Post
import com.example.temp.network.util.Resource
import com.example.temp.ui.theme.TempTheme
import com.example.temp.ui.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel:PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TempTheme {
                Surface(color = MaterialTheme.colors.background) {
                   ShowPost(viewModel)
                }
            }
        }
    }
}

@Composable
fun ShowPost(viewModel: PostViewModel){
    when(val result = viewModel.state.value){
        is Resource.Success->{
            LazyColumn{
                result.data?.let {
                    items(result.data) { post ->
                        EachRow(post = post)
                    }
                }
            }

        }
        is Resource.Loading->{
            Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp),)
            }

        }
        is Resource.Error->{
           Toast.makeText(LocalContext.current,result.message,Toast.LENGTH_LONG).show()
        }
    }
}


@Composable
fun EachRow(post: Post){
    Card(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth(),
        elevation = 16.dp,
    ) {
        Column {
            Text(
                text = post.title,
                fontSize = 20.sp
            )
            Text(text = post.body)
        }

    }
}
