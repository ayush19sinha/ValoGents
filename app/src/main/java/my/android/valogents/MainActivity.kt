package my.android.valogents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import my.android.valogents.navigation.AppNavigation
import my.android.valogents.ui.theme.ValoGentsTheme
import my.android.valogents.ui.viewmodel.AgentDetailViewModel
import my.android.valogents.ui.viewmodel.AgentListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val agentListViewModel :AgentListViewModel by viewModels()
    private val agentDetailViewModel :AgentDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ValoGentsTheme {
                AppNavigation(agentListViewModel = agentListViewModel,
                    agentDetailViewModel = agentDetailViewModel )
            }
        }
    }
}