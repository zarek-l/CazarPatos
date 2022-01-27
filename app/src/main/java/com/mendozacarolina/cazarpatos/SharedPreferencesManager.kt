package com.mendozacarolina.cazarpatos

import android.app.Activity
import android.content.Context

class SharedPreferencesManager(val actividad: Activity) :
    FileHandler {
    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        val sharedPref =
            actividad.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(LOGIN_KEY, datosAGrabar.first)
        editor.putString(PASSWORD_KEY, datosAGrabar.second)
        editor.apply()
    }

    override fun ReadInformation(): Pair<String, String> {
        val sharedPref =
            actividad.getPreferences(Context.MODE_PRIVATE)
        val email = sharedPref.getString(LOGIN_KEY, "").toString()
        val clave = sharedPref.getString(PASSWORD_KEY, "").toString()
        return (email to clave)
    }
}

