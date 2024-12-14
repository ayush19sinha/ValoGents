package my.android.valogents.data.repository

import android.util.Log
import my.android.valogents.data.model.Agent
import my.android.valogents.data.remote.ApiService
import javax.inject.Inject

class ValorantRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchAgents(): List<Agent> {
        return try {
            val apiResponse = apiService.getAllAgents(true)
            apiResponse.data
        } catch (e: Exception) {
            Log.e("ApiResponse", "Error fetching agents: ${e.message}")
            emptyList()
        }
    }
}