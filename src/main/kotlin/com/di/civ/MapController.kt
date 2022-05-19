package com.di.civ

import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.stage.Stage
import java.io.File

class MapController {

    lateinit var root : GridPane
    lateinit var boton : Button
    lateinit var posicion: Label
    lateinit var labelTerreno: Label
    private val mapa = Mapa()
    private var subMapa = mapa.obtenerSubMapa()


    fun initialize() {
        iniciarGridPane()
        rellenarGirdPaneConMapa(subMapa)
    }

    private fun iniciarGridPane() {
        for (fila in 0 until Configuracion.filasCampoVision)
            for (columna in 0 until Configuracion.columnasCampoVision) {
                val vBox = VBox()
                vBox.children.add(0, ImageView())
                vBox.children.add(1, Label("fila $fila columna $columna"))
                root.add(vBox, columna, fila)
                vBox.alignment = Pos.CENTER
            }
        root.hgap = 5.0
        root.vgap = 5.0
        root.padding = Insets(50.0, 50.0, 50.0, 50.0)
    }

    private fun rellenarGirdPaneConMapa(subMapa: MutableList<MutableList<Terreno>>) {
        var pos = 0
        subMapa.forEach { terrenos ->
            terrenos.forEach { terreno ->
                val vBox = root.children[pos]
                vBox as VBox
                vBox.style = "-fx-background-color: ${terreno.colorTerreno};" // $terreno.color
                vBox.setOnMouseClicked {
                    labelTerreno.text = "Terreno: " +mostrarTerrenoActual(terreno)
                    abrirVentanaDetails(terreno)
                }

                val imageView = vBox.children[0] as ImageView
                val f = File(terreno.imagen)
                imageView.fitHeight = 60.0
                imageView.fitWidth = 60.0
                imageView.image = Image(f.toURI().toURL().toString())

                val label = vBox.children[1] as Label
                label.text = terreno.nombre
                label.maxWidth = 80.0
                label.minWidth = 80.0
                label.style = "-fx-background-color: ${terreno.colorTexto};"
                label.alignment = Pos.CENTER

                pos++

            }
        }
        mostrarPosicionActual()

    }

    fun moverArriba() {
        println("moverArriba")
        mapa.moverArriba()
        rellenarGirdPaneConMapa(mapa.obtenerSubMapa())
        mostrarPosicionActual()
    }

    fun moverAbajo() {
        println("moverAbajo")
        mapa.moverAbajo()
        rellenarGirdPaneConMapa(mapa.obtenerSubMapa())
        mostrarPosicionActual()
    }

    fun moverIzquierda() {
        println("moverIzquierda")
        mapa.moverIzquierda()
        rellenarGirdPaneConMapa(mapa.obtenerSubMapa())
        mostrarPosicionActual()
    }

    fun moverDerecha() {
        println("moverDerecha")
        mapa.moverDerecha()
        rellenarGirdPaneConMapa(mapa.obtenerSubMapa())
        mostrarPosicionActual()
    }

    fun miPosiActual(){
        subMapa = mapa.obtenerSubMapa()
        rellenarGirdPaneConMapa(subMapa)
        mostrarPosicionActual()
    }

    fun posiInicial(){
        mapa.centrarAlCero()
        subMapa = mapa.obtenerSubMapa()
        rellenarGirdPaneConMapa(subMapa)
        mostrarPosicionActual()
    }
    fun mostrarPosicionActual(){
        posicion.text="Mi posicion es ("+mapa.obtenerFilaActual()+","+mapa.obtenerColumnaActual()+")"
    }
    fun mostrarTerrenoActual(terreno: Terreno): String{
        return terreno.nombre

    }

    fun abrirVentanaDetails(terreno: Terreno){

        val stage = Stage()
        val loader = FXMLLoader(javaClass.getResource("details.fxml"))
        val root = loader.load<AnchorPane>()
        val scene = Scene(root,500.0,500.0)
        stage.scene = scene
        stage.show()
        val detailsController = loader.getController<DetailsController>()
        detailsController.enviarTerreno(terreno)
        detailsController.enviarMapController(this)
    }


}