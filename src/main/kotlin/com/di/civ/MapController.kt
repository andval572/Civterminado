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
                val anchorPane = AnchorPane()
                anchorPane.children.add(0, ImageView())
                anchorPane.children.add(1, ImageView())
                anchorPane.children.add(2, Label("fila $fila columna $columna"))
                root.add(anchorPane, columna, fila)

            }
        root.hgap = 5.0
        root.vgap = 5.0
        root.padding = Insets(50.0, 50.0, 50.0, 50.0)
    }

    private fun rellenarGirdPaneConMapa(subMapa: MutableList<MutableList<Terreno>>) {
        var pos = 0
        subMapa.forEach { terrenos ->
            terrenos.forEach { terreno ->
                val anchorPane = root.children[pos]
                anchorPane as AnchorPane
                anchorPane.style = "-fx-background-color: ${terreno.colorTerreno};" // $terreno.color
                anchorPane.setOnMouseClicked {
                    labelTerreno.text = "Terreno: " +mostrarTerrenoActual(terreno)
                    abrirVentanaDetails(terreno)
                }
                val imageView = anchorPane.children[0] as ImageView
                terreno.unidad?.let {
                    val f1 = File(it.imagen)
                    imageView.fitHeight = 20.0
                    imageView.fitWidth = 20.0
                    imageView.layoutX =0.0
                    imageView.layoutY=0.0
                    imageView.image = Image(f1.toURI().toURL().toString())
                }?:run{
                    imageView.image = null
                }

                val imageView1= anchorPane.children[1] as ImageView
                val f = File(terreno.imagen)
                imageView1.fitHeight = 45.0
                imageView1.fitWidth = 45.0
                imageView1.layoutX = 20.0
                imageView1.layoutY= 20.0
                imageView1.image = Image(f.toURI().toURL().toString())


                val label = anchorPane.children[2] as Label
                label.text = terreno.nombre
                label.maxWidth = 80.0
                label.minWidth = 80.0
                label.layoutX =0.0
                label.layoutY=70.0
                label.style = "-fx-background-color: ${terreno.colorTexto};"


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