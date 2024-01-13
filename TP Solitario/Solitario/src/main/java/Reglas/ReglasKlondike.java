package Reglas;

import Carta.*;
import Columna.*;
import StackDeCartas.*;

public class ReglasKlondike extends Reglas {

    //-----------------------------------------------------Métodos----------------------------------------------------//
    public boolean esCartaVisible(ColumnaDeJuego columnaOrigen, int indiceCartaOrigen){
        return columnaOrigen.esCartaVisible(indiceCartaOrigen);
    }
    public boolean puedoExtraerDeColumna(ColumnaDeJuego columna){
        return !columna.estaVacia();
    }
    public boolean puedoSacarCartaDelMazo(Mazo mazo){return !mazo.estaVacia();}
    public boolean puedoSacarCartaDelDescarte(Descarte descarte){return !descarte.estaVacia();}
    public boolean puedoAgregarCartasAColumna(ColumnaDeJuego columnaExtraer, ColumnaDeJuego columnaDepositar){
            Carta primeraCartaExtraer = columnaExtraer.verPrimeraCarta();

            return(puedoAgregarCarta(primeraCartaExtraer.obtenerNumero(),primeraCartaExtraer.obtenerPalo(),columnaDepositar));

    }
    public boolean puedoAgregarCarta(int numeroCarta, Palo paloCarta, ColumnaDeJuego columna){//columna a columna
        if(columna.estaVacia()){
            return numeroCarta == REY; // si la carta es igual a 13 devuelve true
        } else {
            return(numeroCarta == columna.verUltimaCarta().obtenerNumero()-1 &&
                    (paloCarta.obtenerColor() != columna.verUltimaCarta().obtenerPalo().obtenerColor()));
        }
    }
}
