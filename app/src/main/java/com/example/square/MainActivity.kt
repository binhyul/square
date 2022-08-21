package com.example.square

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.square.ui.home.model.AssetParamType
import com.example.square.ui.detail.ProductDetail
import com.example.square.ui.detail.ProductDetailViewModel.Companion.PRODUCT_DETAIL
import com.example.square.ui.home.Home
import com.example.square.ui.theme.SquareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SquareTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController)
        }
        composable("detail/{${PRODUCT_DETAIL}}", arguments = listOf(
            navArgument(PRODUCT_DETAIL) { type = AssetParamType() }
        )) {
            ProductDetail()
        }
    }
}
