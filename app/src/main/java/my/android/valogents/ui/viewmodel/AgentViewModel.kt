package my.android.valogents.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import my.android.valogents.data.model.Agent
import my.android.valogents.data.repository.ValorantRepository
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(private val repository: ValorantRepository) : ViewModel(){

    private val _agents = MutableStateFlow<List<Agent>>(emptyList())
    val agents : StateFlow<List<Agent>> = _agents

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchAgents()
    }

    private fun fetchAgents() {
        _loading.value = true

        viewModelScope.launch {
            try {
                val agentList = repository.fetchAgents()
                _agents.value = agentList
            } catch (e: Exception) {
                _error.value = "Failed to fetch agents: ${e.message}"
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun getAgentById(agentId: String): Flow<Agent?> {
        return _agents.map { agentsList ->
            agentsList.find { it.uuid == agentId }
        }
    }
}