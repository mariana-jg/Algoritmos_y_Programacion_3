package Carta;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.Serializable;
import ManejoDeArchivos.ManejoDeArchivos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Carta implements Serializable {

    //-----------------------------------------------------Atributos---------------------------------------------------//
    private final int numero;
    private final Palo palo;
    private boolean bocaArriba;


    //-----------------------------------------------------Metodos---------------------------------------------------//
    public Carta(int numero, Palo palo){
        this.numero = numero;
        this.palo = palo;
        this.bocaArriba = false;

    }
    public boolean esVisible(){
        return bocaArriba;
    }
    public void cambiarVisibilidad(){
        this.bocaArriba = !this.bocaArriba;
    }
    public Color obtenerColor(){
        return this.palo.obtenerColor();
    }
    public Palo obtenerPalo(){
        return this.palo;
    }
    public int obtenerNumero(){
        return this.numero;
    }
    public boolean esPosterior(int numeroCarta){
        return this.numero == (numeroCarta + 1);
    }
    public boolean esAnterior(int numeroCarta){
        return (this.numero) == (numeroCarta - 1);
    }
    public boolean esMismoPalo(Palo paloCarta){
        return this.palo == paloCarta;
    }




}
