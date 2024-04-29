package com.example.almatartask.shopping.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.almatartask.shopping.presentation.add_edit_shopping.AddEditShoppingScreen
import com.example.almatartask.shopping.presentation.shopping.ShoppingScreen
import com.example.almatartask.shopping.presentation.shopping.ShoppingViewModel
import com.example.almatartask.shopping.presentation.util.Screen
import com.example.almatartask.ui.theme.AlmatarTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlmatarTaskTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ShoppingScreen.route
                    ) {
                        composable(route = Screen.ShoppingScreen.route) {
                            val viewModel: ShoppingViewModel = hiltViewModel()
                            ShoppingScreen(
                                state = viewModel.state.value,
                                event = viewModel::onEvent,
                                onItemClick = { id ->
                                    navController.navigate(Screen.AddEditShoppingScreen.route + "?shoppingId=${id}")
                                }
                            )
                        }
                        composable(
                            route = Screen.AddEditShoppingScreen.route + "?shoppingId={shoppingId}",
                            arguments = listOf(
                                navArgument(
                                    name = "shoppingId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            AddEditShoppingScreen(navController = navController)
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlmatarTaskTheme {
        Greeting("Android")
    }
}