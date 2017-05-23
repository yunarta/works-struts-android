import com.mobilesolutionworks.android.struts.EndPoint
import com.mobilesolutionworks.android.struts.Struts
import kotlin.reflect.KClass

/**
 * Created by yunarta on 14/5/17.
 */

public inline fun <T : Struts> T?.map(predicate: (struts: T) -> Unit) = if (this != null) predicate(this) else null

public inline fun <T : EndPoint.Contract> T?.map(predicate: (endPoint: T) -> Unit) = if (this != null) predicate(this) else null

public inline fun <T, R> T?.map(function: (T) -> R): R? {
    if (this != null) {
        return function(this)
    } else {
        return null
    }
}

public inline fun <T, R> T?.map(function: (T) -> R, otherwise: ((Throwable) -> Nothing)?): R? {
    try {
        if (this != null) {
            return function(this)
        }
    } catch(t: Throwable) {
        otherwise?.invoke(t)
    }

    return null
}

public inline fun <M1 : EndPoint.Contract, M2 : EndPoint.Contract> Struts.endPoints(endPoint1: KClass<M1>, endPoint2: KClass<M2>, block: (m1: M1, m2: M2) -> Unit): Boolean? {
    val m1: M1? = this[endPoint1]
    val m2: M2? = this[endPoint2]
    when {
        m1 != null && m2 != null -> {
            block(m1, m2)
            return true
        }
        else -> return null
    }
}

public inline fun <M1 : EndPoint.Contract, M2 : EndPoint.Contract, M3: EndPoint.Contract> Struts.endPoints(endPoint1: KClass<M1>, endPoint2: KClass<M2>, endPoint3: KClass<M3>, block: (m1: M1, m2: M2, m3: M3) -> Unit): Boolean? {
    val m1: M1? = this[endPoint1]
    val m2: M2? = this[endPoint2]
    val m3: M3? = this[endPoint3]
    when {
        m1 != null && m2 != null && m3 != null -> {
            block(m1, m2, m3)
            return true
        }
        else -> return null
    }
}