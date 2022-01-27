package com.mendozacarolina.cazarpatos

import android.app.Activity
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileExternalManager(val actividad: Activity):
    FileHandler {

    //verifica el estado de la memoria para escritura
    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    override fun SaveInformation(infoGrabacion: Pair<String, String>) {
        if (isExternalStorageWritable()) {
            FileOutputStream(
                File(
                    actividad.getExternalFilesDir(null),
                    SHAREDINFO_FILENAME
                )
            ).bufferedWriter().use { outputStream ->
                outputStream.write(infoGrabacion.first)
                outputStream.write(System.lineSeparator())
                outputStream.write(infoGrabacion.second)
            }
        }
    }

    //verifica el estado de la memoria para lecyura
    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    override fun ReadInformation(): Pair<String, String> {
        var email = ""
        var clave = ""
        try {
            if (isExternalStorageReadable()) {
                FileInputStream(
                    File(
                        actividad.getExternalFilesDir(null),
                        SHAREDINFO_FILENAME
                    )
                ).bufferedReader().use {
                    val datoLeido = it.readText()
                    val textArray = datoLeido.split(System.lineSeparator())
                    email = textArray[0]
                    clave = textArray[1]
                }
            }
            return (email to clave)
        } catch (e: Exception) {
            return "" to ""
        }
    }
}