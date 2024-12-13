package my.android.valogents.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import my.android.valogents.ui.viewmodel.AgentListViewModel

@Composable
fun AgentListScreen(agentListViewModel: AgentListViewModel){
        Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                Text("Hello Valorant World!")
        }
}