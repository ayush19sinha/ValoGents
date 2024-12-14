package my.android.valogents.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import my.android.valogents.ui.screens.agentScreens.AgentDetailScreen
import my.android.valogents.ui.screens.agentScreens.AgentListScreen
import my.android.valogents.ui.viewmodel.AgentViewModel

@Composable
fun AppNavigation(agentViewModel: AgentViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AgentListScreen){

        composable<AgentListScreen> {
            AgentListScreen(
                agentViewModel = agentViewModel,
                onAgentClick = {uuid->
                    navController.navigate(AgentDetailScreen(uuid = uuid))})
        }

        composable<AgentDetailScreen> { backStackEntry ->
            val agentId: AgentDetailScreen = backStackEntry.toRoute()
            AgentDetailScreen(agentViewModel = agentViewModel, agentId = agentId.uuid,
                onBackClick = { navController.navigateUp() })
        }

    }
}