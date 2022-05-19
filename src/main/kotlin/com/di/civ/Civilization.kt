package com.di.civ

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.stage.Stage

class Civilization : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(Civilization::class.java.getResource("map.fxml"))
        val scene = Scene(fxmlLoader.load(), 1000.0, 700.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
        val mapController = fxmlLoader.getController<MapController>()
        scene.addEventHandler(KeyEvent.KEY_PRESSED) { keyPressed ->
            when (keyPressed.code) {
                KeyCode.UP -> {
                    println("UP")
                    mapController.moverArriba()
                }
                KeyCode.DOWN -> {
                    println("DOWN")
                    mapController.moverAbajo()
                }
                KeyCode.RIGHT -> {
                    println("RIGHT")
                    mapController.moverDerecha()
                }
                KeyCode.LEFT -> {
                    println("RIGHT")
                    mapController.moverIzquierda()
                }
            }
        }
    }
}

fun main() {
    Application.launch(Civilization::class.java)
}