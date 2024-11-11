package com.sunaa.nestednavigation.auth.subauth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sunaa.nestednavigation.navigation.Dest

@Composable
fun AuthTwoScreen(args : Dest.AuthSecondScreen ,onClick : ()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 3.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Passing Args name = ${args.name} and age = ${args.age}", modifier = Modifier.clickable {
            onClick()
        })
    }
}