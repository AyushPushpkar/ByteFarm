package com.example.vsgarments.navigation
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vsgarments.layout.LoginScreen
import com.example.vsgarments.layout.Signup_Screen
import com.example.vsgarments.layout.EmailVerificationScreen
import com.example.vsgarments.layout.HomeScreen
import com.example.vsgarments.layout.IntroductionScreen
import java.net.URLDecoder


@Composable
fun App_Navigation(modifier: Modifier ){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash_Screen.route
    ) {

        composable(route = Screen.Login_Screen.route) {
            LoginScreen(navController= navController , modifier = modifier)
        }

        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController , modifier = modifier)
        }
        composable(route = Screen.Signup_Screen.route){
            Signup_Screen(navController = navController , modifier = modifier)
        }
        composable(
            route = "${Screen.EmailVerificationScreen.route}/{userEmail}" ,
            arguments = listOf(
                navArgument("userEmail"){type = NavType.StringType}
            )
        ){
            val decodedEmail = URLDecoder.decode(it.arguments?.getString("userEmail"), "UTF-8")
            EmailVerificationScreen(navController = navController , modifier = modifier , userEmail = decodedEmail)
        }

        composable(route = Screen.IntroductionScreen.route){
            IntroductionScreen(navController = navController , modifier = modifier)
        }
    }
}
