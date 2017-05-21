package com.mobilesolutionworks.android.struts

import map


/**
 * Created by yunarta on 13/5/17.
 */

public class Foundation internal constructor(context: android.content.Context) : SelectStruts {

    public val context: android.content.Context = context.applicationContext

    private val strutsMap: MutableMap<String, StrutsImpl>

    private val credentialService: CredentialService

    init {

        strutsMap = java.util.HashMap<String, StrutsImpl>()
        credentialService = CredentialService()
    }


    fun configure(setupInterface: Foundation.ConfigureInterface) {
        setupInterface.setup(Foundation.Configurable(this))
    }

    fun setup(buildInterface: Foundation.BuildInterface) {
        val builder = StrutsBuilder()
        buildInterface.setup(builder)

        val struts = StrutsImpl(buildInterface.name(), context, builder.plugins)
        strutsMap.put(buildInterface.name(), struts)

        struts.start()
    }

    internal fun getInternalStruts(name: String): StrutsImpl? {
        return strutsMap[name]
    }

    fun getStruts(name: String): Struts? {
        val struts = strutsMap[name]
        return when {
            struts != null -> object : Struts by struts {}
            else -> null
        }
    }

    override operator fun get(name: String): Struts? = getStruts(name)

    fun credentialManager(): CredentialService {
        return credentialService
    }

    /**
     * Created by yunarta on 5/5/17.
     */

    interface BuildInterface {

        fun name(): String

        fun setup(builder: StrutsBuilder)
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    interface ConfigureInterface {

        fun setup(foundation: Foundation.Configurable)
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    class Configurable internal constructor(private val foundation: Foundation) {

        val context: android.content.Context
            get() = foundation.context

        fun <T : CredentialManager<*>> installCredentialManager(name: Class<T>, credentialManager: T) {
            foundation.credentialManager().installCredentialManager(name, credentialManager)
        }
    }

    companion object {

        private var instance: Foundation? = null

        public fun create(context: android.content.Context) {
            Foundation.Companion.instance = Foundation(context)
        }

        public fun single(): Foundation {
            return Foundation.Companion.instance!!
        }

        val struts: SelectStruts
            get() = single()
    }
}
