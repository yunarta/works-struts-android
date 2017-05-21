package com.mobilesolutionworks.android.struts.jobmanager

/**
 * Created by yunarta on 19/5/17.
 */

sealed class JobTiming

data class PeriodicTiming(
      val interval: Long
) : JobTiming()

data class SpecificTiming(
      val delay: Long = 0,
      val deadline: Long = 0
) : JobTiming()

data class Job(val timing: JobTiming, val networkType: NetworkType = Job.NetworkType.NONE, val idle: Boolean = false, val charging: Boolean = false) {

    var persisted: Boolean = false

    enum class NetworkType {
        NONE,
        ANY,
        UNMETERED
    }
}