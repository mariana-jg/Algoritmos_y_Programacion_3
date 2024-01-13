package StackDeCartas;

import java.io.Serializable;
import java.util.Stack;
import Carta.*;
import Columna.ColumnaDeJuego;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
public class StackDeCartas implements Serializable {
    protected Stack<Carta> cartas;
    public StackDeCartas(){
        this.cartas = new Stack<>();
    }
    public void agregarCarta(Carta carta){
        this.cartas.push(carta);
    }
    public Carta robarUltimaCarta(){
        return this.cartas.pop();
    }
    public boolean estaVacia(){
        return this.cartas.empty();
    }
    public Carta verUltimaCarta(){
        return this.cartas.peek();
    }
    public void cambiarAColumna(ColumnaDeJuego columnaDestino){
        Carta cartaCopia = this.robarUltimaCarta();
        columnaDestino.agregarCarta(cartaCopia);
    }
    public void cambiarAStack(StackDeCartas stackDestino){
        Carta cartaCopia = this.robarUltimaCarta();
        stackDestino.agregarCarta(cartaCopia);
    }


}
