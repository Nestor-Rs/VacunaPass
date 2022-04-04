package com.jaguarteam.vacunaspass.localData

import java.io.Serializable

class Vacunas constructor(
    nombre:String,
    esquema:String,
    marca:String
):Serializable {
    var nombre:String
    var esquema:String
    var marca:String

    init {
        this.nombre=nombre
        this.esquema=esquema
        this.marca=marca
    }
}