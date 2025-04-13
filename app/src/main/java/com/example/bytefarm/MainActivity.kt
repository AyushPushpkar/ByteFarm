package com.example.bytefarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bytefarm.ui.theme.BytefarmTheme
import com.example.vsgarments.navigation.App_Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BytefarmTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App_Navigation(modifier = Modifier.padding(innerPadding) )
                }
            }
        }
    }
}
