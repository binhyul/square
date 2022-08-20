package com.example.square

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.square.ui.theme.SquareTheme

@Composable
fun Home(){
    Greeting("Android")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SquareTheme {
        Greeting("Android")
    }
}