package com.mobilesolutionworks.android.struts.scheduler

import android.app.job.JobParameters
import android.app.job.JobService
import com.mobilesolutionworks.android.struts.Foundation
import com.mobilesolutionworks.android.struts.StrutsImpl
import map

/**
 * Created by yunarta on 18/5/17.
 */

class StrutsSchedulerService : JobService() {

    override fun onStartJob(params: JobParameters): Boolean {
        params.strings["struts"].map {
            val internalStruts: StrutsImpl? = Foundation.single().getInternalStruts(it)
            if (internalStruts != null) {
                jobFinished(params, internalStruts.scheduler.process(params))
            }
        }

        jobFinished(params, false)
        return false
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return false
    }
}