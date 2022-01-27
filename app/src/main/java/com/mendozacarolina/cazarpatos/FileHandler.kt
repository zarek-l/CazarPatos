package com.mendozacarolina.cazarpatos

interface FileHandler {
    fun SaveInformation(datosAGrabar:Pair<String,String>)
    fun ReadInformation():Pair<String,String>
}
