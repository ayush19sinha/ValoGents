package my.android.valogents.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.android.valogents.ui.screens.AgentDetailScreen
import my.android.valogents.ui.screens.AgentListScreen
import my.android.valogents.ui.viewmodel.AgentDetailViewModel
import my.android.valogents.ui.viewmodel.AgentListViewModel

@Composable
fun AppNavigation(agentListViewModel: AgentListViewModel, agentDetailViewModel: AgentDetailViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AgentListScreen){

        composable<AgentListScreen> {
            AgentListScreen(agentListViewModel = agentListViewModel)
        }

        composable<AgentDetailScreen> {
            AgentDetailScreen(agentDetailViewModel = agentDetailViewModel)
        }

    }
}