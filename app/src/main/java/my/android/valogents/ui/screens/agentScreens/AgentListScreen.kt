package my.android.valogents.ui.screens.agentScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import my.android.valogents.ui.screens.agentScreens.components.AgentCard
import my.android.valogents.ui.viewmodel.AgentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentListScreen(agentViewModel: AgentViewModel, onAgentClick:(String)->Unit){
        val agents = agentViewModel.agents.collectAsStateWithLifecycle()
        val loading = agentViewModel.loading.collectAsStateWithLifecycle()
        val error = agentViewModel.error.collectAsStateWithLifecycle()

        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()


        LaunchedEffect(error.value) {
                if (error.value != null){
                        coroutineScope.launch {
                                snackbarHostState.showSnackbar(error.value.toString())
                        }
                }
        }
        Scaffold(
                topBar = {
                        TopAppBar(
                                title = { Text("ValoGent App") },
                                colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = Color(0xFF111922),
                                        titleContentColor = Color.White
                                )
                        )
                }
        ){padding->
        Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding),
                contentAlignment = Alignment.Center)
        {
                if (loading.value){
                        CircularProgressIndicator()
                }
                LazyColumn {
                        items(agents.value){agent->
                                AgentCard(agent = agent, onAgentClick = onAgentClick)
                        }
                }

        }
}
}