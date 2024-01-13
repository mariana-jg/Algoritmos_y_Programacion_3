package StackDeCartas;

import Carta.Carta;
import Carta.Palo;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;
public class Mazo extends StackDeCartas {
    //-----------------------------------------------------Métodos----------------------------------------------------//
    public Mazo() {
        this.cartas = new Stack<>();
        this.llenarMazo();
    }
    private void llenarMazo() {
        for (Palo palo : Palo.values()) {
            for (int numero = 1; numero < 14; numero++) {
                Carta carta = new Carta(numero, palo);
                this.agregarCarta(carta);
            }
        }
    }
    public void mezclarMazo() {
        //mezcla completamente al azar el mazo
        Collections.shuffle(this.cartas);
    }

    public void pasarCartaADescarte(Descarte descarte){
        descarte.agregarCarta(this.robarUltimaCarta());
    }


}