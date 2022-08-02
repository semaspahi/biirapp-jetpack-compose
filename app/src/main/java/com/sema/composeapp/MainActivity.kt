package com.sema.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sema.composeapp.ui.theme.BiirrAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import com.sema.base.R
import com.sema.composeapp.ui.navigation.NavGraph
import com.sema.composeapp.ui.theme.colorSnow


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiirAppUIMain()
        }
    }
}

@Composable
fun BiirAppUIMain() {
    BiirrAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavGraph()
        }
    }
}
