package uz.frankie.mahalla.utils

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import uz.frankie.mahalla.utils.Constants.APP_PREFS_NAME
import uz.frankie.mahalla.utils.Constants.PREF_ACCESS_TOKEN

class SharedPreferenceHelper(context: Context) {

    private val masterKey by lazy {
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    }

    val preferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            APP_PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun setAccessToken(token: String) {
        preferences.edit {
            putString(PREF_ACCESS_TOKEN, token)
        }
    }

    fun getAccessToken() = preferences.getString(PREF_ACCESS_TOKEN, "empty")

}