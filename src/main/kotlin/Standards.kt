import com.mobilesolutionworks.android.struts.EndPoint
import com.mobilesolutionworks.android.struts.Struts

/**
 * Created by yunarta on 14/5/17.
 */

public inline fun Struts?.map(predicate: (struts: Struts) -> Unit) = if (this != null) predicate(this) else null

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