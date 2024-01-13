package StackDeCartas;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.Stack;
public class Fundacion extends StackDeCartas {
    //-----------------------------------------------------Atributos---------------------------------------------------//
    private final int FUNDACIONCOMPLETA = 13;
    //-----------------------------------------------------Métodos----------------------------------------------------//
    public Fundacion(){
        this.cartas = new Stack<>();
    }
    public boolean estaCompleta(){
        return this.cartas.size() == FUNDACIONCOMPLETA;
    }


}
