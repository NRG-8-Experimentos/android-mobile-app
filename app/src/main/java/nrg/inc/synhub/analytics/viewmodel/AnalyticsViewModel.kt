package com.example.synhub.analytics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synhub.analytics.application.dto.AnalyticsResponse
import com.example.synhub.analytics.application.dto.GroupMemberCountResponse
import com.example.synhub.analytics.application.dto.TaskTimePassedResponse
import com.example.synhub.analytics.model.response.AnalyticsWebService
import com.example.synhub.shared.model.client.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AnalyticsState(
    val taskOverview: AnalyticsResponse? = null,
    val taskDistribution: AnalyticsResponse? = null,
    val rescheduledTasks: AnalyticsResponse? = null,
    val avgCompletionTime: AnalyticsResponse? = null,
    val groupMemberCount: GroupMemberCountResponse? = null,
    val taskTimePassed: TaskTimePassedResponse? = null
)

class AnalyticsViewModel : ViewModel() {
    private val analyticsApi = RetrofitClient.analyticsWebService as AnalyticsWebService

    private val _analyticsState = MutableStateFlow(AnalyticsState())
    val analyticsState: StateFlow<AnalyticsState> = _analyticsState.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun fetchAnalyticsData(token: String) {
        viewModelScope.launch {
            _loading.value = true

            val taskOverview = analyticsApi.getTaskOverview().body()
            val taskDistribution = analyticsApi.getTaskDistribution().body()
            val rescheduledTasks = analyticsApi.getRescheduledTasks().body()
            val avgCompletionTime = analyticsApi.getAvgCompletionTime().body()

            _analyticsState.value = AnalyticsState(
                taskOverview = taskOverview,
                taskDistribution = taskDistribution,
                rescheduledTasks = rescheduledTasks,
                avgCompletionTime = avgCompletionTime
            )
            _loading.value = false
        }
    }
}

