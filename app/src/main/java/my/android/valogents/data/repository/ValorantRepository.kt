package my.android.valogents.data.repository

import my.android.valogents.data.model.Agent
import my.android.valogents.data.remote.ApiService
import javax.inject.Inject

class ValorantRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchAgents():List<Agent>{
        return apiService.getAgents()
    }
}