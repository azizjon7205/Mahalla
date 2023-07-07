package uz.frankie.mahalla.utils.extentions

import android.util.Base64
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.io.UnsupportedEncodingException

@Throws(UnsupportedEncodingException::class)
fun getJson(strEncoded: String): String? {
    val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
    return String(decodedBytes, Charsets.UTF_8)
}

fun snackBar(view: View?, message: String) {
    view?.let {
        Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}