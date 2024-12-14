package my.android.valogents.data.model

data class ApiResponse(
    val `data`: List<Agent>,
    val status: Int
)