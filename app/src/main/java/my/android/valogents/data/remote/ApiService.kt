package my.android.valogents.data.remote

import my.android.valogents.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("agents")
    suspend fun getAllAgents(@Query("isPlayableCharacter") isPlayableCharacter: Boolean): ApiResponse
}