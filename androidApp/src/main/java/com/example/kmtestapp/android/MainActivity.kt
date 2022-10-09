package com.example.kmtestapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    //    val provider by inject<Provider>()
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }

        }
    }

    @Composable
    fun Greeting() {

        val state by viewModel.state.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            Button(onClick = {
                viewModel.getUsersApi()
            }) {
                Text(text = "Get remote data")
            }


            Button(onClick = {
                viewModel.getUsersDB()
            }) {
                Text(text = "Get local data")
            }

            when {
                state.data != null -> {
                    state.data!!.forEach {
                        Text(text = it.toString(), modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
                    }
                }
                state.pending == true -> Text(text = "pending")
                state.error != null -> Text(text = state.error!!.message.toString())
            }
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
            Greeting()
        }
    }
}
