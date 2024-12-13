package my.android.valogents.data.model

data class RecruitmentData(
    val counterId: String,
    val endDate: String,
    val levelVpCostOverride: Int,
    val milestoneId: String,
    val milestoneThreshold: Int,
    val startDate: String,
    val useLevelVpCostOverride: Boolean
)