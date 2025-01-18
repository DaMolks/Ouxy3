package com.damolks.ouxy3.debug

import kotlinx.coroutines.flow.Flow

class DebugManager {
    private val eventMonitor = EventMonitor()
    private val performanceTracker = PerformanceTracker()
    private val moduleDebugger = ModuleDebugger()

    fun startDebugging() {
        eventMonitor.start()
        performanceTracker.start()
        moduleDebugger.start()
    }

    fun getEventLogs(): Flow<EventLog> = eventMonitor.logs
    
    fun getPerformanceMetrics(): Flow<PerformanceMetrics> = performanceTracker.metrics
    
    fun getModuleStates(): Flow<Map<String, ModuleState>> = moduleDebugger.states
}

data class EventLog(
    val timestamp: Long,
    val type: String,
    val source: String,
    val data: Any?
)

data class PerformanceMetrics(
    val moduleLoadTime: Long,
    val memoryUsage: Long,
    val activeModules: Int
)

data class ModuleState(
    val id: String,
    val status: Status,
    val lastError: String?
) {
    enum class Status {
        LOADING, ACTIVE, ERROR, INACTIVE
    }
}