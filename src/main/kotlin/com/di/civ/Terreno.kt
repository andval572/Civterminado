package com.di.civ

data class Terreno(var nombre : String, val imagen  : String, val sePuedeAndarSobreEl : Boolean, val colorTerreno: String, val colorTexto: String) {

    override fun toString(): String {
        return nombre
    }
    companion object {

        const val  SAQUEADO = "Saqueado"
        const val  CONQUISTADO = "Conquistado"
        const val  CON_MINA = "Con mina"
        const val  CON_GRANJA = "Con Granja"

        fun crearLlanura(): Terreno {
            return Terreno("Llanura", "src\\main\\resources\\images\\llanura.png", true, "#70E9F5", "#22750B" )
        }

        fun crearColina(): Terreno {
            return Terreno("Colina", "src\\main\\resources\\images\\colina.png", true, "#FFFF00", "#9AF742" )
        }

        fun crearBosque(): Terreno {
            return Terreno("Bosque", "src\\main\\resources\\images\\bosque.png", true, "#024775", "#55F866" )
        }

        fun crearCiudad(): Terreno {
            return Terreno("Ciudad", "src\\main\\resources\\images\\pueblo.png", true, "#7C7F7C", "#BDC3BC" )
        }

        fun crearMar(): Terreno {
            return Terreno("Mar", "src\\main\\resources\\images\\mar.png", false, "#2663E7", "#3054F7" )
        }

        fun crearMontana(): Terreno {
            return Terreno("Montaña", "src\\main\\resources\\images\\montana.png", false, "#4FF31F", "#A8FB92" )
        }

        fun crearTerrenoDesconocido(): Terreno {
            return Terreno("Desconocido", "src\\main\\resources\\images\\desconocido.png", false, "#32113A", "#DFE5DD" )
        }
    }
}