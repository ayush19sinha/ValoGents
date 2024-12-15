package my.android.valogents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import my.android.valogents.navigation.AppNavigation
import my.android.valogents.ui.theme.ValoGentsTheme
import my.android.valogents.ui.viewmodel.AgentViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val agentViewModel :AgentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ValoGentsTheme {
                AppNavigation(agentViewModel = agentViewModel)
            }
        }
    }
}