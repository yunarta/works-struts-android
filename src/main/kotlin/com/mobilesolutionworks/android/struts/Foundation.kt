package com.mobilesolutionworks.android.struts

/**
 * Created by yunarta on 13/5/17.
 */

public class Foundation internal constructor(context: android.content.Context) : SelectStruts {

    public val context: android.content.Context = context.applicationContext

    private val strutsMap: MutableMap<String, Struts>

    private val credentialService: CredentialService

    init {

        strutsMap = java.util.HashMap<String, Struts>()
        credentialService = CredentialService()
    }


    fun configure(setupInterface: com.mobilesolutionworks.android.struts.Foundation.ConfigureInterface) {
        setupInterface.setup(com.mobilesolutionworks.android.struts.Foundation.Configurable(this))
    }

    fun setup(buildInterface: com.mobilesolutionworks.android.struts.Foundation.BuildInterface) {
        val struts = StrutsImpl(context)
        strutsMap.put(buildInterface.name(), struts)

        buildInterface.setup(struts)
        struts.start()
    }

    fun getStruts(name: String): Struts? {
        return strutsMap[name]
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

        fun setup(struts: Struts.Setup)
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    interface ConfigureInterface {

        fun setup(foundation: com.mobilesolutionworks.android.struts.Foundation.Configurable)
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    class Configurable internal constructor(private val foundation: com.mobilesolutionworks.android.struts.Foundation) {

        val context: android.content.Context
            get() = foundation.context

        fun <T : CredentialManager<*>> installCredentialManager(name: Class<T>, credentialManager: T) {
            foundation.credentialManager().installCredentialManager(name, credentialManager)
        }
    }

    companion object {

        private var instance: com.mobilesolutionworks.android.struts.Foundation? = null

        public fun create(context: android.content.Context) {
            com.mobilesolutionworks.android.struts.Foundation.Companion.instance = com.mobilesolutionworks.android.struts.Foundation(context)
        }

        public fun single(): com.mobilesolutionworks.android.struts.Foundation {
            return com.mobilesolutionworks.android.struts.Foundation.Companion.instance!!
        }

        val struts: SelectStruts
            get() = single()
    }
}
