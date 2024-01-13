package StackDeCartas;
import Carta.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


import java.util.Stack;
public class Descarte extends StackDeCartas {
    //-----------------------------------------------------Métodos----------------------------------------------------//
    public Descarte(){
        this.cartas = new Stack<>();
    }
    public void vaciarDescarte(Mazo mazo) {
        while (!this.estaVacia()){
            Carta cartaAAgregar = this.robarUltimaCarta();
            cartaAAgregar.cambiarVisibilidad();
            mazo.agregarCarta(cartaAAgregar);
        }
    }
    @Override
    public void agregarCarta(Carta carta){
        carta.cambiarVisibilidad();
        this.cartas.push(carta);
    }


}
