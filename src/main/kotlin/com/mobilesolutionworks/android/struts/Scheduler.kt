package com.mobilesolutionworks.android.struts

import android.os.PersistableBundle
import com.mobilesolutionworks.android.struts.jobmanager.Job

interface Scheduler {

    interface Install : Scheduler {

        fun register(name: String, receiver: (PersistableBundle) -> Boolean)
    }

    interface BackEnd : Scheduler {

        fun schedule(name: String, job: Job, bundle: PersistableBundle? = null)

        fun cancel(name: String)
    }

    interface Internal : Scheduler {

        fun register(owner: EndPoint<*>, name: String, receiver: (PersistableBundle) -> Boolean)

        fun schedule(owner: EndPoint<*>, name: String, job: Job, bundle: PersistableBundle? = null)

        fun cancel(owner: EndPoint<*>, name: String)
    }
}