package my.android.valogents.data.remote

import my.android.valogents.data.model.Agent
import retrofit2.http.GET

interface ApiService {
    @GET("v1/agents")
    suspend fun getAgents():List<Agent>
}