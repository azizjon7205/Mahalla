package uz.frankie.mahalla.utils

import android.content.Context
import androidx.annotation.StringRes
import uz.frankie.mahalla.R
import java.io.IOException

sealed class UiText {
    data class DynamicString(@StringRes val resId: Int) : UiText()
    data class ErrorMessage(val message: String) : UiText()
    data class StaticString(val e: Exception? = null) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> context.getString(resId)
            is ErrorMessage -> message
            is StaticString -> {
                if (e is IOException) {
                    context.getString(R.string.check_your_connection)
                } else {
                    context.getString(R.string.something_is_wrong)
                }
            }
        }
    }

}
