package com.jaguarteam.vacunaspass.localData

import java.io.Serializable

class VacunasCartilla  constructor(
    vacuna:Vacunas,
    fechaAplicacion:String,
    numeroLote:String,
    doctor:String,
    centroAplicacion:String,
    domicilio:String
) : Serializable{
    var vacuna:Vacunas;
    var fechaAplicacion:String;
    var numeroLote:String;
    var doctor:String;
    var centroAplicacion:String;
    var domicilio:String;

    init {
        this.vacuna=vacuna
        this.fechaAplicacion=fechaAplicacion
        this.numeroLote=numeroLote
        this.doctor=doctor
        this.centroAplicacion=centroAplicacion
        this.domicilio=domicilio
    }
}