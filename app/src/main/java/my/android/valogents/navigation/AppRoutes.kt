package my.android.valogents.navigation

import kotlinx.serialization.Serializable

@Serializable
object AgentListScreen

@Serializable
data class AgentDetailScreen(val uuid: String)