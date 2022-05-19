package com.di.civ

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.File

class DetailsController {

    lateinit var lTerreno: Label
    lateinit var lColorTerreno: Label
    lateinit var lColorTexto: Label
    lateinit var lImagen: ImageView
    lateinit var lTransitable: Label
    lateinit var boton1 : Button
    lateinit var boton2 : Button
    lateinit var imagen2: ImageView
    var mapController:MapController? = null
    var terreno:Terreno?=null


    var granja = File("src\\main\\resources\\images\\ic_granja.png")
    var saquear = File("src\\main\\resources\\images\\ic_saqueo.png")
    var conquistar = File("src\\main\\resources\\images\\ic_corona.png")
    var mina = File("src\\main\\resources\\images\\ic_mina.png")

    fun enviarTerreno(terreno: Terreno) {
        this.terreno = terreno
        lTerreno.text = "Nombre: "+terreno.nombre
        lColorTerreno.style = "Color Terreno: "+"-fx-background-color: ${terreno.colorTerreno};"
        lColorTexto.style =  "Color Texto: "+"-fx-background-color: ${terreno.colorTexto};"
        val f = File(terreno.imagen)
        lImagen.image = Image(f.toURI().toURL().toString())
        if (terreno.sePuedeAndarSobreEl) {
            lTransitable.text = "Transitable"
        } else
            lTransitable.text = "No transitable"

        when (terreno.nombre) {
            "Llanura" -> {
                boton1.text = "Crear Granja"
                boton2.isVisible = false
            }
            "Ciudad" -> {
                boton1.text = "Saquear"
                boton2.text = "Conquistar"
            }
            "Colina" -> {
                boton1.text = "Crear mina"
                boton2.isVisible = false
            }
            "Bosque" -> {
                boton1.isVisible = false
                boton2.isVisible = false
            }
            "Mar" -> {
                boton1.isVisible = false
                boton2.isVisible = false
            }
            "Montaña" -> {
                boton1.isVisible = false
                boton2.isVisible = false
            }
            "Desconocido" -> {
                boton1.isVisible = false
                boton2.isVisible = false
            }

        }
    }

    fun enviarMapController(mapController: MapController){
        this.mapController = mapController
    }

    fun onClikedBoton1() {

       when ( boton1.text ) {
           "Crear Granja" -> {
               imagen2.image = Image(granja.toURI().toURL().toString())
               terreno?.nombre = Terreno.CON_GRANJA
               mapController?.miPosiActual()
           }
           "Saquear" -> {
               imagen2.image = Image(saquear.toURI().toURL().toString())
               terreno?.nombre = Terreno.SAQUEADO
               mapController?.miPosiActual()
           }
           "Crear mina" -> {
               imagen2.image = Image(mina.toURI().toURL().toString())
               terreno?.nombre = Terreno.CON_MINA
               mapController?.miPosiActual()
           }
       }
    }
    fun onClickedBoton2() {

        if (boton2.text == "Conquistar") {
            imagen2.image = Image(conquistar.toURI().toURL().toString())
            terreno?.nombre = Terreno.CONQUISTADO
            mapController?.miPosiActual()
        }

    }

}
