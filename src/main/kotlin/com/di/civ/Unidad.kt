package com.di.civ

data class Unidad(var nombre : String, val imagen  : String, val vidaMaxima :Int) {

    override fun toString(): String {
        return nombre
    }
    companion object {



        fun crearCaballero(): Unidad {
            return Unidad("Caballero", "src\\main\\resources\\images\\Knight.png",100 )
        }

        fun crearGuerrero(): Unidad {
            return Unidad("Guerrero", "src\\main\\resources\\images\\Warrior.png",100 )
        }

        fun crearLancero(): Unidad {
            return Unidad("Lancero", "src\\main\\resources\\images\\Heavy-lancer.png",100 )
        }


    }
}