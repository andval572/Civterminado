package com.di.civ

import kotlin.random.Random

class Mapa {

    class PosicionActual(var fila : Int, var columna : Int)

    private var posicionActual = PosicionActual(0,0)

    var matriz = MutableList(Configuracion.filasMapa) {
        MutableList(Configuracion.columnasMapa) {

            val terreno = when (Random.nextInt(0, 100)) {
                in 0..24 -> Terreno.crearLlanura()
                in 25..44 -> Terreno.crearColina()
                in 45..64 -> Terreno.crearBosque()
                in 65..69 -> Terreno.crearCiudad()
                in 70..89 -> Terreno.crearMar()
                in 90..99 -> Terreno.crearMontana()
                else -> Terreno.crearTerrenoDesconocido()
            }
            if(terreno.sePuedeAndarSobreEl)
            terreno.unidad = when(Random.nextInt(1,4)) {
                1 -> Unidad.crearCaballero()
                2 -> Unidad.crearGuerrero()
                3 -> Unidad.crearLancero()
                else -> {null}
            }


            terreno
        }
    }

    fun moverArriba() {
        posicionActual.fila--
    }
    fun moverAbajo() {
        posicionActual.fila++
    }

    fun moverIzquierda() {
        posicionActual.columna--
    }

    fun moverDerecha() {
        posicionActual.columna++
    }

    fun obtenerSubMapa() : MutableList<MutableList<Terreno>>{
        return obtenerSubMapaP()
    }

    private fun obtenerSubMapaP(filaCentro: Int = posicionActual.fila, columnaCentro: Int = posicionActual.columna, vision: Int = Configuracion.rangoVision) : MutableList<MutableList<Terreno>>{

        val subMapa = MutableList(Configuracion.filasCampoVision) {
            MutableList(Configuracion.columnasCampoVision) {
                Terreno.crearTerrenoDesconocido()
            }
        }
        for ((filaActual, filaActualMapaGrande) in ((filaCentro - vision) .. (filaCentro + vision)).withIndex()) {
            println("filaActualMapaGrande = $filaActualMapaGrande")
            println("filaActual = $filaActual")
            for ((columnaActual, columnaActualMapaGrande) in ((columnaCentro - vision)..(columnaCentro + vision)).withIndex()) {
                println("columnaActualMapaGrande = $columnaActualMapaGrande")
                println("ColumnaActual = $columnaActual")

                if (!(columnaActualMapaGrande < 0 || filaActualMapaGrande < 0 || columnaActualMapaGrande >= Configuracion.columnasMapa || filaActualMapaGrande >= Configuracion.filasMapa)){
                    subMapa[filaActual][columnaActual] = matriz[filaActualMapaGrande][columnaActualMapaGrande]
                }
            }
        }
        println(matriz)
        println(subMapa)
        return subMapa
    }

    fun centrarAlCero() {
        posicionActual = PosicionActual(0,0)
    }
    fun obtenerFilaActual(): Int {
        return posicionActual.fila
    }
    fun obtenerColumnaActual(): Int {
        return posicionActual.columna
    }


}