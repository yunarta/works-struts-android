package com.mobilesolutionworks.android.struts

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.PersistableBundle
import com.mobilesolutionworks.android.struts.jobmanager.Job
import com.mobilesolutionworks.android.struts.jobmanager.PeriodicTiming
import com.mobilesolutionworks.android.struts.jobmanager.SpecificTiming
import com.mobilesolutionworks.android.struts.scheduler.StrutsSchedulerService
import map

/**
 * Created by yunarta on 19/5/17.
 */

internal class SchedulerImpl(val name: String, val context: Context) : Scheduler.Internal {

    val map = HashMap<Int, ((PersistableBundle) -> Boolean)>()

    override fun register(owner: EndPoint<*>, name: String, receiver: (PersistableBundle) -> Boolean) {
        map.put(createKey(System.identityHashCode(owner), name), receiver)
    }

    override fun schedule(owner: EndPoint<*>, name: String, job: Job, bundle: PersistableBundle?) {
        val key = createKey(System.identityHashCode(owner), name)
        map[key].map {
            (context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as? JobScheduler).map {
                val extra = PersistableBundle()
                extra.putString("struts", this@SchedulerImpl.name)
                extra.putPersistableBundle("extras", bundle)

                val builder = JobInfo.Builder(key.hashCode(), ComponentName(context, StrutsSchedulerService::class.java))
                builder.setRequiresCharging(job.charging)
                builder.setRequiresDeviceIdle(job.idle)

                when (job.timing) {
                    is SpecificTiming -> {
                        builder.setMinimumLatency(job.timing.delay)
                        builder.setOverrideDeadline(job.timing.deadline)
                    }

                    is PeriodicTiming -> {
                        builder.setPeriodic(job.timing.interval)
                    }
                }

                builder.setRequiredNetworkType(when (job.networkType) {
                    Job.NetworkType.NONE -> JobInfo.NETWORK_TYPE_NONE
                    Job.NetworkType.ANY -> JobInfo.NETWORK_TYPE_ANY
                    Job.NetworkType.UNMETERED -> JobInfo.NETWORK_TYPE_UNMETERED
                })

                builder.setPersisted(job.persisted)

                it.schedule(builder.setExtras(extra).build())
            }
        } ?: throw IllegalArgumentException("no scheduler named $name, registered for $owner")
    }

    override fun cancel(owner: EndPoint<*>, name: String) {
        val key = createKey(System.identityHashCode(owner), name)
        (context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as? JobScheduler).map {
            it.cancel(key)
        }
    }

    fun process(params: JobParameters): Boolean {
        return map[params.jobId].map {
            it(params.extras)
        } ?: false
    }

    fun createKey(hash: Int, name: String? = null): Int {
        return hash + (name?.hashCode() ?: 0)
    }
}
