package com.learn.mysunfloweapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.themeadapter.material.MdcTheme
import com.learn.mysunfloweapp.compose.SunflowerApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GardenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Displaying edge to edge
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            MdcTheme {
                SunflowerApp()
            }
        }
    }
}
