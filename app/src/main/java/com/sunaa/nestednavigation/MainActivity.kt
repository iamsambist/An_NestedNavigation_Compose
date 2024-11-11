package com.sunaa.nestednavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sunaa.nestednavigation.navigation.MainNavigation
import com.sunaa.nestednavigation.ui.theme.NestedNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NestedNavigationTheme {
              MainNavigation()
            }
        }
    }
}

